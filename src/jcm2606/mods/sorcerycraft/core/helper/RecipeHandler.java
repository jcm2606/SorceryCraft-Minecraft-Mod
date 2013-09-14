package jcm2606.mods.sorcerycraft.core.helper;

import jcm2606.mods.sorcerycraft.manager.CraftingManagerArcaneWorkbench;
import jcm2606.mods.sorcerycraft.manager.CraftingManagerAstralInfuser;
import net.minecraft.item.ItemStack;

public class RecipeHandler
{
    public static class ArcaneWorkbenchHelper
    {
        public static void addRecipe(ItemStack stack, Object... input)
        {
            CraftingManagerArcaneWorkbench.addRecipe(stack, input);
        }
        
        public static void addShapelessRecipe(ItemStack stack, Object... input)
        {
            CraftingManagerArcaneWorkbench.addShapelessRecipe(stack, input);
        }
    }
    
    public static class AstralInfuserHelper
    {
        public static void addInfusion(ItemStack result, ItemStack[] input)
        {
            CraftingManagerAstralInfuser.getInstance().addInfusion(result, input);
        }
    }
}
