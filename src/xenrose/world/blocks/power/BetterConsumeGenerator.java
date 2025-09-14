package xenrose.world.blocks.power;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.struct.ObjectFloatMap;
import arc.util.Nullable;
import arc.util.Time;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.game.Team;
import mindustry.graphics.Drawf;
import mindustry.type.Item;
import mindustry.type.LiquidStack;
import mindustry.world.Tile;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.consumers.ConsumeItemFilter;
import mindustry.world.consumers.ConsumeLiquidFilter;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.StatValues;

public class BetterConsumeGenerator extends ConsumeGenerator {
    public Effect generateEffect = Fx.none;
    public Attribute attribute = Attribute.heat;
    public TextureRegion chargedRegion;

    public boolean displayEfficiency = true;

    public float effectChance = 0.05f;
    public float minEfficiency = 0f;
    public float displayEfficiencyScale = 1f;
    public float lightClipSize;
    public float itemDuration = 120f;
    public float warmupSpeed = 0.05f;
    public float generateEffectRange = 3f;
    public float minCharge = 6;

    public @Nullable ConsumeItemFilter filterItem;
    public @Nullable ConsumeLiquidFilter filterLiquid;
    public @Nullable LiquidStack outputLiquid;

    public ObjectFloatMap<Item> itemDurationMultipliers = new ObjectFloatMap<>();

    public BetterConsumeGenerator(String name) {
        super(name);
    }

    @Override
    public void load() {
        super.load();
        chargedRegion = Core.atlas.find(name + "-charge");
    }

    public float getDisplayedPowerProduction(){
        return powerProduction / displayEfficiencyScale;
    }

    @Override
    public void init(){
        filterItem = findConsumer(c -> c instanceof ConsumeItemFilter);
        filterLiquid = findConsumer(c -> c instanceof ConsumeLiquidFilter);

        if(outputLiquid != null){
            outputsLiquid = true;
            hasLiquids = true;
        }

        emitLight = true;
        super.init();
        lightClipSize = Math.max(lightClipSize, 45f * size * 2f * 2f);
    }

    @Override
    public void setStats(){
        stats.timePeriod = itemDuration;
        super.setStats();

        stats.add(Stat.tiles, attribute, floating, size * size * displayEfficiencyScale, !displayEfficiency);
        stats.remove(generationType);
        stats.add(generationType, powerProduction * 60.0f / displayEfficiencyScale, StatUnit.powerSecond);

        if(outputLiquid != null){
            stats.add(Stat.output, StatValues.liquid(outputLiquid.liquid, outputLiquid.amount * size * size * 60f, true));
        }
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        if(displayEfficiency){
            drawPlaceText(Core.bundle.formatFloat("bar.efficiency", sumAttribute(attribute, x, y) * 100, 1), x, y, valid);
        }
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return tile.getLinkedTilesAs(this, tempTiles).sumf(other -> other.floor().attributes.get(attribute)) > minEfficiency;
    }

    public class BetterConsumeGeneratorBuild extends ConsumeGeneratorBuild {
        public float sum;
        public float itemDurationMultiplier = 1;

        private float itemCharge(){
            Item item = items.first();

            if(items.total() > 0) return item.charge;
            return 0;
        }

        @Override
        public void updateTile() {
            boolean valid = efficiency > 0;
            totalTime += warmup * Time.delta + (sum + attribute.env());

            warmup = Mathf.lerpDelta(warmup, valid ? 1f : 0f, warmupSpeed);

            if(hasItems){
                productionEfficiency = (efficiency * efficiencyMultiplier) + (sum + attribute.env());
            }else{
                productionEfficiency = sum + attribute.env();
            }

            if (productionEfficiency > 0.1f && Mathf.chanceDelta(effectChance)) {
                generateEffect.at(x + Mathf.range(3f), y + Mathf.range(3f));
            }

            if (outputLiquid != null) {
                float added = Math.min(productionEfficiency * delta() * outputLiquid.amount, liquidCapacity - liquids.get(outputLiquid.liquid));
                liquids.add(outputLiquid.liquid, added);
                dumpLiquid(outputLiquid.liquid);
            }

            if (filterItem != null && valid && itemDurationMultipliers.size > 0 && filterItem.getConsumed(this) != null) {
                itemDurationMultiplier = itemDurationMultipliers.get(filterItem.getConsumed(this), 1);
            }

            if (hasItems && valid && generateTime <= 0f) {
                consume();
                consumeEffect.at(x + Mathf.range(generateEffectRange), y + Mathf.range(generateEffectRange));
                generateTime = 1f;
            }

            if (outputLiquid != null) {
                float added = Math.min(productionEfficiency * delta() * outputLiquid.amount, liquidCapacity - liquids.get(outputLiquid.liquid));
                liquids.add(outputLiquid.liquid, added);
                dumpLiquid(outputLiquid.liquid);
            }
            generateTime -= delta() / (itemDuration * itemDurationMultiplier + (sum + attribute.env()));
        }

        @Override
        public void draw() {
            drawer.draw(this);

            super.draw();
            if(hasItems && itemCharge() > minCharge) Draw.rect(chargedRegion, x, y);

        }

        @Override
        public void afterPickedUp(){
            super.afterPickedUp();
            sum = 0f;
        }

        @Override
        public float totalProgress(){
            return totalTime;
        }

        @Override
        public void drawLight(){
            Drawf.light(x, y, (40f + Mathf.absin(10f, 5f)) * Math.min(productionEfficiency, 2f) * size, Color.scarlet, 0.4f);
        }

        @Override
        public void onProximityAdded(){
            super.onProximityAdded();

            sum = sumAttribute(attribute, tile.x, tile.y);
        }
    }
}
