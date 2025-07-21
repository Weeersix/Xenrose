package xenrose;

import arc.Core;
import arc.Events;
import arc.util.Log;
import arc.util.Reflect;
import mindustry.game.EventType;
import mindustry.gen.Icon;
import mindustry.mod.Mod;
import mindustry.ui.fragments.MenuFragment;
import xenrose.XenContent.*;
import xenrose.graphics.EnvRenderer;
import xenrose.graphics.XenMenuRenderer;
import xenrose.tools.IconLoader;
import xenrose.ui.XenStyles;
import xenrose.util.XenIcons;

import static arc.Core.bundle;
import static mindustry.Vars.ui;
import static xenrose.XenContent.XenPlanets.xenor;
import static xenrose.ui.XenSettings.resetTree;

public class Xenrose extends Mod {

    public Xenrose(){
        //What are you doing here?
        Events.on(EventType.ClientLoadEvent.class, e -> {
            loadSettings();
        });
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
                    Reflect.set(MenuFragment.class, ui.menufrag, "renderer", new XenMenuRenderer());
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

    private void loadSettings(){
        ui.settings.addCategory(bundle.get("setting.xenrose-title"), XenIcons.xenroseSetting, t -> {
            t.button("@settings.Xenrose-tech-tree", Icon.tree, () -> {
                ui.showConfirm("@confirm", "@settings.xenrose-tech-tree.confirm", () -> resetTree(xenor.techTree));
            }).size(290f, 60f).left();
        });
        ui.settings.addCategory(bundle.get("setting.xenrose-graphics-title"), XenIcons.graphicsIcon, t -> {
            t.checkPref("@settings.particles", true);
            t.checkPref("@settings.blocks-effects", true);
        });
    }
}