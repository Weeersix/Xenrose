package xenrose.PlanetGenerator;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.math.geom.Vec3;
import arc.util.Tmp;
import arc.util.noise.Ridged;
import arc.util.noise.Simplex;
import mindustry.game.Schematics;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.world.Block;
import xenrose.XenContent.XenBlocks;

public class XenPlanetGenerator extends PlanetGenerator{
    public float heightScl = 0.9f, octaves = 8, persistence = 0.7f, heightPow = 3f, heightMult = 1.6f;

    Color c1 = Color.valueOf("5057a6"), c2 = Color.valueOf("272766");

    Block[] terrain = {XenBlocks.orinilWall, XenBlocks.orinilWall, XenBlocks.burnedDamscusWall, XenBlocks.burnedDamscusWall, XenBlocks.burnedDamscusWall, XenBlocks.burnedDamscusWall, XenBlocks.burnedDamscusWall, XenBlocks.damascusWall, XenBlocks.burnedDamscusWall, XenBlocks.damascusWall, XenBlocks.damascusWall,XenBlocks.burnedDamscusWall, XenBlocks.burnedDamscusWall, XenBlocks.damascusWall};

    public static float arkThresh = 0.28f, arkScl = 0.83f;
    public static int arkSeed = 7, arkOct = 2;
    public static float redThresh = 3.1f, noArkThresh = 0.3f;
    public static int kirmiteSeed = 8, kirmiteOct = 1;
    public static float kirmiteScl = 0.4f, kirmiteMag = 0.02f;

    {
        baseSeed = 2;
        defaultLoadout = Schematics.readBase64("bXNjaAF4nGNgZmBmZmDJS8xNZeBJzi9K1S0uzSvKLE5l4E5JLU4uyiwoyczPY2BgYMtJTErNKWZgio5lZBCpSM0ryi9O1UXRwcDACEJAAgD8Ghak");
    }

    @Override
    public float getHeight(Vec3 position){
        return Mathf.pow(rawHeight(position), heightPow) * heightMult;
    }

    @Override
    public float getSizeScl(){
        return 1750 * 1.07f * 6f / 5f;
    }

    @Override
    public Color getColor(Vec3 position){
        Block block = getBlock(position);

        if(block == XenBlocks.burnedDamscusWall) block = XenBlocks.burnedDamscusWall;

        return Tmp.c1.set(block.mapColor).a(1f - block.albedo);
    }

    float rawHeight(Vec3 position){
        return Simplex.noise3d(seed, octaves, persistence, 1f/heightScl, 10f + position.x, 10f + position.y, 10f + position.z);
    }

    float rawTemp(Vec3 position){
        return position.dst(0, 0, 1)*2.2f - Simplex.noise3d(seed, 8, 0.54f, 1.4f, 10f + position.x, 10f + position.y, 10f + position.z) * 2.9f;
    }

    @Override
    protected float noise(float x, float y, double octaves, double falloff, double scl, double mag){
        Vec3 v = sector.rect.project(x, y).scl(5f);
        return Simplex.noise3d(seed, octaves, falloff, 1f / scl, v.x, v.y, v.z) * (float)mag;
    }

    Block getBlock(Vec3 position){
        float ice = rawTemp(position);
        Tmp.v32.set(position);

        float height = rawHeight(position);
        Tmp.v31.set(position);
        height *= 1.3f;
        height = Mathf.clamp(height);

        Block result = terrain[Mathf.clamp((int)(height * terrain.length), 0, terrain.length - 1)];

        if(ice < 0.3 + Math.abs(Ridged.noise3d(seed + kirmiteSeed, position.x + 4f, position.y + 5f, position.z + 0.5f, kirmiteOct, kirmiteScl)) * kirmiteMag){
            return XenBlocks.kirmiteStoneWall;
        }

        if(ice < 0.8){
            if(result == XenBlocks.burnedDamscusWall){
                return XenBlocks.burnedDamscusWall;
            }
        }

        position = Tmp.v32;

        if(ice < redThresh - noArkThresh && Ridged.noise3d(seed + arkSeed, position.x + 2f, position.y + 8f, position.z + 1f, arkOct, arkScl) > arkThresh){
            result = XenBlocks.burnedDamscusWall;
        }

        if(ice > redThresh){
            result = XenBlocks.kirmiteStoneWall;
        }else if(ice > redThresh - 0.4){
            result = XenBlocks.burnedDamscusWall;
        }else if(ice > redThresh - 0.6) {
            result = XenBlocks.orinilWall;
        }

        return result;
    }
}