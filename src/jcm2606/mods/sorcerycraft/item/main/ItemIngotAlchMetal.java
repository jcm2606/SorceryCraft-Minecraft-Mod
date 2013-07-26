package jcm2606.mods.sorcerycraft.item.main;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.helper.SCHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

public class ItemIngotAlchMetal extends SCItemShine {
	public ItemIngotAlchMetal(int par1) {
		super(par1, "ingotAlchMetal");
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
                list.add("An extremely magical type");
                list.add("of metallic alloy which");
                list.add("seems to be the favourite");
                list.add("material to use to build");
                list.add("some arcane constructs, notely");
                list.add("the cores. While this metal is");
                list.add("magical, it isn't strong enough");
                list.add("to withstand the immense energies");
                list.add("that Astral constructs constantly");
                list.add("radiate.");
            } else {
                list.add("<Hold SHIFT>");
            }
        }
    }
}
