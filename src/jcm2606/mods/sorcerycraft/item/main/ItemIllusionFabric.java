package jcm2606.mods.sorcerycraft.item.main;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.helper.SCHelper;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

public class ItemIllusionFabric extends SCItemShine {
	public ItemIllusionFabric(int par1) {
		super(par1, "fabricIllusion");
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
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
                list.add("A special type of fabric");
                list.add("which is seamingly as durable");
                list.add("as the thoughtest of leathers,");
                list.add("but as flexible and workable as");
                list.add("the softest of silks. It can also");
                list.add("react with certain magical forces");
                list.add("to produce some interesting");
                list.add("effects.");
            } else {
                list.add("<Hold SHIFT>");
            }
        }
    }
}
