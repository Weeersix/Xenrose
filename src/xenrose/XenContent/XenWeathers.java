package xenrose.XenContent;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.gen.Sounds;
import mindustry.type.Weather;
import mindustry.type.weather.ParticleWeather;
import mindustry.world.meta.Attribute;

public class XenWeathers {
    float windSpeed = 2.5f, windAngle = 50f;
    float windx = Mathf.cosDeg(windAngle) * windSpeed, windy = Mathf.sinDeg(windAngle) * windSpeed;

        public static Weather
                storm;

    public static void load() {
            storm = new ParticleWeather("storm") {{
                color = noiseColor = Color.valueOf("f7cba4");
                particleRegion = "particle";
                drawNoise = true;
                useWindVector = false;
                sizeMax = 210f;
                sizeMin = 110f;
                minAlpha = 0.1f;
                maxAlpha = 0.4f;
                density = 2000f;
                baseSpeed = 5.4f;
                attrs.set(Attribute.light, -0.1f);
                attrs.set(Attribute.water, -0.1f);
                opacityMultiplier = 0.35f;
                force = 0.1f;
                sound = Sounds.wind;
                soundVol = 0.8f;
                duration = 7f * Time.toMinutes;
            }};
    }
}
