package xenrose.graphics;

import mindustry.graphics.Layer;
import mindustry.graphics.MenuRenderer;
import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.gl.*;
import arc.math.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import arc.util.noise.*;
import mindustry.content.*;
import mindustry.type.*;
import mindustry.world.*;
import xenrose.XenContent.*;

import static mindustry.Vars.*;


public class XenMenuRenderer extends MenuRenderer {
    private static final float darkness = 0.3f;
    private final int width = !mobile ? 100 : 60, height = !mobile ? 50 : 40;

    Color warmColor = Color.valueOf("b36d3e");
    Color particleColor = Color.valueOf("f4b866");
    float windSpeed = 2.5f, windAngle = 50f;
    float windx = Mathf.cosDeg(windAngle) * windSpeed, windy = Mathf.sinDeg(windAngle) * windSpeed;
    private int cacheFloor, cacheWall;
    private Camera camera = new Camera();
    private Mat mat = new Mat();
    private FrameBuffer shadows;
    private CacheBatch batch;
    private float time = 0f;

    public XenMenuRenderer(){
        Time.mark();
        generate();
        cache();
        Log.debug("Time to generate menu: @", Time.elapsed());
    }

    private void generate(){
        //suppress tile change events.
        world.setGenerating(true);

        Tiles tiles = world.resize(width, height);
        shadows = new FrameBuffer(width, height);
        int offset = Mathf.random(100000);
        int s1 = offset, s2 = offset + 1, s3 = offset + 2;
        Block[] selected = Structs.random(
                new Block[]{XenBlocks.softDamascusGround, XenBlocks.damascusWall, XenBlocks.crackedDamascusWall},
                new Block[]{XenBlocks.kirmiteStoneFloor, XenBlocks.kirmiteStoneWall},
                new Block[]{XenBlocks.softDamascusGround, XenBlocks.damascusWall},
                new Block[]{XenBlocks.softDamascusGround, XenBlocks.crackedDamascusWall},
                new Block[]{XenBlocks.kirmiteStoneFloor, XenBlocks.kirmiteStoneWall}
        );
        Block[] selected2 = Structs.random(
                new Block[]{XenBlocks.softDamascusGround, XenBlocks.damascusWall, XenBlocks.crackedDamascusWall},
                new Block[]{XenBlocks.kirmiteStoneFloor, XenBlocks.kirmiteStoneWall},
                new Block[]{XenBlocks.softDamascusGround, XenBlocks.damascusWall},
                new Block[]{XenBlocks.softDamascusGround, XenBlocks.crackedDamascusWall},
                new Block[]{XenBlocks.kirmiteStoneFloor, XenBlocks.kirmiteStoneWall}
        );

        double tr1 = Mathf.random(0.65f, 0.85f);
        double tr2 = Mathf.random(0.65f, 0.85f);
        boolean doheat = Mathf.chance(0.25);
        boolean tendrils = Mathf.chance(0.25);
        boolean tech = Mathf.chance(0.25);
        int secSize = 10;

        Block floord = selected[0], walld = selected[1];
        Block floord2 = selected2[0], walld2 = selected2[1];

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Block floor = floord;
                Block ore = Blocks.air;
                Block wall = Blocks.air;

                if(Simplex.noise2d(s1, 3, 0.5, 1/20.0, x, y) > 0.5){
                    wall = walld;
                }

                if(Simplex.noise2d(s3, 3, 0.5, 1/20.0, x, y) > 0.5){
                    floor = floord2;
                    if(wall != Blocks.air){
                        wall = walld2;
                    }
                }

                if(doheat){
                    double heat = Simplex.noise2d(s3, 4, 0.6, 1 / 50.0, x, y + 9999);
                    double base = 0.65;

                    if(heat > base){
                        ore = Blocks.air;
                        wall = Blocks.air;
                        floor = XenBlocks.burnedDamscusFloor;

                        if(heat > base + 0.1){
                            floor = XenBlocks.burnedDamscusFloor;

                            if(heat > base + 0.15){
                                floor = XenBlocks.burnedDamscusGround;
                            }
                        }
                    }
                }

                if(tech){
                    int mx = x % secSize, my = y % secSize;
                    int sclx = x / secSize, scly = y / secSize;
                    if(Simplex.noise2d(s1, 2, 1f / 10f, 0.5f, sclx, scly) > 0.4f && (mx == 0 || my == 0 || mx == secSize - 1 || my == secSize - 1)){
                        floor = XenBlocks.damascusGround;
                        if(Mathf.dst(mx, my, secSize/2, secSize/2) > secSize/2f + 1){
                            floor = XenBlocks.crackedDamascusGround;
                        }


                        if(wall != Blocks.air && Mathf.chance(0.7)){
                            wall = XenBlocks.burnedDamscusFloor;
                        }
                    }
                }

                if(tendrils){
                    if(Ridged.noise2d(1 + offset, x, y, 1f / 17f) > 0f){
                        floor = Mathf.chance(0.2) ? XenBlocks.kirmiteStoneFloor : XenBlocks.kirmiteStoneGround;

                        if(wall != Blocks.air){
                            wall = XenBlocks.kirmiteStoneWall;
                        }
                    }
                }

                Tile tile;
                tiles.set(x, y, (tile = new CachedTile()));
                tile.x = (short)x;
                tile.y = (short)y;
                tile.setFloor(floor.asFloor());
                tile.setBlock(wall);
                tile.setOverlay(ore);
            }
        }
        world.setGenerating(false);
    }

    private void cache(){

        //draw shadows
        Draw.proj().setOrtho(0, 0, shadows.getWidth(), shadows.getHeight());
        shadows.begin(Color.clear);
        Draw.color(Color.black);

        for(Tile tile : world.tiles){
            if(tile.block() != Blocks.air){
                Fill.rect(tile.x + 0.5f, tile.y + 0.5f, 1, 1);
            }
        }

        Draw.color();
        shadows.end();

        Batch prev = Core.batch;

        Core.batch = batch = new CacheBatch(new SpriteCache(width * height * 6, false));
        batch.beginCache();

        for(Tile tile : world.tiles){
            tile.floor().drawBase(tile);
        }

        for(Tile tile : world.tiles){
            tile.overlay().drawBase(tile);
        }

        cacheFloor = batch.endCache();
        batch.beginCache();

        for(Tile tile : world.tiles){
            tile.block().drawBase(tile);
        }

        cacheWall = batch.endCache();

        Core.batch = prev;
    }

    public void render() {
        time += Time.delta;
        float scaling = Math.max(Scl.scl(4f), Math.max(Core.graphics.getWidth() / ((width - 1f) * tilesize), Core.graphics.getHeight() / ((height - 1f) * tilesize)));
        camera.position.set(width * tilesize / 2f, height * tilesize / 2f);
        camera.resize(Core.graphics.getWidth() / scaling,
                Core.graphics.getHeight() / scaling);

        mat.set(Draw.proj());
        Draw.flush();
        Draw.proj(camera);
        batch.setProjection(camera.mat);
        batch.beginDraw();
        batch.drawCache(cacheFloor);
        batch.endDraw();
        Draw.color();
        Draw.rect(Draw.wrap(shadows.getTexture()),
                width * tilesize / 2f - 4f, height * tilesize / 2f - 4f,
                width * tilesize, -height * tilesize);
        Draw.flush();
        batch.beginDraw();
        batch.drawCache(cacheWall);
        batch.endDraw();

        Draw.proj(mat);
        Draw.color(0f, 0f, 0f, darkness);
        Fill.crect(0f, 0f, Core.graphics.getWidth(), Core.graphics.getHeight());
        Draw.color();
    }

    private void particles(){
        Texture tex = Core.assets.get("sprites/noiseAlpha.png", Texture.class);
        if(tex.getMagFilter() != Texture.TextureFilter.linear){
            tex.setFilter(Texture.TextureFilter.linear);
            tex.setWrap(Texture.TextureWrap.repeat);
        }

        Draw.z(state.rules.fog ? Layer.fogOfWar + 1 : Layer.weather - 1);
        Weather.drawNoiseLayers(tex, warmColor, 1000f, 0.34f, 0.65f, 1f, 1f, 0f, 3, -1.1f, 0.45f, 0.38f, 0.4f);
        Draw.reset();

        Draw.draw(Layer.weather, () ->
                Weather.drawParticles(
                        Core.atlas.find("particle"), particleColor,
                        3f, 5.6f, //minmax size
                        10000f, 1f, 10f, //density
                        windx, windy, //wind vectors
                        0.8f, 2f, //minmax alpha
                        45f, 74f, //sinscl
                        2.5f, 9f, //sinmag
                        false
                ));
    }
}
