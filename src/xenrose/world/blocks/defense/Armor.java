package xenrose.world.blocks.defense;

import arc.Core;
import arc.graphics.Blending;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.Time;
import mindustry.entities.TargetPriority;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.meta.BlockGroup;

import static mindustry.Vars.state;
import static mindustry.Vars.tilesize;

public class Armor extends Block{

    public Armor armored;

    public Armor(String name) {
        super(name);
    }

    public boolean isBanned(){
        return state.rules.isBanned(this);
    }

    public boolean unlockedNow(){
        return unlocked() || !state.isCampaign();
    }

    public class ArmorBuild extends Building{
    }
}
