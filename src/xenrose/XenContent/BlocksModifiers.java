package xenrose.XenContent;

import mindustry.type.ItemStack;
import xenrose.type.ArmorModifier;
import xenrose.type.Modifier;
import xenrose.type.RepairModifier;

public class BlocksModifiers {
    public static Modifier
            //Walls
            diocasiumArmor, healTechTree;

    public static void load(){

                //Walls
                diocasiumArmor = new ArmorModifier("diocasium-armor"){{
                    researchCost = ItemStack.with(XenItems.damascus, 1f);
                }};
                healTechTree = new RepairModifier("heal-tech-tree"){{
                    researchCost = ItemStack.with(XenItems.damascus, 1f);
                }};
            }
}
