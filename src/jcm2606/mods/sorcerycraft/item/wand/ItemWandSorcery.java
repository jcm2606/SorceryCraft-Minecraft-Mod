package jcm2606.mods.sorcerycraft.item.wand;

import java.util.List;

import jcm2606.mods.jccore.helper.NBTHelper;
import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWandSorcery extends ItemWand {
	public ItemWandSorcery(int par1) {
		super(par1, "wandSorcery");
		this.setMaxStackSize(1);
		this.setMaxDamage(128);
		this.setNoRepair();
	}

	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return RarityHelper.getCustomRarityType(Rarities.MAGICAL);
	}
	
	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps
	 * to check if is on a player hand and update it's contents.
	 */
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {}
	
	public static void setMode(ItemStack itemStack, String name)
    {
       NBTTagCompound nbtTagCompound = NBTHelper.getNBTCompoundForItemStack(itemStack);
       nbtTagCompound.setString("Mode", name);
    }
    
    public static String getMode(ItemStack itemstack)
    {
		NBTTagCompound nbtTagCompound = NBTHelper.getNBTCompoundForItemStack(itemstack);
		
		if(nbtTagCompound != null)
		{
			return nbtTagCompound.getString("Mode");
		}
		
		return "";
    }
    
    @Override
	@SideOnly(Side.CLIENT)
	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
    	
	}
}
