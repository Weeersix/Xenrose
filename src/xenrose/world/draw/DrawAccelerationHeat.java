package xenrose.world.draw;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.draw.DrawBlock;
import mindustry.world.draw.DrawTurret;
import xenrose.world.blocks.defense.turret.AccelerationTurret;

public class DrawAccelerationHeat extends DrawTurret {
    public String suffix = "-accelheat";
    public float turretLayer = Layer.turret, shadowLayer = Layer.turret - 0.5f, heatLayer = Layer.turretHeat;

    public TextureRegion accelerationHeat;

    public DrawAccelerationHeat(String suffix){
        this.suffix = suffix;
    }

    public void DrawAccelerationHeat(AccelerationTurret block, AccelerationTurret.AccelerationTurretBuild build){
        if(build.heat <= 0.00001f || !accelerationHeat.found()) return;

        Drawf.additive(accelerationHeat, block.heatColor.write(Tmp.c1).a(build.heat), build.x + build.recoilOffset.x, build.y + build.recoilOffset.y, build.drawrot(), heatLayer);
    }


    @Override
    public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list){
    }

    @Override
    public void load(Block block){
        if(!(block instanceof AccelerationTurret)) throw new ClassCastException("This drawer can only be used on turrets.");

        preview = Core.atlas.find(block.name + "-preview", block.region);
        outline = Core.atlas.find(block.name + "-outline");
        liquid = Core.atlas.find(block.name + "-liquid");
        top = Core.atlas.find(block.name + "-top");
        heat = Core.atlas.find(block.name + "-heat");
        base = Core.atlas.find(block.name + "-base");
        accelerationHeat = Core.atlas.find(block.name + "-accelheat");

        for(var part : parts){
            part.turretShading = true;
            part.load(block.name);
        }

        //TODO test this for mods, e.g. exotic
        if(!base.found() && block.minfo.mod != null) base = Core.atlas.find(block.minfo.mod.name + "-" + basePrefix + "block-" + block.size);
        if(!base.found()) base = Core.atlas.find(basePrefix + "block-" + block.size);
    }
}
