package xenrose.world.blocks.production;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.entities.units.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.DrawBlock;
import mindustry.world.draw.DrawDefault;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class EnergyDrill extends Block {
    public TextureRegion armBaseRegion, joinRegion, armRegion, baseRegion, arm;
    public int range = 60;
    public float radius = 100;
    public @Nullable Item blockedItem;
    public @Nullable Seq<Item> blockedItems;
    public float drillTime = 200f;
    public float optionalBoostIntensity = 2.5f;
    public int tier = 1;
    public int armSpeed = 4;

    public DrawBlock drawer = new DrawDefault();

    public ObjectFloatMap<Item> drillMultipliers = new ObjectFloatMap<>();

    public EnergyDrill(String name) {
        super(name);
        update = true;
        solid = true;
        rotate = true;
        hasItems = true;
        consumesPower = true;

        envEnabled |= Env.any;
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{baseRegion, armBaseRegion, joinRegion, armRegion};
    }

    @Override
    public void load() {
        super.load();
        baseRegion = Core.atlas.find(name + "-base");
        armBaseRegion = Core.atlas.find(name + "-arm-base");
        joinRegion = Core.atlas.find(name + "-arm-join");
        armRegion = Core.atlas.find(name + "-arm");
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("drillspeed", (EnergyDrillBuild e) ->
                new Bar(() -> Core.bundle.format("bar.drillspeed", Strings.fixed(e.lastDrillSpeed * 60, 2)), () -> Pal.ammo, () -> e.warmup));
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.drillTier, StatValues.drillables(drillTime, 0f, size, drillMultipliers, b ->
                (b instanceof Floor f && f.wallOre && f.itemDrop != null && f.itemDrop.hardness <= tier && (blockedItems == null || !blockedItems.contains(f.itemDrop))) ||
                        (b instanceof StaticWall w && w.itemDrop != null && w.itemDrop.hardness <= tier && (blockedItems == null || !blockedItems.contains(w.itemDrop)))
        ));

        stats.add(Stat.drillSpeed, 60f / drillTime * size, StatUnit.itemsSecond);

        if(optionalBoostIntensity != 1 && findConsumer(f -> f instanceof ConsumeLiquidBase && f.booster) instanceof ConsumeLiquidBase consBase){
            stats.remove(Stat.booster);
            stats.add(Stat.booster,
                    StatValues.speedBoosters("{0}" + StatUnit.timesSpeed.localized(),
                            consBase.amount, optionalBoostIntensity, false,
                            l -> (consumesLiquid(l) && (findConsumer(f -> f instanceof ConsumeLiquid).booster || ((ConsumeLiquid)findConsumer(f -> f instanceof ConsumeLiquid)).liquid != l)))
            );
        }
    }


    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        Item item = null, invalidItem = null;
        boolean multiple = false;
        int count = 0;

        for(int i = 0; i < size; i++){
            nearbySide(x, y, rotation, i, Tmp.p1);

            int j = 0;
            Item found = null;
            for(; j < range; j++){
                int rx = Tmp.p1.x + Geometry.d4x(rotation)*j, ry = Tmp.p1.y + Geometry.d4y(rotation)*j;
                Tile other = world.tile(rx, ry);
                if(other != null && other.solid()){
                    Item drop = other.wallDrop();
                    if(drop != null){
                        if(drop.hardness <= tier && (blockedItems == null || !blockedItems.contains(drop))){
                            found = drop;
                            count++;
                        }else{
                            invalidItem = drop;
                        }
                    }
                    break;
                }
            }

            if(found != null){
                //check if multiple items will be drilled
                if(item != found && item != null){
                    multiple = true;
                }
                item = found;
            }

            int len = Math.min(j, range - 1);
            Drawf.dashLine(found == null ? Pal.remove : Pal.placing,
                    Tmp.p1.x * tilesize,
                    Tmp.p1.y *tilesize,
                    (Tmp.p1.x + Geometry.d4x(rotation)*len) * tilesize,
                    (Tmp.p1.y + Geometry.d4y(rotation)*len) * tilesize
            );
        }

        if(item != null){
            float width = drawPlaceText(Core.bundle.formatFloat("bar.drillspeed", 60f / getDrillTime(item) * count, 2), x, y, valid);
            if(!multiple){
                float dx = x * tilesize + offset - width/2f - 4f, dy = y * tilesize + offset + size * tilesize / 2f + 5, s = iconSmall / 4f;
                Draw.mixcol(Color.darkGray, 1f);
                Draw.rect(item.fullIcon, dx, dy - 1, s, s);
                Draw.reset();
                Draw.rect(item.fullIcon, dx, dy, s, s);
            }
        }else if(invalidItem != null){
            drawPlaceText(Core.bundle.get("bar.drilltierreq"), x, y, false);
        }
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        Draw.rect(baseRegion, plan.drawx(), plan.drawy());
        Draw.rect(armBaseRegion, plan.drawx(), plan.drawy(), plan.rotation * 90);
        Draw.rect(armRegion, plan.drawx(), plan.drawy(), plan.rotation * 90);
    }

    @Override
    public void init(){
        updateClipRadius((range + 2) * tilesize);
        super.init();
        if(blockedItems == null && blockedItem != null){
            blockedItems = Seq.with(blockedItem);
        }
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        for(int i = 0; i < size; i++){
            nearbySide(tile.x, tile.y, 3, i, Tmp.p1);
            for(int j = 0; j < range; j++){
                Tile other = world.tile(Tmp.p1.x + Geometry.d4x(rotation)*j, Tmp.p1.y + Geometry.d4y(rotation)*j);
                if(other != null && other.solid()){
                    Item drop = other.wallDrop();
                    if(drop != null && drop.hardness <= tier && (blockedItems == null || !blockedItems.contains(drop))){
                        return true;
                    }
                    break;
                }
            }
        }

        return false;
    }

    @Override
    public boolean outputsItems(){
        return true;
    }

    @Override
    public boolean rotatedOutput(int x, int y){
        return false;
    }

    public float getDrillTime(Item item){
        return drillTime / drillMultipliers.get(item, 1f);
    }

    public class EnergyDrillBuild extends Building{
        public float timeDrilled;
        float progress = 0f;
        public float lastDrillSpeed;
        public float warmup, boostWarmup;
        public Tile[] facing = new Tile[size];
        public int facingAmount;
        public float time;
        public @Nullable Item lastItem;
        public Point2[] arm = new Point2[size];

        @Override
        public void updateTile() {
            super.updateTile();

            warmup = Mathf.approachDelta(warmup, Mathf.num(efficiency > 0), 1f / 60f);


            float multiplier = Mathf.lerp(1f, optionalBoostIntensity, optionalEfficiency);
            float drillTime = getDrillTime(lastItem);
            boostWarmup = Mathf.lerpDelta(boostWarmup, optionalEfficiency, 0.1f);
            lastDrillSpeed = (facingAmount * multiplier * timeScale) / drillTime;

            time += edelta() * multiplier;

            if (time >= drillTime) {
                for (Tile tile : facing) {
                    Item drop = tile == null ? null : tile.wallDrop();
                    if (items.total() < itemCapacity && drop != null) {
                        items.add(drop, 1);
                        produced(drop);
                    }
                }
                time %= drillTime;
            }

            timeDrilled += warmup * delta();

            if (timer(timerDump, dumpTime / timeScale)) {
                dump();
            }
        }

        @Override
        public void draw() {
            Draw.rect(baseRegion, x, y);

            float armLength = 5f;
            float angle = rotdeg();

            float inX = x + Angles.trnsx(angle, armLength);
            float inY = y + Angles.trnsy(angle, armLength);
            float outX = x - Angles.trnsx(angle, armLength);
            float outY = y - Angles.trnsy(angle, armLength);

            float drawX = Mathf.lerp(inX, outX, progress);
            float drawY = Mathf.lerp(inY, outY, progress);

            Draw.rect(armRegion, timeDrilled * armSpeed + drawX, drawY, angle);
        }
    }
}
