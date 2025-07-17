package xenrose.world.draw;

import arc.Core;
import arc.func.Floatf;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.struct.Seq;
import arc.util.Eachable;
import arc.util.Nullable;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.draw.DrawBlock;
import xenrose.graphics.Drawj;
import xenrose.graphics.Rotor;

public class DrawRotor extends DrawBlock {
    public TextureRegion rotorRegion;
    public float rotateSpeed, x, y, rotation;
    public float layer = -1;
    public String suffix = "-rotor";
    public boolean drawPlan = true;
    public boolean buildingRotate = false;
    public Seq<Rotor> rotors = new Seq<>();

    public @Nullable Floatf<Building> rotationOverride;

    public DrawRotor(Rotor... rotors){
        this.rotors.add(rotors);
    }

    public DrawRotor(Floatf<Building> rotationOverride, Rotor... rotors) {
        this.rotationOverride = rotationOverride;
        this.rotors.add(rotors);
    }

    public DrawRotor(Floatf<Building> rotationOverride, float layer, Rotor... rotors) {
        this.rotationOverride = rotationOverride;
        this.layer = layer;
        this.rotors.add(rotors);
    }

    @Override
    public void draw(Building build){
        float z = Draw.z();
        if (layer > 0) Draw.z(layer);
        rotors.each(rotor -> {
            float rot = build.block.rotate ? ((build.rotdeg() + 90f + rotor.rotation) % 180f - 90f) : rotor.rotation;
            float spin = (rotationOverride != null ? rotationOverride.get(build) : build.totalProgress()) * rotor.spinScale;
            float dx = build.x + Angles.trnsx(build.block.rotate ? build.rotdeg() : 0, rotor.x, rotor.y);
            float dy = build.y + Angles.trnsy(build.block.rotate ? build.rotdeg() : 0, rotor.x, rotor.y);

            Drawj.palette(rotor.palLight, rotor.palMedium, rotor.palDark);
            if (rotor.hasSprites) {
                Drawj.regionCircular(rotor.regions, dx, dy, rotor.width, rotor.height, spin, rot, rotor.circular);
            }
            Drawj.palette();
        });
        Draw.reset();
        Draw.z(z);
    }

    @Override
    public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list) {
        rotors.each(rotor -> rotor.icon, rotor -> Draw.rect(rotor.iconRegion, plan.drawx(), plan.drawy()));
    }

    @Override
    public TextureRegion[] icons(Block block) {
        Seq<Rotor> tmp = rotors.select(b -> b.icon);
        TextureRegion[] out = new TextureRegion[tmp.size];
        for(int i = 0; i < out.length; i++) out[i] = tmp.get(i).iconRegion;
        return out;
    }


    @Override
    public void load(Block block) {
        rotors.each(rotor -> {
            if (rotor.hasSprites) rotor.regions = Core.atlas.find(block.name + rotor.suffix).split(rotor.pixelWidth, rotor.pixelHeight)[0];
            if (rotor.icon) rotor.iconRegion = Core.atlas.find(rotor.iconOverride == null ? block.name + rotor.suffix + "-icon" : rotor.iconOverride);
        });
    }
}
