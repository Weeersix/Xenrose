package xenrose.world.blocks.power;

import arc.Core;
import arc.func.Cons;
import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.struct.Seq;
import arc.util.Eachable;
import arc.util.Tmp;
import mindustry.Vars;
import mindustry.core.*;
import mindustry.entities.TargetPriority;
import mindustry.entities.units.BuildPlan;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.graphics.*;
import mindustry.input.Placement;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.power.*;
import mindustry.world.meta.BlockStatus;
import mindustry.world.meta.Env;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

import java.util.Arrays;

import static mindustry.Vars.*;

public class CableNode extends BeamNode {
    //Stole code from Psammos Mod

    public CableNode(String name){
        super(name);
        laserColor1 = Color.valueOf("cebaa5");
        laserColor2 = Color.valueOf("9c9082");
    }

    @Override
    public void load(){
        super.load();
        laser = Core.atlas.find(name+"-cable");
        laserEnd = Core.atlas.find(name+"-cable-end");
    }

    public class CableNodeBuild extends BeamNodeBuild {

        @Override
        public void draw() {
            if (this.block.variants != 0 && this.block.variantRegions != null) {
                Draw.rect(this.block.variantRegions[Mathf.randomSeed(this.tile.pos(), 0, Math.max(0, this.block.variantRegions.length - 1))], this.x, this.y, this.drawrot());
            } else {
                Draw.rect(this.block.region, this.x, this.y, this.drawrot());
            }

            this.drawTeamTop();

            for(int i = 0; i < 4; i ++){

                if(dests[i] != null && links[i].wasVisible && (!(links[i].block instanceof CableNode node) ||
                        (links[i].tileX() != tileX() && links[i].tileY() != tileY()) ||
                        (links[i].id > id && range >= node.range) || range > node.range)){

                    int dst = Math.max(Math.abs(dests[i].x - tile.x),  Math.abs(dests[i].y - tile.y));
                    if(dst > 1 + size/2){
                        Draw.reset();
                        Draw.color(laserColor1, laserColor2, 1f - power.graph.getSatisfaction());
                        Draw.alpha(Renderer.laserOpacity);
                        Draw.z(Layer.blockOver + 0.1f);

                        if (i == 1 || i == 2){
                            Draw.yscl = -1;
                        }

                        var point = Geometry.d4[i];
                        float off = (size - 1) / 2;
                        float startX =  x + off * point.x * Vars.tilesize;
                        float startY =  y + off * point.y * Vars.tilesize;
                        float endX = dests[i].worldx();
                        float endY = dests[i].worldy();

                        Draw.rect(laserEnd, startX, startY, i * 90);

                        for(float ix = Math.min(startX, endX) + Vars.tilesize; ix<Math.max(startX, endX); ix += Vars.tilesize){
                            Draw.rect(laser, ix, startY, i * 90);
                        }
                        for(float iy = Math.min(startY, endY) + Vars.tilesize; iy<Math.max(startY, endY); iy += Vars.tilesize){
                            Draw.rect(laser, startX, iy, i * 90);
                        }

                        Draw.yscl *= -1;
                        Draw.rect(laserEnd, endX, endY, i * 90 + 180);
                    }
                }
            }
        }
    }
}