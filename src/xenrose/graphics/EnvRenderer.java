package xenrose.graphics;

import arc.Core;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.Texture;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.math.Rand;
import arc.util.Time;
import mindustry.graphics.Layer;
import mindustry.type.Weather;
import xenrose.world.meta.Environment;

import static mindustry.Vars.*;

public class EnvRenderer {

    public static void init() {
        float windSpeed = 1.9f, windAngle = 50f;
        float windx = Mathf.cosDeg(windAngle) * windSpeed, windy = Mathf.sinDeg(windAngle) * windSpeed;
        Color warmColor = Color.valueOf("b36d3e");
        Rand rand = new Rand();
        Core.assets.load("sprites/noiseAlpha.png", Texture.class);
        Color particleColor = Color.valueOf("f4b866");
        renderer.addEnvRenderer(Environment.warm, () -> {
            Core.assets.load("sprites/rays.png", Texture.class).loaded = t -> t.setFilter(Texture.TextureFilter.linear);
            Texture tex = Core.assets.get("sprites/noiseAlpha.png", Texture.class);
            if (tex.getMagFilter() != Texture.TextureFilter.linear) {
                tex.setFilter(Texture.TextureFilter.linear);
                tex.setWrap(Texture.TextureWrap.repeat);
            }

            Draw.z(state.rules.fog ? Layer.fogOfWar - 1 : Layer.weather - 1);
            Weather.drawNoiseLayers(tex, warmColor, 1000f, 0.34f, 0.65f, 1f, 1f, 0f, 3, -1.1f, 0.45f, 0.38f, 0.4f);
            Draw.reset();

            Draw.z(Layer.light + 2);
            int rays = 65;
            float timeScale = 2000f;
            rand.setSeed(0);
            Draw.blend(Blending.additive);

            float t = Time.time / timeScale;
            Texture ray = Core.assets.get("sprites/rays.png", Texture.class);
            for (int i = 0; i < rays; i++) {
                float offset = rand.random(0f, 1f);
                float time = t + offset;
                int pos = (int) time;
                float life = time % 1f;
                float opacity = rand.random(0.2f, 0.5f) * Mathf.slope(life) * 0.7f;
                float x = (rand.random(0f, world.unitWidth()) + (pos % 100) * 753) % world.unitWidth();
                float y = (rand.random(0f, world.unitHeight()) + (pos % 120) * 453) % world.unitHeight();
                float rot = rand.range(7f);
                float sizeScale = 1f + rand.range(0.3f);
                float topDst = (Core.camera.position.y + Core.camera.height / 2f) - (y + ray.height / 2f + ray.height * 1.9f * sizeScale / 2f);
                float invDst = topDst / 1000f;
                opacity = Math.min(opacity, -invDst);

                if (opacity > 0.01) {
                    Draw.alpha(opacity);
                    Draw.rect(Draw.wrap(ray), x, y + ray.height / 2f, ray.width * 2 * sizeScale, ray.height * 2 * sizeScale, rot);
                    Draw.color();
                }
            }

            Draw.draw(Layer.weather, () ->
                    Weather.drawParticles(
                            Core.atlas.find("particle"), particleColor,
                            3f, 5.6f, //minmax size
                            10000f, 1f, 1f, //density
                            windx, windy, //wind vectors
                            0.8f, 2f, //minmax alpha
                            45f, 74f, //sinscl
                            2.5f, 9f, //sinmag
                            false
                    ));
            Draw.blend();
        });

        renderer.addEnvRenderer(Environment.warmLite, () -> {
            Texture tex = Core.assets.get("sprites/noiseAlpha.png", Texture.class);
            if (tex.getMagFilter() != Texture.TextureFilter.linear) {
                tex.setFilter(Texture.TextureFilter.linear);
                tex.setWrap(Texture.TextureWrap.repeat);
            }
            Draw.z(state.rules.fog ? Layer.fogOfWar - 1 : Layer.weather - 1);
            Weather.drawNoiseLayers(tex, warmColor, 1000f, 0.34f, 0.65f, 1f, 1f, 0f, 3, -1.1f, 0.45f, 0.38f, 0.4f);
            Draw.reset();
        });
    }
}