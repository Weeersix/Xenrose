package xenrose.graphics;

import arc.graphics.Color;
import arc.graphics.g2d.TextureRegion;
import arc.util.Nullable;

public class Rotor {
    public String suffix;

    public @Nullable String iconOverride;

    public float x, y, rotation;
    public float width = 21, height = 2, spinScale = 1;

    public int pixelWidth = 32, pixelHeight = 32;

    public boolean icon = true, hasSprites = true, circular = false;

    public Color palLight = Color.valueOf("7c746b");
    public Color palMedium = Color.valueOf("57534e");
    public Color palDark = Color.valueOf("2f2d2a");

    public TextureRegion[] regions;
    public TextureRegion iconRegion;

    public Rotor(String suffix){
        this.suffix = suffix;
    }
}
