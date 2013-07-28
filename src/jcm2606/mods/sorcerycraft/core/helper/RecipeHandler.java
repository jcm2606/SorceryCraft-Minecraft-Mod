package jcm2606.mods.sorcerycraft.core.helper;

import jcm2606.mods.sorcerycraft.manager.CraftingManagerAlchLectern;
import jcm2606.mods.sorcerycraft.manager.CraftingManagerInfuseTablet;
import net.minecraft.item.ItemStack;

public class RecipeHandler {
	public static class InfusionTabletHelper {
		public static void addRecipe(ItemStack stack, Object... input) {
			CraftingManagerInfuseTablet.addRecipe(stack, input);
		}

		public static void addShapelessRecipe(ItemStack stack, Object... input) {
			CraftingManagerInfuseTablet.addShapelessRecipe(stack, input);
		}
	}

	public static class ArcaneWorkbenchHelper {
		public static void addRecipe(ItemStack stack, Object... input) {
			CraftingManagerAlchLectern.addRecipe(stack, input);
		}

		public static void addShapelessRecipe(ItemStack stack, Object... input) {
			CraftingManagerAlchLectern.addShapelessRecipe(stack, input);
		}
	}
}
