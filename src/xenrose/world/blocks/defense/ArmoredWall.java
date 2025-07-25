package xenrose.world.blocks.defense;

import arc.Core;
import arc.audio.Sound;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Eachable;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Icon;
import mindustry.gen.Sounds;
import mindustry.graphics.Layer;
import mindustry.ui.Styles;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.meta.Stat;
import xenrose.ui.XenStyles;
import xenrose.util.XenIcons;

import static mindustry.Vars.*;

public class ArmoredWall extends Wall{

    //sdffrenvervsmmekscmkdslefmcsdmc(helppppp)

    public TextureRegion armorRegion;
    public Effect addArmorfx = Fx.dooropen;
    public Effect removeArmorfx = Fx.doorclose;
    public float shieldHealth = 900f;
    public float breakCooldown = 60f * 10f;
    public float regenSpeed = 2f;
    Seq<Armor> unlockedNow = new Seq<>();

    public boolean flashHit;
    public Color flashColor = Color.white;
    public Sound deflectSound = Sounds.none;

    public ArmoredWall(String name){
        super(name);
        configurable = true;
        saveConfig = true;
        clearOnDoubleTap = true;
        update = true;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.shieldHealth, shieldHealth);
    }

    @Override
    public void load() {
        super.load();
        armorRegion = Core.atlas.find(name + "-armored");
    }

    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{armorRegion, region};
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
    }

    public class ArmoredWallBuild extends WallBuild{
        public float hit;
        public boolean addArmor = false;
        public float shield = shieldHealth, shieldRadius = 0f;
        public float breakTimer;
        public int currentPlan = -1;

        @Override
        public void buildConfiguration(Table table){
            Seq<Armor> armor = Seq.with(unlockedNow).map(u -> u.armored).retainAll(a -> unlockedNow() && !state.rules.isBanned(a));
            if(armor.any()){
                table.button(XenIcons.armorAdd, XenStyles.sandButton, () -> {
                    addArmor = true;
                    deselect();
                }).size(68f);
                table.button(XenIcons.armorRemove, XenStyles.sandButton, () -> {
                    addArmor = false;
                    deselect();
                }).size(68f);
                }else{
                table.button(Icon.lock, Styles.cleari, () -> {}).size(45f);
            }
        }

        @Override
        public void updateTile() {
            if(addArmor) {
                if (breakTimer > 0) {
                    breakTimer -= Time.delta;
                } else {
                    //regen when not broken
                    shield = Mathf.clamp(shield + regenSpeed * edelta(), 0f, shieldHealth);
                }

                if (hit > 0) {
                    hit -= Time.delta / 10f;
                    hit = Math.max(hit, 0f);
                }
                shieldRadius = Mathf.lerpDelta(shieldRadius, (broken() || !addArmor) ? 0f : 1f, 0.12f);
            }

        }

        public boolean broken(){
            return breakTimer > 0 || !canConsume();
        }

        @Override
        public void draw(){
            super.draw();

            if(addArmor) {
                Draw.rect(armorRegion, x, y);
            }

            if(addArmor){
                if (shieldRadius > 0) {
                    float radius = shieldRadius * tilesize * size / 2f;

                    Draw.z(Layer.shields);

                    Draw.color(team.color, Color.white, Mathf.clamp(hit));

                    if (renderer.animateShields) {
                        Fill.square(x, y, radius);
                    } else {
                        Lines.stroke(1.5f);
                        Draw.alpha(0.09f + Mathf.clamp(0.08f * hit));
                        Fill.square(x, y, radius);
                        Draw.alpha(1f);
                        Lines.poly(x, y, 4, radius, 45f);
                        Draw.reset();
                    }

                    Draw.reset();
            }
            if(flashHit) {
                if (hit < 0.0001f) return;

                Draw.color(flashColor);
                Draw.alpha(hit * 0.5f);
                Draw.blend(Blending.additive);
                Fill.rect(x, y, tilesize * size, tilesize * size);
                Draw.blend();
                Draw.reset();

                if (!state.isPaused()) {
                    hit = Mathf.clamp(hit - Time.delta / 10f);
                }
            }
            }
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(shield);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            shield = read.f();
            if(shield > 0) shieldRadius = 1f;
        }
    }
}
