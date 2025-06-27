package xenrose.world.blocks.power;

import arc.Core;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.Mathf;
import arc.math.geom.*;
import arc.scene.ui.layout.Table;
import arc.struct.EnumSet;
import arc.util.*;
import mindustry.*;
import mindustry.core.Renderer;
import mindustry.entities.*;
import mindustry.entities.units.BuildPlan;
import mindustry.game.Team;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.Styles;
import mindustry.world.Block;
import mindustry.world.Build;
import mindustry.world.Tile;
import mindustry.world.blocks.power.BeamNode;
import mindustry.world.blocks.power.PowerGraph;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.meta.*;
import xenrose.world.meta.*;

import static mindustry.Vars.*;
import static mindustry.entities.part.DrawPart.PartProgress.warmup;
import static mindustry.gen.Icon.link;
import static mindustry.logic.LAccess.color;

public class PowerUpgrader extends Block {

    public int upgrade = 2;
    public TextureRegion topRegion;

    public PowerUpgrader(String name) {
        super(name);
        rotate = true;
        rotateDraw = false;
        update = true;
        solid = true;
        drawArrow = true;
        hasPower = true;

        envEnabled |= Env.space;
        flags = EnumSet.of(BlockFlag.drill);
    }

    @Override
    public void load() {
        super.load();
        topRegion = Core.atlas.find(name + "-top");
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(XenStat.powerTier, upgrade);
    }

    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{region, topRegion};
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        Draw.rect(region, plan.drawx(), plan.drawy());
        Draw.rect(topRegion, plan.drawx(), plan.drawy(), plan.rotation * 90);
    }

    public class PowerUpgraderBuild extends Building {
        public int lastChange = -2;

        @Override
        public void draw() {
            Draw.rect(block.region, x, y);
            Draw.rect(topRegion, x, y, rotdeg());
        }

        public float updater(){
            return upgrade;
        }

        @Override
        public void updateTile() {
            if (lastChange != world.tileChanges) {
                lastChange = world.tileChanges;
            }
        }
    }
}