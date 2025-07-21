package xenrose.ui;

import arc.struct.Seq;
import mindustry.content.TechTree;
import mindustry.ui.dialogs.SettingsMenuDialog;

public class XenSettings {
    protected Seq<SettingsMenuDialog.SettingsTable.Setting> list = new Seq<>();
    public static void resetTree(TechTree.TechNode root) {
        root.reset();
        root.content.clearUnlock();
        root.children.each(XenSettings::resetTree);
    }
}
