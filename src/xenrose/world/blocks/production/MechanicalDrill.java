package xenrose.world.blocks.production;

import arc.Core;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.Texture;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.struct.EnumSet;
import arc.util.Eachable;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.entities.Effect;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Itemsc;
import mindustry.gen.Sounds;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.type.Item;
import mindustry.world.Tile;
import mindustry.world.blocks.production.BeamDrill;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.Env;

import static mindustry.Vars.tilesize;

public class MechanicalDrill extends BeamDrill {
    public TextureRegion rotatorRegion, baseRegion;
    public boolean drawSpinSprite = true;
    public float rotateSpeed = 2f;
    public Effect drillEffect = Fx.mine;
    public float drillEffectRnd = -1f;
    public float drillEffectChance = 0.02f;

    public MechanicalDrill(String name){
        super(name);

        hasItems = true;
        rotate = true;
        update = true;
        solid = true;
        drawArrow = false;
        regionRotated1 = 1;
        ambientSoundVolume = 0.05f;
        ambientSound = Sounds.minebeam;

        envEnabled |= Env.space;
        flags = EnumSet.of(BlockFlag.drill);
    }

    @Override
    public void load() {
        super.load();
        topRegion = Core.atlas.find(name + "-top");
        rotatorRegion = Core.atlas.find(name + "-rotator");
        baseRegion = Core.atlas.find(name + "-base");
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{baseRegion, rotatorRegion, topRegion};
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(baseRegion, plan.drawx(), plan.drawy());
        Draw.rect(topRegion, plan.drawx(), plan.drawy(), plan.rotation * 90);
        Draw.rect(rotatorRegion, plan.drawx(), plan.drawy());
    }

    public class MechanicalDrillBuild extends BeamDrillBuild{
        public float timeDrilled;
        public float warmup;

        @Override
        public void updateTile(){
            super.updateTile();

            if(lasers[0] == null) updateLasers();

            timeDrilled += warmup * delta();

            warmup = Mathf.approachDelta(warmup, Mathf.num(efficiency > 0), 1f / 60f);

            updateFacing();

            float multiplier = Mathf.lerp(1f, optionalBoostIntensity, optionalEfficiency);
            float drillTime = getDrillTime(lastItem);
            boostWarmup = Mathf.lerpDelta(boostWarmup, optionalEfficiency, 0.1f);
            lastDrillSpeed = (facingAmount * multiplier * timeScale) / drillTime;

            time += edelta() * multiplier;

            if(time >= drillTime){
                for(Tile tile : facing){
                    Item drop = tile == null ? null : tile.wallDrop();
                    if(items.total() < itemCapacity && drop != null){
                        items.add(drop, 1);
                        produced(drop);
                    }
                }
                time %= drillTime;
            }

            if(timer(timerDump, dumpTime / timeScale)){
                dump();
            }

            if(wasVisible && Mathf.chanceDelta(drillEffectChance * warmup)) drillEffect.at(x + Mathf.range(drillEffectRnd), y + Mathf.range(drillEffectRnd), Color.valueOf("bfb3a3"));
        }

        @Override
        public void draw(){
            Draw.rect(baseRegion, x, y);
            if(drawSpinSprite){
                Drawf.spinSprite(rotatorRegion, x, y, timeDrilled * rotateSpeed);
            }else{
                Draw.rect(rotatorRegion, x, y, timeDrilled * rotateSpeed);
            }

            Draw.rect(topRegion, x, y, rotdeg());

            if(isPayload()) return;

            var dir = Geometry.d4(rotation);
            int ddx = Geometry.d4x(rotation + 1), ddy = Geometry.d4y(rotation + 1);

            for(int i = 0; i < size; i++){
                Tile face = facing[i];
                if(face != null){
                    Point2 p = lasers[i];
                    float lx = face.worldx() - (dir.x/2f)*tilesize, ly = face.worldy() - (dir.y/2f)*tilesize;

                    float width = (laserWidth + Mathf.absin(Time.time + i*5 + (id % 9)*9, glowScl, pulseIntensity)) * warmup;

                    Draw.z(Layer.power - 1);
                    Draw.mixcol(glowColor, Mathf.absin(Time.time + i*5 + id*9, glowScl, glowIntensity));
                    if(Math.abs(p.x - face.x) + Math.abs(p.y - face.y) == 0){
                        Draw.scl(width);

                        if(boostWarmup < 0.01f){
                            Draw.alpha(1f - boostWarmup);
                            Draw.rect(laserCenter, lx, ly);
                        }

                        if(boostWarmup > 0.01f){
                            Draw.alpha(boostWarmup);
                            Draw.rect(laserCenter, lx, ly);
                        }

                        Draw.scl();
                    }else{
                        float lsx = (p.x - dir.x/2f) * tilesize, lsy = (p.y - dir.y/2f) * tilesize;

                        if(boostWarmup < 0.99f){
                            Draw.alpha(1f - boostWarmup);
                            Drawf.laser(laser, laserEnd, lsx, lsy, lx, ly, width);
                        }

                        if(boostWarmup > 0.001f){
                            Draw.alpha(boostWarmup);
                            Drawf.laser(laser, laserEnd, lsx, lsy, lx, ly, width);
                        }
                    }
                    Draw.color();
                    Draw.mixcol();

                    Draw.z(Layer.effect);
                    Lines.stroke(warmup);
                    rand.setState(i, id);
                    Color col = face.wallDrop().color;
                    Color spark = Tmp.c3.set(sparkColor).lerp(boostHeatColor, boostWarmup);
                    for(int j = 0; j < sparks; j++){
                        float fin = (Time.time / sparkLife + rand.random(sparkRecurrence + 1f)) % sparkRecurrence;
                        float or = rand.range(2f);
                        Tmp.v1.set(sparkRange * fin, 0).rotate(rotdeg() + rand.range(sparkSpread));

                        Draw.color(spark, col, fin);
                        float px = Tmp.v1.x, py = Tmp.v1.y;
                        if(fin <= 1f) Lines.lineAngle(lx + px + or * ddx, ly + py + or * ddy, Angles.angle(px, py), Mathf.slope(fin) * sparkSize);
                    }
                    Draw.reset();
                }
            }

            if(glowRegion.found()){
                Draw.z(Layer.blockAdditive);
                Draw.blend(Blending.additive);
                Draw.color(Tmp.c1.set(heatColor).lerp(boostHeatColor, boostWarmup), warmup * (heatColor.a * (1f - heatPulse + Mathf.absin(heatPulseScl, heatPulse))));
                Draw.rect(glowRegion, x, y, rotdeg());
                Draw.blend();
                Draw.color();
            }

            Draw.blend();
            Draw.reset();
        }
    }
}
