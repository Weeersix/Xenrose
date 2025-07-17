package xenrose.world.blocks.units;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.Time;
import mindustry.content.StatusEffects;
import mindustry.entities.Units;
import mindustry.gen.Building;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;
import mindustry.world.Block;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;
import mindustry.world.meta.StatUnit;

import static mindustry.Vars.tilesize;

public class StatusBlock extends Block {
    //stoled from Psammos Mod :3
    public float range = 80f;
    public float statusDuration = 1500;
    public float polyRad = 4f, polySpinScl = 0.8f, polyStroke = 1.5f;
    public int sides = 6;
    public StatusEffect status = StatusEffects.shielded;

    static final float refreshInterval = 6f;

    public StatusBlock(String name) {
        super(name);
        update = true;
        solid = true;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.range, range / tilesize, StatUnit.blocks);
        stats.add(
                new Stat("xen-status", StatCat.function),
                (status.hasEmoji() ? status.emoji() : "") + "[stat]" + status.localizedName + (status.reactive ? "" : "[lightgray] ~ [stat]" + ((int)(statusDuration / 60f)) + "[lightgray] " + Core.bundle.get("unit.seconds"))
        );
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, Pal.accent);
    }

    public class StatusBlockBuild extends Building {
        public float circleSpeed = 90f, circleStroke = 3f;
        public float warmup = 0f;
        public float totalProgress = 0f;
        public float refresh = Mathf.random(refreshInterval);
        public Seq<Unit> targets = new Seq<>();

        @Override
        public void updateTile(){

            if(potentialEfficiency > 0 && (refresh += Time.delta) >= refreshInterval){
                targets.clear();
                refresh = 0f;
                Units.nearby(team, x, y, range, u -> {
                    targets.add(u);
                });
            }

            if(efficiency > 0){
                for(Unit target : targets){
                    target.apply(status, statusDuration);
                }
            }

            warmup = Mathf.lerp(warmup, efficiency, 0.08f);
            totalProgress += Time.delta / circleSpeed;
        }

        @Override
        public boolean shouldConsume(){
            return targets.size > 0;
        }

        @Override
        public void draw(){
            super.draw();

            if(warmup <= 0.001f) return;

            Draw.z(Layer.effect);
            float mod = totalProgress % 1f;
            Draw.color(Pal.accent.cpy().a(0.5f));

            Lines.stroke(circleStroke * (1f - mod) * warmup);
            Lines.poly(x, y, sides, range * mod, mod * 180);
            Lines.poly(x, y, sides, range * mod * 0.8f, (360f / sides / 2f) - (mod * 180));

            Lines.stroke(polyStroke);
            Lines.poly(x, y, sides, polyRad * warmup, Time.time / polySpinScl);
            Draw.reset();
        }

        @Override
        public float warmup(){
            return warmup;
        }

        @Override
        public void drawSelect(){
            Drawf.dashCircle(x, y, range, Pal.placing);
        }
    }
}
