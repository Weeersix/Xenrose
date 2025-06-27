package xenrose.world.blocks.liquid;

import arc.Core;
import arc.func.Func;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import mindustry.content.Fx;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.logic.LAccess;
import mindustry.type.Liquid;
import mindustry.ui.Bar;
import mindustry.world.Block;
import mindustry.world.blocks.liquid.LiquidJunction;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.consumers.ConsumeLiquids;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

import static mindustry.world.blocks.liquid.LiquidBlock.drawTiledFrames;

public class BreakableLiquidJunction extends Block {
    public TextureRegion bottomRegion;
    public float liquidPadding = 0f;

    public BreakableLiquidJunction(String name) {
        super(name);
    }

    @Override
    public void load() {
        super.load();
        bottomRegion = Core.atlas.find(name + "-bottom");
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{bottomRegion, region};
    }

    public class BreakableLiquidJunctionBuild extends Building{

        @Override
        public void draw(){
            Draw.rect(bottomRegion, x, y);

            if(liquids.currentAmount() >= 0.01){
                drawTiledFrames(size, x, y, liquidPadding, liquids.current(), liquids.currentAmount() / liquidCapacity);
            }

            Draw.rect(region, x, y);
        }
        @Override
        public void updateTile(){
            dumpLiquid(liquids.current());

            if (liquids.currentAmount() > 2.5f) {
                damageContinuous(-1f);
                if (Mathf.chanceDelta(0.05)) {
                    Fx.generatespark.at(x, y);
                }
            }
        }

        @Override
        public Building getLiquidDestination(Building source, Liquid liquid){
            if(!enabled) return this;

            int dir = (source.relativeTo(tile.x, tile.y) + 4) % 4;
            Building next = nearby(dir);
            if(next == null || (!next.acceptLiquid(this, liquid) && !(next.block instanceof LiquidJunction))){
                return this;
            }
            return next.getLiquidDestination(this, liquid);
        }
    }
}
