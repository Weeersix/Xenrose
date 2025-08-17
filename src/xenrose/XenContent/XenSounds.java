package xenrose.XenContent;

import arc.Core;
import arc.assets.AssetDescriptor;
import arc.assets.loaders.SoundLoader;
import arc.audio.Sound;
import mindustry.Vars;

public class XenSounds {
    public static Sound
            zanarShoot = new Sound(),
            imitationShoot = new Sound(),
            weaponMinShoot1 = new Sound(),
            weaponMinShoot2 = new Sound(),
            largeShoot1 = new Sound(),
            inzeranDash = new Sound(),
            xanitShoot = new Sound(),
            shipShoot1 = new Sound();

    public static void load(){
        zanarShoot = loadSound("zanar-shoot");
        imitationShoot = loadSound("imitation-shoot");
        weaponMinShoot1 = loadSound("weapon-min-shoot1");
        weaponMinShoot2 = loadSound("weapon-min-shoot2");
        largeShoot1 = loadSound("large-shoot1");
        inzeranDash = loadSound("inzeran-dash");
        xanitShoot = loadSound("xanit-shoot");
        shipShoot1 = loadSound("ship-shoot1");
    }

    private static Sound loadSound(String soundName){
        if(!Vars.headless) {
            String name = "sounds/" + soundName;
            String path = Vars.tree.get(name + ".ogg").exists() ? name + ".ogg" : name + ".mp3";

            Sound sound = new Sound();

            AssetDescriptor<?> desc = Core.assets.load(path, Sound.class, new SoundLoader.SoundParameter(sound));
            desc.errored = Throwable::printStackTrace;

            return sound;

        } else {
            return new Sound();
        }
    }
}
