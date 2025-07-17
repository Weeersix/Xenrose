package xenrose.XenContent;

import arc.graphics.Color;
import mindustry.type.Liquid;

public class XenLiquids {
    public static Liquid liquidKirmit, liquidOrinil, oxygen;

    public static void load() {
        liquidKirmit = new Liquid("liquid-kirmite", Color.valueOf("9265bd")){{
            temperature = 0.3f;
            viscosity = 0.5f;
            lightColor = Color.valueOf("bd8cec").a(0.5f);
        }};
        liquidOrinil = new Liquid("liquid-orinil", Color.valueOf("ffc77e")){{
            temperature = 6f;
            viscosity = 0.35f;
            heatCapacity = 1.35f;
            lightColor = Color.valueOf("ffc77e").a(0.9f);
        }};
        oxygen = new Liquid("oxygen", Color.valueOf("9097a1")){{
            gas = true;
            flammability = 3.4f;
        }};
    }
}
