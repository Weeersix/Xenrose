package xenrose.XenContent;

import mindustry.content.Items;
import mindustry.type.ItemStack;
import xenrose.type.Modifier;

public class BlocksModifiers {
    public static Modifier
            //Walls
            diocasiumArmor, healTechTree, reflectModifier, shieldModifier;

    public static void load(){

        //Walls
        reflectModifier = new Modifier("reflection-module"){{
            researchCost = ItemStack.with(XenItems.damascus, 12550f, XenItems.zinc, 10850f,XenItems.dantstalin, 10150f, XenItems.gold, 8960, Items.silicon, 8230f);
        }};
        healTechTree = new Modifier("heal-tech-tree"){{
            researchCost = ItemStack.with(XenItems.damascus, 21650f, XenItems.zinc, 18500f,XenItems.dantstalin, 16150f, XenItems.protexide, 7580, Items.silicon, 7490);
        }};
        diocasiumArmor = new Modifier("diocasium-armor"){{
            researchCost = ItemStack.with(XenItems.damascus, 58650f, XenItems.zinc, 27570f,XenItems.dantstalin, 26150f, XenItems.gold, 17580, XenItems.protexide, 10490, Items.silicon, 10000f, XenItems.diocasium, 4670);
        }};
        shieldModifier = new Modifier("shield-module"){{
            researchCost = ItemStack.with(XenItems.damascus, 78650f, XenItems.zinc, 77570f, XenItems.dantstalin, 65150f, XenItems.gold, 57580, XenItems.protexide, 50490, Items.silicon, 48650f, XenItems.isoteron, 5000);
        }};
    }
}
