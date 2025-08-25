package xenrose.ui;

import arc.Core;
import arc.scene.ui.ImageButton;

public class XenStyles {

    public static ImageButton.ImageButtonStyle armorButton, armorButtonUsed, maxHealthButton, reflectModule, shieldButton, buttonUsed, lockButton, metalButton, discordBack;

    public static void load() {
        armorButton = new ImageButton.ImageButtonStyle() {{
            up = imageUp = Core.atlas.drawable("xenrose-xen-button");
        }};
        armorButtonUsed = new ImageButton.ImageButtonStyle() {{
            up = imageUp = Core.atlas.drawable("xenrose-xen-button-used");
        }};
        maxHealthButton = new ImageButton.ImageButtonStyle() {{
            up = imageUp = Core.atlas.drawable("xenrose-max-health-button");
        }};
        reflectModule = new ImageButton.ImageButtonStyle() {{
            up = imageUp = Core.atlas.drawable("xenrose-reflect-module-button");
        }};
        shieldButton = new ImageButton.ImageButtonStyle() {{
            up = imageUp = Core.atlas.drawable("xenrose-shield-button");
        }};
        buttonUsed = new ImageButton.ImageButtonStyle() {{
            up = imageUp = Core.atlas.drawable("xenrose-button-used");
        }};
        lockButton = new ImageButton.ImageButtonStyle() {{
            up = imageUp = Core.atlas.drawable("xenrose-lock-button");
        }};
        metalButton = new ImageButton.ImageButtonStyle() {{
            up = imageUp = Core.atlas.drawable("xenrose-metal-button");
        }};
        discordBack = new ImageButton.ImageButtonStyle() {{
            up = imageUp = Core.atlas.drawable("xenrose-discord-back");
        }};
    }
}
