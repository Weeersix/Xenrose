package xenrose.world.blocks.liquid;

import arc.math.Mathf;
import arc.util.Time;
import mindustry.content.Fx;
import mindustry.type.Liquid;
import mindustry.world.blocks.production.Pump;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.Env;

public class BreackablePump extends Pump {

    public BreackablePump(String name){
        super(name);
        group = BlockGroup.liquids;
        floating = true;
        envEnabled = Env.terrestrial;
    }

    public class BreackablePumpBuild extends PumpBuild {

        @Override
        public void updateTile(){
            Liquid liquid = liquids.current();
            if(efficiency > 0 && liquidDrop != null){
                float maxPump = Math.min(liquidCapacity - liquids.get(liquidDrop), amount * pumpAmount * edelta());
                liquids.add(liquidDrop, maxPump);

                //does nothing for most pumps, as those do not require items.
                if((consTimer += delta()) >= consumeTime){
                    consume();
                    consTimer %= 1f;
                }

                warmup = Mathf.approachDelta(warmup, maxPump > 0.001f ? 1f : 0f, warmupSpeed);
            }else{
                warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
            }

            totalProgress += warmup * Time.delta;

            if(liquidDrop != null){
                dumpLiquid(liquidDrop);
            }
            if (liquids.currentAmount() > 0.01 && liquid.temperature >= 5.8f) {
                damage((liquids.currentAmount()/30f + liquid.temperature / 35f) * delta());
                if (Mathf.chanceDelta(0.05)) {
                    Fx.fire.at(x, y);
                }
            }
        }
    }
}
