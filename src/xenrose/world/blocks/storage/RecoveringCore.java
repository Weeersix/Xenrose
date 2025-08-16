package xenrose.world.blocks.storage;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Interp;
import arc.math.Mathf;
import arc.struct.EnumSet;
import arc.util.Scaling;
import arc.util.Time;
import mindustry.content.Fx;
import mindustry.entities.TargetPriority;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.ui.Styles;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.Env;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

import static mindustry.Vars.ui;

public class RecoveringCore extends CoreBlock {
    public float healReload = 95;
    public float healAmount = 5;
    public float healPercent = 4f;
    public TextureRegion glowRegion;

    public float glowMag = 0.8f, glowScl = 10f;

    public RecoveringCore(String name){
        super(name);
        solid = true;
        update = true;
        hasItems = true;
        priority = TargetPriority.core;
        flags = EnumSet.of(BlockFlag.core);
        unitCapModifier = 10;
        drawDisabled = false;
        canOverdrive = false;
        commandable = true;
        envEnabled |= Env.space;
        replaceable = false;
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.remove(Stat.buildTime);

        stats.add(Stat.repairSpeed, (int)(health * healPercent / 100 * 60 / healReload), StatUnit.perSecond);
    }

    @Override
    public void load(){
        super.load();
        glowRegion = Core.atlas.find(name + "-glow");
        uiIcon = fullIcon = Core.atlas.find(name + "-full");
    }

    public class RecoveringCoreBuild extends CoreBuild{
        public float charge = 0;

        @Override
        public void draw(){
            if(thrusterTime > 0){
                float frame = thrusterTime;

                Draw.alpha(1f);
                drawThrusters(frame);
                Draw.rect(block.region, x, y);
                Draw.alpha(Interp.pow4In.apply(frame));
                drawThrusters(frame);
                Draw.reset();

                drawTeamTop();
            }else{
                super.draw();
            }
            super.draw();
            Drawf.additive(glowRegion, team.color,1f - glowMag + Mathf.absin(glowScl, glowMag), x, y, 0f, Layer.blockAdditive);
        }

        @Override
        public void updateTile(){
            iframes -= Time.delta;
            thrusterTime -= Time.delta/90f;

            boolean canHeal = !checkSuppression();

            charge += Time.delta;

            if(charge >= healReload && canHeal && health() < maxHealth()){
                charge = 0f;

                heal((maxHealth() / 2) * (healPercent) / 100f);
                recentlyHealed();
                Fx.healBlockFull.at(x, y, block.size, team.color, block);
            }
        }
    }
}
