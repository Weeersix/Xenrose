package xenrose.world.blocks.defense.turret;

import arc.Core;
import arc.graphics.Color;
import arc.math.Mathf;
import mindustry.content.Fx;
import mindustry.graphics.Pal;
import mindustry.type.Liquid;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.turrets.ItemTurret;

public class AccelerationTurret extends ItemTurret {
    public Color accelHeatColor = Color.valueOf("ce7746");
    public float glowMag = 0.6f, glowScl = 8f;
    public float maxHeat = 36;

    public AccelerationTurret(String name){
        super(name);
        hasItems = true;
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("ammo", (AccelerationTurretBuild entity) ->
                new Bar("stat.ammo", Pal.ammo, () -> (float)entity.totalAmmo / maxAmmo)
        );
        addBar("heating", (AccelerationTurretBuild e) ->
                new Bar(() -> Core.bundle.format("bar.heatingamount", (int)(e.accelHeat / maxHeat) * 100), () -> Pal.lightOrange, () -> (e.accelHeat / maxHeat) * 3.8f)
        );
    }

    public class AccelerationTurretBuild extends ItemTurretBuild {
        public float accelHeat;
        public float liquidCurrent;

        @Override
        public void updateTile(){
            Liquid liquid = liquids.current();
            unit.ammo((float)unit.type().ammoCapacity * totalAmmo / maxAmmo);

            if(liquids.currentAmount() >= 0.1f && liquid.temperature > 3f) {
                accelHeat = liquid.temperature;
                liquidCurrent = liquids.currentAmount();
                damageContinuous(liquids.currentAmount()/(300 - liquid.temperature * 3));
                if(Mathf.chanceDelta(0.05)) {
                    Fx.fire.at(x, y);
                }
            }
            super.updateTile();
        }
    }
}
