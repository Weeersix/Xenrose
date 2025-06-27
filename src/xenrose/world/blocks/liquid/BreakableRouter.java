package xenrose.world.blocks.liquid;

import arc.math.Mathf;
import mindustry.content.Fx;
import mindustry.world.blocks.liquid.LiquidRouter;

public class BreakableRouter extends LiquidRouter {
    public float liquidLimit = 8;

    public BreakableRouter(String name) {
        super(name);
        solid = true;
        noUpdateDisabled = true;
        canOverdrive = false;
        floating = true;
    }

    public class BreakableRouterBuild extends LiquidRouterBuild {

        @Override
        public void updateTile(){
            dumpLiquid(liquids.current());

            if (liquids.currentAmount() > liquidLimit) {
                damageContinuous(liquids.currentAmount()/3f);
                if (Mathf.chanceDelta(0.05)) {
                    Fx.generatespark.at(x, y);
                }
            }
        }
    }
}
