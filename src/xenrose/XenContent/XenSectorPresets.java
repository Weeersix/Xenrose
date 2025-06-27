package xenrose.XenContent;

import mindustry.content.SectorPresets;
import mindustry.content.Weathers;
import mindustry.type.SectorPreset;
import mindustry.type.Weather;

public class XenSectorPresets {
    public static SectorPreset
            Landing, BurntHills, DryingThickets, LightLowland, KirmitCoast;
    public static void load(){
        Landing = new SectorPreset("Landing", XenPlanets.xenor, 1){{
            alwaysUnlocked = true;
            difficulty = 1;
            captureWave = 6;
            overrideLaunchDefaults = true;
            startWaveTimeMultiplier = 3f;
        }};
        BurntHills = new SectorPreset("Burnt-hills", XenPlanets.xenor, 13){{
            difficulty = 1;
            captureWave = 10;
        }};
        DryingThickets = new SectorPreset("Drying-thickets", XenPlanets.xenor, 4){{
            difficulty = 1;
        }};
        LightLowland = new SectorPreset("Light-lowland", XenPlanets.xenor, 24){{
            difficulty = 3;
            captureWave = 15;
        }};
        KirmitCoast = new SectorPreset("Kirmit-coast", XenPlanets.xenor, 3){{
            difficulty = 3;
        }};
    }
}