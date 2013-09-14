package jcm2606.mods.sorcerycraft.core.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class ForgeHookHandler
{
    
    public static void addCustomChestGenContent(String category, ItemStack stack, int minStackSize, int maxStackSize, int rarity)
    {
        ChestGenHooks.getInfo(category).addItem(new WeightedRandomChestContent(stack, minStackSize, maxStackSize, rarity));
    }
    
}
