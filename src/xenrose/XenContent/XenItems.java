package xenrose.XenContent;

import arc.graphics.*;
import arc.struct.Seq;
import mindustry.type.*;


public class XenItems {
    public static Item
            damascus, zinc, gold, dantstalin, protexide, diocasium, moluron, isoteron, asteril;
    public static final Seq<Item> xenorItems = new Seq<>();
    public static void load(){
        damascus = new Item("damascus", Color.valueOf("9d9175")){{
            hardness = 2;
            cost = 1;
        }};
        zinc = new Item("zinc", Color.valueOf("54525d")){{
            hardness = 3;
            cost = 3;
        }};
        gold = new Item("gold", Color.valueOf("ffa62a")){{
            cost = 3;
            healthScaling = 0.2f;
        }};
        dantstalin = new Item("dantstalin", Color.valueOf("c6cfda")){{
            cost = 4;
            healthScaling = 0.7f;
        }};
        protexide = new Item("protexide", Color.valueOf("443a5a")){{
            hardness = 5;
            cost = 4;
            explosiveness = 1.25f;
            radioactivity = 0.3f;
        }};
        diocasium = new Item("diocasium", Color.valueOf("d68136")){{
            radioactivity = 1.3f;
            explosiveness = 2.6f;
            charge = 3.8f;
            cost = 4;
            healthScaling = 0.3f;
            frames = 8;
            frameTime = 6;
        }};
        isoteron = new Item("isoteron", Color.valueOf("fbb463")){{
            radioactivity = 1.4f;
            explosiveness = 0.3f;
            charge = 6.8f;
            cost = 5;
            healthScaling = 0.5f;
            frames = 8;
            frameTime = 6;
        }};
        moluron = new Item("moluron", Color.valueOf("5a10bc")){{
            radioactivity = 2f;
            explosiveness = 1f;
            charge = 9.5f;
            cost = 6;
            healthScaling = 0.5f;
            frames = 12;
            frameTime = 4;
        }};
        asteril = new Item("asteril", Color.valueOf("80bcb0")){{
            radioactivity = 4f;
            explosiveness = 3.2f;
            charge = 18.2f;
            cost = 7;
            healthScaling = 0.92f;
            frames = 8;
            frameTime = 6;
        }};
    }
}