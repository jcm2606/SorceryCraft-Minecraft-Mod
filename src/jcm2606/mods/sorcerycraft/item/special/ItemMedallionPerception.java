package jcm2606.mods.sorcerycraft.item.special;

import java.util.List;

import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

public class ItemMedallionPerception extends SCItem {
	public ItemMedallionPerception(int par1) {
		super(par1, "medallionPerception");
	}
	
	@Override
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4) {
	    if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode))
	    {
	        list.add("Grants the player the ability");
	        list.add("to see a lot more than what");
	        list.add("originally meets the eye.");
	        list.add("Also grants the player to see");
	        list.add("some extra information about");
	        list.add("certain constructs. Sneak MUST");
	        list.add("be held to see this information.");
	    } else {
	        list.add("<Hold SHIFT>");
	    }
	}
}
