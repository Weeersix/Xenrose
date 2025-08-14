package xenrose.world.blocks.defense;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.scene.ui.layout.Table;
import arc.util.Eachable;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.content.Fx;
import mindustry.entities.units.BuildPlan;
import mindustry.world.blocks.defense.Wall;
import xenrose.XenContent.BlocksModifiers;
import xenrose.type.Modifier;
import xenrose.type.SecondModifier;
import xenrose.ui.XenStyles;
import xenrose.util.XenIcons;

import static mindustry.Vars.state;

public class ArmoredWall extends Wall{
    public float healReload = 1;
    public float healPercent = 7f;

    public TextureRegion armorRegion;

    public ArmoredWall(String name){
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
    }

    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{armorRegion, region};
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
    }

    public boolean canApplyOne(SecondModifier m){
        return m.unlockedNow() && !m.isBanned();
    }
    public boolean canApplyTwo(Modifier m2){
        return m2.unlockedNow() && !m2.isBanned();
    }

    public class ArmoredWallBuild extends WallBuild{
        public boolean addArmor = false, repair = false, used = false;
        public float charge = 0, recharge = 0;
        public int armor = 0;
        boolean canHeal = true;

        @Override
        public void buildConfiguration(Table table){

            //Campaign Configurations
            if(state.isCampaign()) {

                //Heal Module
                if(canApplyOne((SecondModifier) BlocksModifiers.healTechTree)) {
                    if (!used) {
                        table.button(XenIcons.maxHealth, XenStyles.maxHealthButton, () -> {
                            repair = true;
                            used = true;
                            recharge = 10000;
                            deselect();
                        }).size(68f);
                    } else {
                        table.button(XenIcons.maxHealthUsed, XenStyles.maxHealthButtonUsed, () -> {
                        }).size(68f);
                    }

                    //Armor Module
                    if(!canApplyTwo(BlocksModifiers.diocasiumArmor)) {
                        table.button(XenIcons.moduleLock, XenStyles.lockButton, () -> {}).size(68f);
                    }else{
                        if (!addArmor) {
                            table.button(XenIcons.armorAdd, XenStyles.armorButton, () -> {
                                addArmor = true;
                                deselect();
                            }).size(68f);
                        } else {
                            table.button(XenIcons.armorAddUsed, XenStyles.armorButtonUsed, () -> {
                            }).size(68f);
                        }
                    }
                }else{
                    table.button(XenIcons.moduleLock, XenStyles.lockButton, () -> {}).size(68f);
                    table.button(XenIcons.moduleLock, XenStyles.lockButton, () -> {}).size(68f);
                }
            }else{
                //Sandbox Configurations
                if(!used){
                    table.button(XenIcons.maxHealth, XenStyles.maxHealthButton, () -> {
                        repair = true;
                        used = true;
                        recharge = 400;
                        deselect();
                    }).size(68f);
                }else{
                    table.button(XenIcons.maxHealthUsed, XenStyles.maxHealthButtonUsed, () -> {
                    }).size(68f);
                }
                if(!addArmor){
                    table.button(XenIcons.armorAdd, XenStyles.armorButton, () -> {
                        addArmor = true;
                        deselect();
                    }).size(68f);
                }else{
                    table.button(XenIcons.armorAddUsed, XenStyles.armorButtonUsed, () -> {
                    }).size(68f);
                }
            }
        }

        @Override
        public void updateTile() {
            //Armor
            if(addArmor){
                if(armor <= 0) {
                    maxHealth(maxHealth * 5f);
                    armor = 1;
                    health(maxHealth());
                    Fx.dooropen.at(x, y, block.size, block);
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
        }

        @Override
        public void draw(){
            super.draw();

            if(addArmor) {
                Draw.rect(armorRegion, x, y);
            }
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.bool(repair);
            write.bool(used);
            write.bool(addArmor);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            this.repair = read.bool();
            this.used = read.bool();
            this.addArmor = read.bool();
        }
    }
}
