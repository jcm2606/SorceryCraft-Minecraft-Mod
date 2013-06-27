package jcm2606.mods.sorcerycraft.item.main;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.helper.SCHelper;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

public class ItemEndEssence extends SCItem {
	public ItemEndEssence(int par1) {
		super(par1, "endEssence");
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return RarityHelper.getCustomRarityType(Rarities.BASIC);
	}
	
	@Override
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4) {
        if(SCHelper.playerHasPerceptionMedallion(player))
        {
            if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode))
            {
                list.add("A special type of dust which is saturated with");
                list.add("the mysterious energy that the End holds.");
                list.add("Ancient Sorcerer's hypothesised that these");
                list.add("types of dust were somehow linked to the Astral,");
                list.add("seeing how the End is literally right next to the");
                list.add("Astral.");
            } else {
                list.add("<Hold SHIFT>");
            }
        }
    }
}
