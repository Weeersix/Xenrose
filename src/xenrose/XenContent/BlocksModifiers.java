package xenrose.XenContent;

import mindustry.type.ItemStack;
import xenrose.type.Modifier;
import xenrose.type.SecondModifier;

public class BlocksModifiers {
    public static Modifier
            //Walls
            diocasiumArmor, healTechTree;

    public static void load(){

        //Walls
        healTechTree = new SecondModifier("heal-tech-tree"){{
            researchCost = ItemStack.with(XenItems.damascus, 8850, XenItems.zinc, 8490, XenItems.dantstalin, 6200, XenItems.gold, 5680, XenItems.protexide, 6740);
        }};
        diocasiumArmor = new Modifier("diocasium-armor"){{
            researchCost = ItemStack.with(XenItems.damascus, 18960, XenItems.zinc, 16770, XenItems.dantstalin, 11430, XenItems.gold, 9850, XenItems.protexide, 10860, XenItems.diocasium, 5640);
        }};
    }
}
