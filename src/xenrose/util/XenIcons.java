package xenrose.util;

import arc.Core;
import arc.scene.style.TextureRegionDrawable;

public class XenIcons {

    public static TextureRegionDrawable
            armorAdd, armorAddUsed, maxHealth, maxHealthUsed, moduleLock, craftItem, craftLiquid, xenroseSetting, graphicsIcon, xenDiscord;

    public static void init() {
        armorAdd = get("armorAdd");
        armorAddUsed = get("armorAdd-used");
        maxHealth = get("maxHealth");
        maxHealthUsed = get("maxHealth-used");
        moduleLock = get("module-lock");
        craftItem = get("craft-item");
        craftLiquid = get("craft-liquid");
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
