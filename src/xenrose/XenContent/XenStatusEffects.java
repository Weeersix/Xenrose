package xenrose.XenContent;

import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.entities.Effect;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;

import static mindustry.content.StatusEffects.tarred;

public class XenStatusEffects {

    public static StatusEffect shield;

    public static void load(){
        float polySpinScl = 0.8f, polyStroke = 1.5f;
        int sides = 8;

        shield = new StatusEffect("shield"){{
            healthMultiplier = 2.8f;
            color = Pal.accent;
            effect = new Effect(1500, e -> {
                affinity(tarred, (unit, result, time) -> {
                    Lines.stroke(polyStroke);
                    Lines.poly(unit.x, unit.y, sides, Mathf.range(unit.bounds() / 2f) * Time.time, Time.time / polySpinScl);
                });
            });
        }};
    }
}
