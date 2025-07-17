package xenrose.XenContent;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Vec2;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;

import static arc.graphics.g2d.Draw.alpha;
import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;

public class XenFx extends Fx {
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();

    public static final Effect

    crusherSmoke = new Effect(150f, e -> {
        color(Color.valueOf("d3ae8d"));
        alpha(1.6f);

        rand.setSeed(e.id);
        for(int i = 0; i < 3; i++){
            float len = rand.random(6f), rot = rand.range(35f) + e.rotation;

            e.scaled(e.lifetime * rand.random(0.3f, 1f), b -> {
                v.trns(rot, len * b.finpow());
                Fill.circle(e.x + v.x, e.y + v.y, 3f * b.fslope() + 0.2f);
            });
        }
    }),

    pyrometallurgicalInstallationSmoke = new Effect(210f, e -> {
        color(Color.valueOf("b76b19"));
        alpha(1.6f);

        rand.setSeed(e.id);
        for(int i = 0; i < 3; i++){
            float len = rand.random(6f), rot = rand.range(35f) + e.rotation;

            e.scaled(e.lifetime * rand.random(0.3f, 1f), b -> {
                v.trns(rot, len * b.finpow());
                Fill.circle(e.x + v.x, e.y + v.y, 3f * b.fslope() + 0.2f);
            });
        }
    }),
    kirmiteSteam = new Effect(110f, e -> {
        color(e.color, Color.valueOf("9265bd"), e.fin());

        alpha(e.fslope() * 0.78f);

        float length = 4f + e.finpow() * 10f;
        rand.setSeed(e.id);
        for (int i = 0; i < rand.random(3, 5); i++) {
            v.trns(rand.random(360f), rand.random(length));
            Fill.circle(e.x + v.x, e.y + v.y, rand.random(1.2f, 3.5f) + e.fslope() * 1.1f);
        }
    }),

    zanarTrail = new Effect(20, e -> {
        color(Color.valueOf("fbcf95"), e.color, e.fin());
        stroke(0.5f + e.fout() * 1.7f);
        rand.setSeed(e.id);

        for(int i = 0; i < 2; i++){
            float rot = e.rotation + rand.range(10f) + 180f;
            v.trns(rot, rand.random(e.fin() * 24f));
            lineAngle(e.x + v.x, e.y + v.y, rot, e.fout() * rand.random(2f, 7f) + 1.5f);
        }
    }),

    imitationTrail = new Effect(40, e -> {
        color(Color.valueOf("c696f2b7"), e.color, e.fin());
        stroke(0.5f + e.fout() * 1.7f);
        rand.setSeed(e.id);

        for (int i = 0; i < 2; i++) {
            float rot = e.rotation + rand.range(10f) + 180f;
            v.trns(rot, rand.random(e.fin() * 24f));
            lineAngle(e.x + v.x, e.y + v.y, rot, e.fout() * rand.random(2f, 7f) + 1.5f);
        }
    }),

    inzeranTrail = new Effect(80, e -> {
        color(Color.valueOf("fbcf95"), e.color, e.fin());
        stroke(0.5f + e.fout() * 1.7f);
        rand.setSeed(e.id);

        for (int i = 0; i < 2; i++) {
            float rot = e.rotation + rand.range(10f) + 180f;
            v.trns(rot, rand.random(e.fin() * 24f));
            lineAngle(e.x + v.x, e.y + v.y, rot, e.fout() * rand.random(2f, 7f) + 2f);
        }
    }),

    inzeranExplosion = new Effect(40f, 100f, e -> {
        color(Color.valueOf("fbcf95"));
        stroke(e.fout() * 2f);
        float circleRad = 4f + e.finpow() * 65f;
        Lines.circle(e.x, e.y, circleRad);

        color(Color.valueOf("fbcf95"));
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 6f, 100f * e.fout(), i*90);
        }

        for(int i = 0; i < 2; i++){
            float rot = e.rotation + rand.range(10f) + 180f;
            v.trns(rot, rand.random(e.fin() * 24f));
            lineAngle(e.x + v.x, e.y + v.y, rot, e.fout() * rand.random(2f, 7f) + 1.5f);
        }

        color();
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 3f, 35f * e.fout(), i*90);
        }

        Drawf.light(e.x, e.y, circleRad * 1.6f, Color.valueOf("fbcf95"), e.fout());
    }),

    inzeranCharge = new Effect(80f, 80f, e -> {
        color(Color.valueOf("fbcf95"));
        stroke(e.fin() * 2f);
        Lines.circle(e.x, e.y, 3f + e.fout() * 100f);

        Fill.circle(e.x, e.y, e.fin() * 14);

        randLenVectors(e.id, 20, 40f * e.fout(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fin() * 5f);
            Drawf.light(e.x + x, e.y + y, e.fin() * 15f, Color.valueOf("fbcf95"), 0.5f);
        });

        color();

        Fill.circle(e.x, e.y, e.fin() * 10);
        Drawf.light(e.x, e.y, e.fin() * 20f, Color.valueOf("e9bb59"), 0.7f);
    }).followParent(true).rotWithParent(true),

    mergeHitSquares = new Effect(14, e -> {
        color(Color.valueOf("f0b467"), e.color, e.fin());

        e.scaled(7f, s -> {
            stroke(0.5f + s.fout());
            Lines.circle(e.x, e.y, s.fin() * 5f);
        });

        stroke(0.5f + e.fout());

        randLenVectors(e.id, 5, e.fin() * 17f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            Fill.square(e.x + x, e.y + y, e.fout() * 3.2f, ang);
        });

        Drawf.light(e.x, e.y, 20f, e.color, 0.6f * e.fout());
    });
}
