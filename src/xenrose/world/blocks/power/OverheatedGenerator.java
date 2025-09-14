package xenrose.world.blocks.power;

import arc.Core;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.entities.Units;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.type.Liquid;
import mindustry.ui.Bar;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.consumers.ConsumeItemEfficiency;
import mindustry.world.consumers.ConsumeItemFilter;
import mindustry.world.consumers.ConsumeLiquidFilter;
import mindustry.world.meta.StatUnit;
import xenrose.XenContent.XenUnits;
import xenrose.world.meta.XenStat;

import static mindustry.Vars.tilesize;

public class OverheatedGenerator extends ConsumeGenerator {
    public float maxLiquidTemp = 12;
    public float damageReload = 40;
    public Liquid consumeLiquidTemp;
    public static float stabilizingRange = 4;

    public OverheatedGenerator(String name) {
        super(name);
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("overheat", (OverheatedGeneratorBuild entity) ->
                new Bar(Core.bundle.get("bar.overheat"), Pal.lightOrange, () -> entity.overheat / 10)
        );
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.add(XenStat.stabilizing, stabilizingRange / tilesize, StatUnit.blocks);
    }

    @Override
    public void init(){
        filterItem = findConsumer(c -> c instanceof ConsumeItemFilter);
        filterLiquid = findConsumer(c -> c instanceof ConsumeLiquidFilter);

        //pass along the duration multipliers to the consumer, so it can display them properly
        if(filterItem instanceof ConsumeItemEfficiency eff){
            eff.itemDurationMultipliers = itemDurationMultipliers;
        }

        if(outputLiquid != null){
            outputsLiquid = true;
            hasLiquids = true;
        }

        if(explodeOnFull && outputLiquid != null && explosionPuddleLiquid == null){
            explosionPuddleLiquid = outputLiquid.liquid;
        }

        emitLight = true;
        lightRadius = baseLightRadius * size;

        updateClipRadius(stabilizingRange + tilesize);
        super.init();
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        x *= tilesize;
        y *= tilesize;
        x += offset;
        y += offset;

        Drawf.dashSquare(Pal.lightOrange, x, y, stabilizingRange * tilesize);
    }

    public class OverheatedGeneratorBuild extends ConsumeGeneratorBuild {
        public float charge = 0;
        public float overheat = 0;

        public boolean heatDamage = false;

        public float liquidTemp(Liquid liquid){
            return liquid.temperature;
        }

        public boolean foundUnit() {
            if(XenUnits.maneuver.useUnitCap) {
                return true;
            }else{
                return false;
            }
        }

        @Override
        public void updateTile(){
            boolean valid = efficiency > 0;

            if(productionEfficiency > 0 && liquids.currentAmount() > 0.5f) {
                overheat += (Time.delta / 100);

                if (overheat >= 9.7f) heatDamage = true;
            }else{
                overheat = 0;
                heatDamage = false;
            }

            Units.nearby(team, x, y, stabilizingRange + tilesize, u -> {
                if(foundUnit()){
                    overheat = 0;
                    heatDamage = false;
                }
            });

            if(liquidTemp(consumeLiquidTemp) > 0 && liquids.currentAmount() > 0.5f && heatDamage){
                charge += Time.delta;
                if(charge >= damageReload){
                    charge = 0;
                    damage(liquidTemp(consumeLiquidTemp) * (maxLiquidTemp / 10));
                }
            }

            warmup = Mathf.lerpDelta(warmup, valid ? 1f : 0f, warmupSpeed);

            productionEfficiency = efficiency * efficiencyMultiplier;
            totalTime += warmup * Time.delta;

            if(valid && Mathf.chanceDelta(effectChance)){
                generateEffect.at(x + Mathf.range(generateEffectRange), y + Mathf.range(generateEffectRange));
            }

            if(filterItem != null && valid && itemDurationMultipliers.size > 0 && filterItem.getConsumed(this) != null){
                itemDurationMultiplier = itemDurationMultipliers.get(filterItem.getConsumed(this), 1);
            }

            if(hasItems && valid && generateTime <= 0f){
                consume();
                consumeEffect.at(x + Mathf.range(generateEffectRange), y + Mathf.range(generateEffectRange));
                generateTime = 1f;
            }

            if(outputLiquid != null){
                float added = Math.min(productionEfficiency * delta() * outputLiquid.amount, liquidCapacity - liquids.get(outputLiquid.liquid));
                liquids.add(outputLiquid.liquid, added);
                dumpLiquid(outputLiquid.liquid);

            }

            generateTime -= delta() / (itemDuration * itemDurationMultiplier);
        }

        public float range(){
            return stabilizingRange * tilesize;
        }
    }
}
