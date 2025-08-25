package xenrose.util;

import arc.Core;
import arc.scene.style.TextureRegionDrawable;

public class XenIcons {

    public static TextureRegionDrawable
            armorAdd, armorAddUsed, maxHealth, maxHealthUsed, reflectModuleAdd, reflectModuleUsed, shieldAdd, shieldUsed, moduleLock, xenroseSetting, graphicsIcon, xenDiscord;

    public static void init() {
        armorAdd = get("armorAdd");
        armorAddUsed = get("armorAdd-used");
        maxHealth = get("maxHealth");
        maxHealthUsed = get("maxHealth-used");
        reflectModuleAdd = get("reflect-module-add");
        reflectModuleUsed = get("reflect-module-used");
        shieldAdd = get("shield-add");
        shieldUsed = get("shield-used");
        moduleLock = get("module-lock");

        xenroseSetting = get("xenrose-setting");
        graphicsIcon = get("xenrose-graphics-icon");
        xenDiscord = get("discord");
    }

    static TextureRegionDrawable get(String name){
        return new TextureRegionDrawable(Core.atlas.find("xenrose-" + name));
    }

    public static TextureRegionDrawable get(TextureRegionDrawable icon){
        return new TextureRegionDrawable(icon);
    }
}
