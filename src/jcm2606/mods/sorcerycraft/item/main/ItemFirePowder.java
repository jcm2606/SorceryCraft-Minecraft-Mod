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

public class ItemFirePowder extends SCItemShine {
	public ItemFirePowder(int par1) {
		super(par1, "dustFire");
		this.setMaxStackSize(16);
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
                list.add("Fiery powder which pulsates");
                list.add("with heat, even just at a touch.");
                list.add("It can be used as fuel in a");
                list.add("fuel-based smelting device, however");
                list.add("in a Smooth Furnace, this fuel burns");
                list.add("twice as hotter, but also twice as");
                list.add("quickly.");
            } else {
                list.add("<Hold SHIFT>");
            }
        }
    }
}
