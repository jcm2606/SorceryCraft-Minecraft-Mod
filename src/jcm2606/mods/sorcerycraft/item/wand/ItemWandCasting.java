package jcm2606.mods.sorcerycraft.item.wand;

import java.util.List;

import jcm2606.mods.jccore.helper.NBTHelper;
import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.config.Settings;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWandCasting extends ItemWand {
	public ItemWandCasting(int par1) {
		super(par1, "wandCasting");
		this.setMaxDamage(120);
	}

	@Override
    public boolean shouldRotateAroundWhenRendering() {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return RarityHelper.getCustomRarityType(Rarities.BASIC);
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
			int x, int y, int z, int par7, float par8, float par9, float par10) {
		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			if (this.getActiveBehaviour(stack) >= WandManager.behaviourList.length - 1) {
				this.setActiveBehaviour(stack, 0);
			} else {
				this.setActiveBehaviour(stack, this.getActiveBehaviour(stack) + 1);
			}

			if (world.isRemote && Settings.WAND_CASTING_BEHAVIOUR_SWITCH_MESSAGE) {
				player.addChatMessage("Wand behaviour changed to \247o" + WandManager.getBehaviour(this.getActiveBehaviour(stack)).name + "\247r.");
			}

			return false;
		} else
		if(player.isSneaking()){
			return WandManager.performCastingWandBlockInteractionEffect(stack, world, player, x, y, z);
		} else {
			if (this.getActiveBehaviour(stack) != 0) {
				if(WandManager.performBehaviour(stack, world, player, x, y, z, WandManager.behaviourList[this.getActiveBehaviour(stack)]))
				{
					if(WandManager.getBehaviour(this.getActiveBehaviour(stack)).useCustomWandSound())
					{
						WandManager.getBehaviour(this.getActiveBehaviour(stack)).wandSound(stack, player, world, x, y, z, true);
					} else {
						this.playWandSound("sorcerycraft.wand_use", player, world, 0.4f, 1.0f);
					}
					
					return true;
				}
			} else {
				return WandManager.performCastingWandBlockInteractionEffect(stack, world, player, x, y, z);
			}
		}
		
		return false;
	}

	@Override
    public boolean itemInteractionForEntity(ItemStack stack,
			EntityLiving entity) {
	    if (this.getActiveBehaviour(stack) != 0) {
            if(WandManager.performBehaviourEntity(stack, entity, WandManager.getBehaviour(this.getActiveBehaviour(stack)), entity.worldObj))
            {
                return true;
            } else {
                return false;
            }
        } else {
            return WandManager.performCastingWandEntityInteractionEffect(stack, entity.worldObj, entity);
        }
	}

	public void playWandSound(String sound, EntityPlayer player, World world,
			float volume, float frequency) {
		playWandSound(sound, player, world, volume, frequency, 0.0f);
	}

	public void playWandSound(String sound, EntityPlayer player, World world,
			float volume, float frequency, float multiplier) {
		playWandSound(sound, player, world, volume, frequency, multiplier,
				multiplier);
	}

	public void playWandSound(String sound, EntityPlayer player, World world,
			float volume, float frequency, float multiplier, float multiplier2) {
		world.playSoundAtEntity(player, sound, volume + multiplier, frequency
				+ multiplier2);
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps
	 * to check if is on a player hand and update it's contents.
	 */
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {
	}

	public static void setActiveBehaviour(ItemStack itemStack, int id) {
		NBTTagCompound nbtTagCompound = NBTHelper
				.getNBTCompoundForItemStack(itemStack);
		nbtTagCompound.setInteger("ActiveBehaviour", id);
	}

	public static int getActiveBehaviour(ItemStack itemstack) {
		NBTTagCompound nbtTagCompound = NBTHelper
				.getNBTCompoundForItemStack(itemstack);

		int i = -1;
		
		if (nbtTagCompound != null) {
			i = nbtTagCompound.getInteger("ActiveBehaviour");
		}
		
		if(WandManager.behaviourList[i] == null)
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
