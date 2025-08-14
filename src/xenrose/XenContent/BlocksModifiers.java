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
                diocasiumArmor = new Modifier("diocasium-armor"){{
                    researchCost = ItemStack.with(XenItems.damascus, 1f);
                }};
                healTechTree = new SecondModifier("heal-tech-tree"){{
                    researchCost = ItemStack.with(XenItems.damascus, 1f);
                }};
            }
}
