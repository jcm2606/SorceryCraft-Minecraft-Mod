package jcm2606.mods.sorcerycraft.item.wand;

import java.util.List;

import jcm2606.mods.jccore.helper.NBTHelper;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWandScroll extends SCItem {
	public ItemWandScroll(int par1) {
		super(par1, "wandScroll");
		this.setMaxStackSize(1);
	}
	
	public static void setActiveBehaviour(ItemStack itemStack, int id) {
		NBTTagCompound nbtTagCompound = NBTHelper
				.getNBTCompoundForItemStack(itemStack);
		nbtTagCompound.setInteger("ActiveBehaviour", id);
	}

	public static int getActiveBehaviour(ItemStack itemstack) {
		NBTTagCompound nbtTagCompound = NBTHelper
				.getNBTCompoundForItemStack(itemstack);

		int i = 0;
		
		if (nbtTagCompound != null) {
			i = nbtTagCompound.getInteger("ActiveBehaviour");
		}
		
		if(i >= WandManager.behaviourList.length || WandManager.behaviourList[i] == null)
		{
			i = 0;
		}

		return i;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	public void addInformation(ItemStack stack, EntityPlayer player, List list,
			boolean par4) {
		if (this.getActiveBehaviour(stack) != 0) {
			list.add(WandManager.behaviourList[this.getActiveBehaviour(stack)].name);
		}

		if (stack.getItemDamage() != 0) {
			list.add("Damaged (" + stack.getItemDamage() + ")");
		}
	}
}
