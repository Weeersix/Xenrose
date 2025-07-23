package xenrose.XenContent;

import mindustry.game.Schematic;
import mindustry.game.Schematics;

public class XenLoadouts {
    public static Schematic
            basicSunrise,
            basicZenith;

    public static void load(){
        basicSunrise = Schematics.readBase64("bXNjaAF4nGNgZmBmZmDJS8xNZeBJzi9K1S0uzSvKLE5l4E5JLU4uyiwoyczPY2BgYMtJTErNKWZgio5lZBCpSM0ryi9O1UXRwcDACEJAAgD8Ghak");
        basicZenith = Schematics.readBase64("bXNjaAF4nCXMwQmAMBBE0VFEQY82YANWYReKhxgHXDCJZHMQqzciPP7xo0KVeeOIbgqRw0wv6UCjicbJjlbtQWeSWEW3U22UK0nwAOrTbDwV5bIW6G/6GJSjzZfx+S9A8cl5ARVFHIY=");
    }
}
