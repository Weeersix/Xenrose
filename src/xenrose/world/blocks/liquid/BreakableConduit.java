package xenrose.world.blocks.liquid;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.util.Eachable;
import arc.util.Nullable;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.entities.Puddles;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.type.Liquid;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.ChainedBuilding;
import mindustry.world.blocks.liquid.Conduit;

import static mindustry.Vars.renderer;
import static mindustry.Vars.tilesize;

public class BreakableConduit extends Conduit {
    static final float[][] rotateOffsets = new float[][]{{0.75F, 0.75F}, {-0.75F, 0.75F}, {-0.75F, -0.75F}, {0.75F, -0.75F}};

    public float liquidLimit = 10;
    public TextureRegion[][] regions;

    public BreakableConduit(String name) {
        super(name);
    }

    @Override
    public void load() {
        super.load();
        regions = new TextureRegion[7][4];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                regions[i][j] = Core.atlas.find(name + "-top-" + i + "-" + j);
            }
        }
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        int[] bits = getTiling(plan, list);

        if (bits == null) return;

        Draw.scl(bits[1], bits[2]);
        Draw.color(botColor);
        Draw.alpha(0.5f);
        Draw.rect(botRegions[bits[0]], plan.drawx(), plan.drawy(), plan.rotation * 90);
        Draw.reset();

        int blendbits2 = bits[0];
        if (blendbits2 == 1 && (bits[1] != 1 || bits[2] != 1)) { //Flips corner conduits
            blendbits2 = 5;
        }
        if (blendbits2 == 2 && (bits[1] != 1 || bits[2] != 1)) { //Flips T junction conduits
            blendbits2 = 6;
        }
        Draw.rect(regions[blendbits2][plan.rotation], plan.drawx(), plan.drawy());
    }

    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{Core.atlas.find(name + "-bottom"), regions[0][0]};
    }

    public class BreakableConduitBuild extends ConduitBuild{
        public int damage = 1;

        @Override
        public void draw() {
            int r = this.rotation;

            Draw.z(Layer.blockUnder);
            for (int i = 0; i < 4; i++) {
                if ((blending & (1 << i)) != 0) {
                    int dir = r - i;
                    drawBottomAt(x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, 0, i == 0 ? r : dir, i != 0 ? SliceMode.bottom : SliceMode.top);

                    float rot = i == 0 ? r * 90 : (dir) * 90;
                    if (Mathf.mod(dir, 4) == 1 || Mathf.mod(dir, 4) == 2) Draw.yscl = -1;
                    Draw.rect(sliced(regions[0][0], i != 0 ? SliceMode.bottom : SliceMode.top), x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, rot);
                }
            }

            Draw.z(Layer.block);

            Draw.scl(xscl, yscl);
            drawBottomAt(x, y, blendbits, r, SliceMode.none);
            Draw.reset();

            int blendbits2 = blendbits;
            if (blendbits2 == 1 && (xscl != 1 || yscl != 1)) { //Flips corner conveyors
                blendbits2 = 5;
            }
            if (blendbits2 == 2 && (xscl != 1 || yscl != 1)) { //Flips T junction conveyors
                blendbits2 = 6;
            }
            Draw.rect(regions[blendbits2][rotation], x, y);

            if (r == 1 || r == 2) Draw.yscl = -1;
            else Draw.yscl = 1;
            if (capped && capRegion.found()) Draw.rect(capRegion, x, y, rotdeg());
            if (Mathf.mod(r + 2, 4) == 1 || Mathf.mod(r + 2, 4) == 2) Draw.yscl = -1;
            else Draw.yscl = 1;
            if (backCapped && capRegion.found()) Draw.rect(capRegion, x, y, rotdeg() + 180);
        }

        protected void drawBottomAt(float x, float y, int bits, int rotation, SliceMode slice) {
            float angle = rotation * 90f;
            Draw.color(botColor);
            Draw.rect(sliced(botRegions[bits], slice), x, y, angle);

            int offset = yscl == -1 ? 3 : 0;

            int frame = liquids.current().getAnimationFrame();
            int gas = liquids.current().gas ? 1 : 0;
            float ox = 0f, oy = 0f;
            int wrapRot = (rotation + offset) % 4;
            TextureRegion liquidr = bits == 1 && padCorners ? rotateRegions[wrapRot][gas][frame] : renderer.fluidFrames[gas][frame];

            if (bits == 1 && padCorners) {
                ox = rotateOffsets[wrapRot][0];
                oy = rotateOffsets[wrapRot][1];
            }

            Drawf.liquid(sliced(liquidr, slice), x + ox, y + oy, smoothLiquid, liquids.current().color.write(Tmp.c1).a(1f));
        }

        public void updateTile() {
            smoothLiquid = Mathf.lerpDelta(smoothLiquid, liquids.currentAmount() / liquidCapacity, 0.05f);

            if(liquids.currentAmount() > 0.0001f && timer(timerFlow, 1)){
                moveLiquidForward(leaks, liquids.current());
                noSleep();
            }else{
                sleep();
            }

            if (liquids.currentAmount() > liquidLimit) {
                damage(liquids.currentAmount()/30f * delta());
                if (Mathf.chanceDelta(0.05)) {
                    Fx.generatespark.at(x, y);
                }
            }
        }
    }
}
