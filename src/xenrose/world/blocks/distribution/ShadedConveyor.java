package xenrose.world.blocks.distribution;

import arc.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import arc.graphics.g2d.*;
import mindustry.entities.units.*;
import mindustry.graphics.*;
import mindustry.world.blocks.distribution.*;

import static mindustry.Vars.*;

public class ShadedConveyor extends Conveyor {

    public TextureRegion[][] edgeRegions;

    public ShadedConveyor(String name) {
        super(name);
    }

    @Override
    public void load() {
        super.load();
        edgeRegions = new TextureRegion[7][4];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                edgeRegions[i][j] = Core.atlas.find(name + "-edge-" + i + "-" + j);
            }
        }
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        int[] bits = getTiling(plan, list);

        if (bits == null) return;

        TextureRegion region = regions[bits[0]][0];
        Draw.rect(region, plan.drawx(), plan.drawy(), region.width * bits[1] * region.scl(), region.height * bits[2] * region.scl(), plan.rotation * 90);

        int blendbits2 = bits[0];
        if (blendbits2 == 1 && (bits[1] != 1 || bits[2] != 1)) {
            blendbits2 = 5;
        }
        if (blendbits2 == 2 && (bits[1] != 1 || bits[2] != 1)) {
            blendbits2 = 6;
        }
        Draw.rect(edgeRegions[blendbits2][plan.rotation], plan.drawx(), plan.drawy(), region.width * region.scl(), region.height * region.scl());
    }

    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{regions[0][0], edgeRegions[0][0]};
    }

    public class ShadedConveyorBuild extends ConveyorBuild {

        @Override
        public void draw() {
            super.draw();

            Draw.z(Layer.blockUnder);
            for (int i = 0; i < 4; i++) {
                if ((blending & (1 << i)) != 0) {
                    int dir = rotation - i;
                    float rot = i == 0 ? rotation * 90 : (dir) * 90;

                    if (Mathf.mod(dir, 4) == 1 || Mathf.mod(dir, 4) == 2) Draw.yscl = -1;
                    Draw.rect(sliced(edgeRegions[0][0], i != 0 ? SliceMode.bottom : SliceMode.top), x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, rot);
                }
            }
            Draw.yscl = 1;

            Draw.z(Layer.block - 0.2f);
            int blendbits2 = blendbits;
            if (blendbits2 == 1 && (blendsclx != 1 || blendscly != 1)) {
                blendbits2 = 5;
            }
            if (blendbits2 == 2 && (blendsclx != 1 || blendscly != 1)) {
                blendbits2 = 6;
            }
            Draw.rect(edgeRegions[blendbits2][rotation], x, y);
        }
    }
}