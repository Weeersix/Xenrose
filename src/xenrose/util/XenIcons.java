package xenrose.util;

import arc.*;
import arc.scene.style.*;
import mindustry.content.*;

public class XenIcons {

    public static TextureRegionDrawable
            armorAdd, armorRemove, xenDiscord;

    public static void init() {
        armorAdd = get("armorAdd");
        armorRemove = get("armorRemove");
        xenDiscord = get("xenrose-discord");
    }

    static TextureRegionDrawable get(String name){
        return new TextureRegionDrawable(Core.atlas.find("xenrose-" + name));
    }

    public static TextureRegionDrawable get(TextureRegionDrawable icon){
        return new TextureRegionDrawable(icon);
    }
}
