package xenrose.world.blocks.defense.turret;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import mindustry.content.Fx;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Liquid;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.turrets.ItemTurret;

public class AccelerationTurret extends ItemTurret {
    public TextureRegion accelHeatRegion;
    public Color accelHeatColor = Color.valueOf("ce7746");

    public float glowMag = 0.2f, glowScl = 10f;
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
                new Bar(() -> Core.bundle.format("bar.heatingamount", (int)(e.accelHeat / maxHeat) * 100), () -> Pal.lightOrange, () -> (e.accelHeat / maxHeat) * 3f)
        );
    }

    @Override
    public void load() {
        super.load();
        accelHeatRegion = Core.atlas.find(name + "-acheat");
    }

    public class AccelerationTurretBuild extends ItemTurretBuild {
        public float accelHeat;
        public float liquidCurrent;

        @Override
        public void draw() {
            drawer.draw(this);

            Draw.reset();

            if(accelHeat > 3 && liquidCurrent > 1f) {
                Drawf.additive(accelHeatRegion, accelHeatColor, (1f - glowMag + Mathf.absin(glowScl, glowMag)), x, y, (buildRotation() * rotation), Layer.turretHeat);
            }
        }

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
