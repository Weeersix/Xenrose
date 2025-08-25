package xenrose.XenContent;

import mindustry.type.ItemStack;
import xenrose.type.Modifier;

public class BlocksModifiers {
    public static Modifier
            //Walls
            diocasiumArmor, healTechTree, reflectModifier, shieldModifier;

    public static void load(){

        //Walls
        reflectModifier = new Modifier("reflection-module"){{
            researchCost = ItemStack.with(XenItems.damascus, 1f);
        }};
        healTechTree = new Modifier("heal-tech-tree"){{
            researchCost = ItemStack.with(XenItems.damascus, 1f);
        }};
        diocasiumArmor = new Modifier("diocasium-armor"){{
            researchCost = ItemStack.with(XenItems.damascus, 1f);
        }};
        shieldModifier = new Modifier("shield-module"){{
            researchCost = ItemStack.with(XenItems.damascus, 1f);
        }};
    }
}
