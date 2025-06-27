package xenrose.world.blocks.liquid;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.core.Renderer;
import mindustry.gen.Building;
import mindustry.graphics.Layer;
import mindustry.world.Tile;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Env;

import static mindustry.Vars.tilesize;
import static mindustry.Vars.world;
import static mindustry.world.blocks.liquid.LiquidBlock.drawTiledFrames;

public class BreakableLiquidBridge extends LiquidBridge {
    public float liquidLimit = 10;
    public float liquidPadding = 0f;

    public BreakableLiquidBridge(String name) {
        super(name);
        hasItems = false;
        hasLiquids = true;
        outputsLiquid = true;
        canOverdrive = false;
        group = BlockGroup.liquids;
        envEnabled = Env.any;
    }

    public class LiquidBridgeBuild extends ItemBridgeBuild{

        @Override
        public void updateTransport(Building other){
            if(warmup >= 0.25f){
                moved |= moveLiquid(other, liquids.current()) > 0.05f;
            }
        }

        @Override
        public void updateTile(){
            if(timer(timerCheckMoved, 30f)){
                wasMoved = moved;
                moved = false;
            }

            //smooth out animation, so it doesn't stop/start immediately
            timeSpeed = Mathf.approachDelta(timeSpeed, wasMoved ? 1f : 0f, 1f / 60f);

            time += timeSpeed * delta();

            checkIncoming();

            Tile other = world.tile(link);
            if(!linkValid(tile, other)){
                doDump();
                warmup = 0f;
            }else{
                var inc = ((ItemBridgeBuild)other.build).incoming;
                int pos = tile.pos();
                if(!inc.contains(pos)){
                    inc.add(pos);
                }

                warmup = Mathf.approachDelta(warmup, efficiency, 1f / 30f);
                updateTransport(other.build);
            }

            if (liquids.currentAmount() > liquidLimit) {
                damageContinuous(liquids.currentAmount()/30f);
                if (Mathf.chanceDelta(0.05)) {
                    Fx.generatespark.at(x, y);
                }
            }
        }

        @Override
        public void doDump(){
            dumpLiquid(liquids.current(), 1f);
        }
    }
}
