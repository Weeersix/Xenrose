package xenrose.world.blocks.defense;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.util.Eachable;
import mindustry.content.Fx;
import mindustry.entities.units.BuildPlan;
import mindustry.world.blocks.defense.Wall;

public class EnemyArmoredWall extends Wall {
    public TextureRegion armorRegion;

    public EnemyArmoredWall(String name){
        super(name);
        update = true;
    }

    @Override
    public void load() {
        super.load();
        armorRegion = Core.atlas.find(name + "-armored");
    }

    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{armorRegion, region};
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
    }

    public class EnemyArmoredWallBuild extends WallBuild{
        public boolean addArmor = true;

        @Override
        public void updateTile() {
            if(addArmor && health() <= (maxHealth() / 2.2f)){
                addArmor = false;
                Fx.dooropen.at(x, y, block.size, block);
            }
        }

        @Override
        public void draw(){
            super.draw();

            if(addArmor) {
                Draw.rect(armorRegion, x, y);
            }
        }
    }
}
