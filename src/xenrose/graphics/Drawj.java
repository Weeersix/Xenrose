package xenrose.graphics;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.util.Tmp;

public class Drawj {

    public static Color palLight, palMedium, palDark;

    public static void palette() {
        palLight = palMedium = palDark = Color.clear;
    }
    public static void palette(Color light, Color medium, Color dark) {
        palLight = light;
        palMedium = medium;
        palDark = dark;
    }

    public static void regionCircular(TextureRegion[] regions, float x, float y, float width, float height, float angle, float rotation, boolean circular) {
        int sides = regions.length;
        for (int i = 0; i < sides; i++) {
            float mod1 = Mathf.mod(angle + 360f/regions.length * i, 360f);
            float mod2 = Mathf.mod(angle + 360f/regions.length * (i + 1), 360f);

            float cos1 = Mathf.cos(Mathf.degreesToRadians * mod1, 1f, 1f);
            float cos2 = Mathf.cos(Mathf.degreesToRadians * mod2, 1f, 1);
            float cos3 = Mathf.cos(Mathf.degreesToRadians * (angle + 360f/regions.length * (i + 0.5f)), 1, 1);

            if (cos3 > 0f) {
                Tmp.c1.set(palMedium).lerp(palLight, Mathf.clamp(cos3));
            } else {
                Tmp.c1.set(palDark).lerp(palMedium, Mathf.clamp(cos3 + 1f));
            }

            Draw.mixcol(Tmp.c1, Tmp.c1.a);
        }
    }
}
