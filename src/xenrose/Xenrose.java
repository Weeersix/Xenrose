package xenrose;

import arc.Core;
import arc.Events;
import arc.util.Log;
import arc.util.Reflect;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.mod.Mod;
import mindustry.ui.fragments.MenuFragment;
import xenrose.XenContent.*;
import xenrose.graphics.EnvRenderer;
import xenrose.graphics.XenMenuRenderer;
import xenrose.tools.IconLoader;
import xenrose.ui.XenStyles;
import xenrose.util.XenIcons;

public class Xenrose extends Mod {

    public Xenrose(){
        //What are you doing here?
    }

    @Override
    public void init(){
        super.init();
        XenIcons.init();
        XenTeams.load();
        IconLoader.loadIcons();
        XenStyles.load();

        Events.on(EventType.ClientLoadEvent .class, e -> {
            if (Core.settings.getBool("xenrose-custom-menu", true)) {
                try {
                    Reflect.set(MenuFragment.class, Vars.ui.menufrag, "renderer", new XenMenuRenderer());
                } catch (Exception except) {
                    Log.err("Failed to replace renderer", except);
                }
            }
        });
    }

    @Override
    public void loadContent() {
        Log.info("Loading some example content.");
        XenShaders.load();
        XenCacheLayer.load();
        XenItems.load();
        XenLiquids.load();
        XenSounds.load();
        XenWeathers.load();
        XenUnits.load();
        XenBlocks.load();
        XenPlanets.load();
        XenSectorPresets.load();
        XenTechTree.load();

        EnvRenderer.init();
    }
}