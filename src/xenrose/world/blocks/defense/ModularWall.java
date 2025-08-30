package xenrose.world.blocks.defense;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.util.Eachable;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.content.Fx;
import mindustry.entities.units.BuildPlan;
import mindustry.graphics.Layer;
import mindustry.world.blocks.defense.Wall;
import xenrose.XenContent.BlocksModifiers;
import xenrose.type.Modifier;
import xenrose.ui.XenStyles;
import xenrose.util.XenIcons;

import static mindustry.Vars.*;

public class ModularWall extends Wall{
    public float healReload = 1;
    public float healPercent = 7f;

    public float shieldHealth = 1600f;
    public float regenSpeed = 2f;
    public float breakCooldown = 60f * 4f;

    public int canApply = -1;
    public TextureRegion armorRegion;
    public TextureRegion reflectRegion;

    public ModularWall(String name){
        super(name);
        configurable = true;
        saveConfig = true;
        clearOnDoubleTap = true;
        update = true;
    }

    @Override
    public void load() {
        super.load();
        armorRegion = Core.atlas.find(name + "-armored");
        reflectRegion = Core.atlas.find(name + "-reflect");
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
        public boolean addArmor = false;
        public boolean repair = false, used = false;
        public boolean reflect = false, reflectUsed = false;
        public boolean shieldAdd = false, shieldUsed = false;

        public float charge = 0, recharge = 0;

        public float preparation = 1;
        public int armor = 0, ref = 0;

        boolean canHeal = true;

        public float shield = shieldHealth;
        public float shieldRadius;
        public float breakTimer;

        private int enumModules(){
            if(Modifier.isUnlock(BlocksModifiers.reflectModifier)){
                if(Modifier.isUnlock(BlocksModifiers.healTechTree)){
                    if(Modifier.isUnlock(BlocksModifiers.diocasiumArmor)){
                        if(Modifier.isUnlock(BlocksModifiers.shieldModifier)) return canApply = 4;
                        return canApply = 3;
                    }
                    return canApply = 2;
                }
                return canApply = 1;
            }
            return canApply;
        }

        @Override
        public void buildConfiguration(Table t){

            //Campaign Configurations
            if(state.isCampaign()) {

               switch(enumModules()){

                   //Reflect Module
                   case 1 -> {
                       if(!reflectUsed){
                           t.button(XenIcons.reflectModuleAdd, XenStyles.reflectModule, () -> {
                               reflect = true;
                               reflectUsed = true;
                               ref = 1;
                               deselect();
                           }).size(50);

                           t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                           t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                           t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                       }else{
                           t.button(XenIcons.reflectModuleUsed, XenStyles.buttonUsed, () -> {}).size(50f);

                           t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                           t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                           t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                       }
                       break;
                   }

                   //Heal Module
                   case 2 -> {
                       if(!reflectUsed){
                           t.button(XenIcons.reflectModuleAdd, XenStyles.reflectModule, () -> {
                               reflect = true;
                               reflectUsed = true;
                               deselect();
                           }).size(50);
                       }else{
                           t.button(XenIcons.reflectModuleUsed, XenStyles.buttonUsed, () -> {}).size(50f);
                       }

                       if(!used){
                           t.button(XenIcons.maxHealth, XenStyles.maxHealthButton, () -> {
                               repair = true;
                               used = true;
                               recharge = 12000;
                               deselect();
                           }).size(50);

                           t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                           t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                       }else{
                           t.button(XenIcons.maxHealthUsed, XenStyles.buttonUsed, () -> {}).size(50f);

                           t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                           t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                       }
                       break;
                   }

                   //Armor
                   case 3 -> {
                       if(!reflectUsed){
                           t.button(XenIcons.reflectModuleAdd, XenStyles.reflectModule, () -> {
                               reflect = true;
                               reflectUsed = true;
                               deselect();
                           }).size(50);
                       }else{
                           t.button(XenIcons.reflectModuleUsed, XenStyles.buttonUsed, () -> {}).size(50f);
                       }

                       if(!used){
                           t.button(XenIcons.maxHealth, XenStyles.maxHealthButton, () -> {
                               repair = true;
                               used = true;
                               recharge = 12000;
                               deselect();
                           }).size(50);
                       }else{
                           t.button(XenIcons.maxHealthUsed, XenStyles.buttonUsed, () -> {}).size(50f);
                       }

                       if(!addArmor){
                           t.button(XenIcons.armorAdd, XenStyles.armorButton, () -> {
                               addArmor = true;
                               preparation = 6;
                               deselect();
                           }).size(50);

                           t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                       }else{
                           t.button(XenIcons.armorAddUsed, XenStyles.buttonUsed, () -> {}).size(50f);

                           t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                       }
                       break;
                   }

                   //Shield Module
                   case 4 -> {
                       if(!reflectUsed){
                           t.button(XenIcons.reflectModuleAdd, XenStyles.reflectModule, () -> {
                               reflect = true;
                               reflectUsed = true;
                               deselect();
                           }).size(50);
                       }else{
                           t.button(XenIcons.reflectModuleUsed, XenStyles.buttonUsed, () -> {}).size(50f);
                       }

                       if(!used){
                           t.button(XenIcons.maxHealth, XenStyles.maxHealthButton, () -> {
                               repair = true;
                               used = true;
                               recharge = 12000;
                               deselect();
                           }).size(50);
                       }else{
                           t.button(XenIcons.maxHealthUsed, XenStyles.buttonUsed, () -> {}).size(50f);
                       }

                       if(!addArmor){
                           t.button(XenIcons.armorAdd, XenStyles.armorButton, () -> {
                               addArmor = true;
                               preparation = 6;
                               deselect();
                           }).size(50);
                       }else{
                           t.button(XenIcons.armorAddUsed, XenStyles.buttonUsed, () -> {}).size(50f);
                       }

                       if(!shieldUsed) {
                           t.button(XenIcons.shieldAdd, XenStyles.shieldButton, () -> {
                               shieldAdd = true;
                               shieldUsed = true;
                               deselect();
                           }).size(50);
                       }else{
                           t.button(XenIcons.shieldUsed, XenStyles.buttonUsed, () -> {}).size(50f);
                       }
                   }
                   default -> {
                       t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                       t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                       t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                       t.button(XenIcons.moduleLock, XenStyles.buttonUsed, () -> {}).size(50f);
                   }
               }

            }else{
                //Sandbox Configurations
                if(!reflectUsed){
                    t.button(XenIcons.reflectModuleAdd, XenStyles.reflectModule, () -> {
                        reflect = true;
                        reflectUsed = true;
                        deselect();
                    }).size(50);
                }else{
                    t.button(XenIcons.reflectModuleUsed, XenStyles.buttonUsed, () -> {}).size(50f);
                }

                if(!used){
                    t.button(XenIcons.maxHealth, XenStyles.maxHealthButton, () -> {
                        repair = true;
                        used = true;
                        recharge = 12000;
                        deselect();
                    }).size(50);
                }else{
                    t.button(XenIcons.maxHealthUsed, XenStyles.buttonUsed, () -> {}).size(50f);
                }

                if(!addArmor){
                    t.button(XenIcons.armorAdd, XenStyles.armorButton, () -> {
                        addArmor = true;
                        preparation = 6;
                        deselect();
                    }).size(50);
                }else{
                    t.button(XenIcons.armorAddUsed, XenStyles.buttonUsed, () -> {}).size(50f);
                }

                if(!shieldUsed) {
                    t.button(XenIcons.shieldAdd, XenStyles.shieldButton, () -> {
                        shieldAdd = true;
                        shieldUsed = true;
                        deselect();
                    }).size(50);
                }else{
                    t.button(XenIcons.shieldUsed, XenStyles.buttonUsed, () -> {}).size(50f);
                }
            }
        }

        @Override
        public void draw(){
            super.draw();

            if(reflect) Draw.rect(reflectRegion, x, y);

            if(addArmor) Draw.rect(armorRegion, x, y);


            if(shieldAdd) {
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
                }

                Draw.reset();
            }
        }

        @Override
        public void updateTile() {

            //Reflect Module
            if(reflect) {
                chanceDeflect = 20f;
                if(ref >= 1) {
                    Fx.dooropen.at(x, y, block.size, block);
                    ref = 0;
                }
            } else chanceDeflect = 0;

            //Armor
            if(addArmor){
                if(armor <= 0) {
                    maxHealth(maxHealth * 5f);
                    armor = 1;
                    Fx.dooropen.at(x, y, block.size, block);
                    health(maxHealth());

                }
            }

            if(addArmor && health() <= (maxHealth() / 2.2f)){
                addArmor = false;
                Fx.dooropen.at(x, y, block.size, block);
            }

            //Heal
            if(used){
                recharge -= Time.delta;
                if(recharge <= 0) used = false;
            }

            charge += Time.delta;
            if(charge >= healReload && canHeal && repair && health() < maxHealth()) {
                charge = 0f;

                heal((maxHealth() / 5) * (healPercent) / 100f);
                recentlyHealed();

                if(health() >= maxHealth()) repair = false;
            }

            //Shield
            if(shieldAdd){
                if(breakTimer > 0){
                    breakTimer -= Time.delta;
                }else{
                    shield = Mathf.clamp(shield + regenSpeed * edelta(), 0f, shieldHealth);
                }

                if(hit > 0){
                    hit -= Time.delta / 10f;
                    hit = Math.max(hit, 0f);
                }

                shieldRadius = Mathf.lerpDelta(shieldRadius, broken() ? 0f : 2.5f, 0.12f);
            }
        }

        public boolean broken(){
            return breakTimer > 0 || !canConsume();
        }

        @Override
        public void damage(float damage){
            if(shieldAdd){
                float shieldTaken = broken() ? 0f : Math.min(shield, damage);
                shield -= shieldTaken;

                if(shieldTaken > 0){
                    hit = 1f;
                }

                if(shield <= 0.00001f && shieldTaken > 0){
                    breakTimer = breakCooldown;
                }

                if(damage - shieldTaken > 0) {
                    super.damage(damage - shieldTaken);
                }
            }else{
                super.damage(damage);
            }
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.bool(reflectUsed);
            write.bool(repair);
            write.bool(used);
            write.bool(addArmor);
            write.bool(shieldAdd);
            write.bool(shieldUsed);

            if(shieldAdd) write.f(shield);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            this.reflectUsed = read.bool();
            this.repair = read.bool();
            this.used = read.bool();
            this.addArmor = read.bool();
            this.shieldAdd = read.bool();
            this.shieldUsed = read.bool();

            if(shieldAdd) this.shield = read.f();
        }
    }
}
