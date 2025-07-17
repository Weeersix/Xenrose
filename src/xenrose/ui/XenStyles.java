package xenrose.ui;

import arc.Core;
import arc.scene.ui.ImageButton;

public class XenStyles {

    public static ImageButton.ImageButtonStyle sandButton, metalButton;

    public static void load() {
        sandButton = new ImageButton.ImageButtonStyle() {{
            up = imageUp = Core.atlas.drawable("xenrose-xen-button");
        }};
        metalButton = new ImageButton.ImageButtonStyle() {{
            up = imageUp = Core.atlas.drawable("xenrose-metal-button");
        }};
    }
}
