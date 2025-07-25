package xenrose.world.blocks.enviroments;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.math.geom.Point2;
import arc.util.Time;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

import static mindustry.Vars.tilesize;

public class Pit extends Floor {
    public static final Point2[] offsets = {
        new Point2(1, 1),
        new Point2(1, -1),
        new Point2(-1, 1),
        new Point2(-1, -1),
        new Point2(1, 2),
        new Point2(2, 1),
        new Point2(2, -1),
        new Point2(1, -2),
        new Point2(-1, -2),
        new Point2(-2, -1),
        new Point2(-2, 1),
        new Point2(-1, 2),
        new Point2(2, 2),
        new Point2(2, -2),
        new Point2(-2, -2),
        new Point2(-2, 2),
        new Point2(1, 3),
        new Point2(-1, 3),
        new Point2(3, 1),
        new Point2(3, -1),
        new Point2(1, -3),
        new Point2(-1, -3),
        new Point2(-3, 1),
        new Point2(-3, -1),
        new Point2(3, 2),
        new Point2(2, 3),
        new Point2(-2, 3),
        new Point2(-3, 2),
        new Point2(3, -2),
        new Point2(2, -3),
        new Point2(-3, -2),
        new Point2(-2, -3),
        new Point2(3, 3),
        new Point2(-3, 3),
        new Point2(3, -3),
        new Point2(-3, -3),
    };

    public Block parent = Blocks.air;
    public Effect effect = Fx.none;
    public Color effectColor = Color.white;
    public float effectSpacing = 15f;

    static{
        for(var p : offsets){
            p.sub(1, 1);
        }
    }

    public Pit(String name) {
        super(name);
    }

    @Override
    public void drawBase(Tile tile){
        parent.drawBase(tile);

        if(checkAdjacent(tile)){
            Mathf.rand.setSeed(tile.pos());
            Draw.rect(variantRegions[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variantRegions.length - 3))], tile.worldx() - tilesize -16, tile.worldy() - tilesize -16);
        }
    }

    @Override
    public boolean updateRender(Tile tile){
        return checkAdjacent(tile);
    }

    @Override
    public void renderUpdate(UpdateRenderState state){
        if(state.tile.block() == Blocks.air && (state.data += Time.delta) >= effectSpacing){
            effect.at(state.tile.x * tilesize - tilesize -16, state.tile.y * tilesize - tilesize -16, effectColor);
            state.data = 0f;
        }
    }

    public boolean checkAdjacent(Tile tile){
        for(var point : offsets){
            Tile other = Vars.world.tile(tile.x + point.x, tile.y + point.y);
            if(other == null || other.floor() != this){
                return false;
            }
        }
        return true;
    }
}
