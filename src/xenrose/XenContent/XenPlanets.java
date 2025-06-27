package xenrose.XenContent;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.math.geom.Vec3;
import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.world.meta.Env;
import xenrose.PlanetGenerator.*;
import mindustry.content.Planets;
import mindustry.graphics.Pal;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.type.Planet;
import xenrose.world.meta.*;

public class XenPlanets{
    public static Planet
            xenor;

    public static void load() {
        xenor = new Planet("Xenor", Planets.sun, 1.4f, 3){{
            generator = new XenPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 5);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 2, 0.2f, 0.19f, 5, Color.valueOf("a595829c").a(0.55f), 4, 0.33f, 0.8f, 0.45f),
                    new HexSkyMesh(this, 4, 0.8f, 0.21f, 5, Color.valueOf("b89767d9").a(0.75f), 3, 0.42f, 1f, 0.47f),
                    new HexSkyMesh(this, 5, 0.5f, 0.16f, 5, Color.valueOf("cc9b5298").a(0.67f), 5, 0.32f, 0.96f, 0.47f)
            );
            launchCapacityMultiplier = 0.5f;
            sectorSeed = 2;
            allowLaunchLoadout = true;
            allowLaunchSchematics = true;
            prebuildBase = false;
            defaultCore = XenBlocks.coreSunrise;
            defaultEnv = Environment.warm | Env.terrestrial;
            clearSectorOnLose = false;
            ruleSetter = r -> {
                r.waveTeam = XenTeams.renars;
                r.placeRangeCheck = false;
                r.showSpawns = false;
                r.coreDestroyClear = true;
                r.ambientLight = Color.valueOf("c8793246");
                r.coreDestroyClear = false;
                r.onlyDepositCore = true;
            };
            unlockedOnLand.add(XenBlocks.coreSunrise);
            iconColor = Pal.shield;
            orbitRadius = 28.3f;
            atmosphereColor = Color.valueOf("a595829c");
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 1;
            allowLaunchToNumbered = false;
            alwaysUnlocked = true;
            landCloudColor = Pal.darkMetal.cpy().a(0.5f);
            updateLighting = false;
        }};
    }
}