package jcm2606.mods.sorcerycraft.helper;

import jcm2606.mods.sorcerycraft.manager.CraftingManagerAlchLectern;
import jcm2606.mods.sorcerycraft.manager.CraftingManagerInfuseTablet;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeHelper {
	public static class InfusionTabletHelper {
		public static void addRecipe(ItemStack stack, Object... input) {
			CraftingManagerInfuseTablet.addRecipe(stack, input);
		}

		public static void addShapelessRecipe(ItemStack stack, Object... input) {
			CraftingManagerInfuseTablet.addShapelessRecipe(stack, input);
		}
	}

	public static class AlchemicalPodiumHelper {
		public static void addRecipe(ItemStack stack, Object... input) {
			CraftingManagerAlchLectern.addRecipe(stack, input);
		}

		public static void addShapelessRecipe(ItemStack stack, Object... input) {
			CraftingManagerAlchLectern.addShapelessRecipe(stack, input);
		}
	}
	
	public static void addUniversalRecipe(ItemStack stack, Object... input)
	{
		GameRegistry.addRecipe(stack, input);
		AlchemicalPodiumHelper.addRecipe(stack, input);
	}
	
	public static void addUniversalShapelessRecipe(ItemStack stack, Object... input)
	{
		GameRegistry.addShapelessRecipe(stack, input);
		AlchemicalPodiumHelper.addShapelessRecipe(stack, input);
	}
}
