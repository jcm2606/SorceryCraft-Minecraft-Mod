package jcm2606.mods.sorcerycraft.item.note;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.SorceryCraft;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNote extends SCItem {
	public int guiID;
	public boolean isBlank;
	
	public ItemNote(int par1, String par2) {
		super(par1, par2);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return RarityHelper.getCustomRarityType(Rarities.BASIC);
	}
	
	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world,
			EntityPlayer player) {
		if(!isBlank)
		{
			player.openGui(SorceryCraft.instance, guiID, world, (int) player.posX, (int) player.posY, (int) player.posZ);
		}

		return stack;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
		if(this.isBlank)
		{
			this.itemIcon = par1IconRegister.registerIcon("sorcerycraft:noteBlank");
		} else {
			this.itemIcon = par1IconRegister.registerIcon("sorcerycraft:noteWritten");
		}
    }
}
