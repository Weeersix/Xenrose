package xenrose.XenContent;

import mindustry.type.SectorPreset;

public class XenSectorPresets {
    public static SectorPreset
            Landing, BurntHills, DryingThickets, LightLowland, KirmitCoast, OrinilFault, KirmiteArhipelago;
    public static void load(){
        Landing = new SectorPreset("Landing", XenPlanets.xenor, 1){{
            alwaysUnlocked = true;
            difficulty = 1;
            captureWave = 6;
            overrideLaunchDefaults = true;
            startWaveTimeMultiplier = 3f;
            showSectorLandInfo = true;
        }};
        BurntHills = new SectorPreset("Burnt-hills", XenPlanets.xenor, 13){{
            difficulty = 1;
            captureWave = 10;
            showSectorLandInfo = true;
        }};
        DryingThickets = new SectorPreset("Drying-thickets", XenPlanets.xenor, 4){{
            difficulty = 1;
            showSectorLandInfo = true;
        }};
        LightLowland = new SectorPreset("Light-lowland", XenPlanets.xenor, 24){{
            difficulty = 3;
            captureWave = 15;
            showSectorLandInfo = true;
        }};
        KirmitCoast = new SectorPreset("Kirmit-coast", XenPlanets.xenor, 3){{
            difficulty = 3;
            showSectorLandInfo = true;
        }};
        OrinilFault = new SectorPreset("Orinil-fault", XenPlanets.xenor, 36){{
            difficulty = 5;
            showSectorLandInfo = true;
        }};
        KirmiteArhipelago = new SectorPreset("Kirmite-Archipelago", XenPlanets.xenor, 2){{
            difficulty = 6;
            showSectorLandInfo = true;
        }};
    }
}