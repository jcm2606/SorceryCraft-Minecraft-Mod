package jcm2606.mods.sorcerycraft.item.wand.behaviour;

import jcm2606.mods.sorcerycraft.item.wand.EnumWandBehaviour;
import jcm2606.mods.sorcerycraft.item.wand.ItemWandCasting;
import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class Behaviour {
	public int id;
	public Object behaviourClass;
	public EnumWandBehaviour behaviourType;
	public String name;
	public String icon;
	
	public Behaviour(int i, String s, EnumWandBehaviour behaviour)
	{
		id = i + 1;
		behaviourType = behaviour;
		behaviourClass = this;
		name = s;
		icon = Reference.PATH_TEXTURES_GUI_HUD + "wand/wand_normal.png";
	}
	
	public void setBehaviourClass(Object clas)
	{
		this.behaviourClass = clas;
	}
	
	public Object getBehaviourClass()
	{
		return this.behaviourClass;
	}
	
	public void setBehaviourType(EnumWandBehaviour clas)
	{
		this.behaviourType = clas;
	}
	
	public EnumWandBehaviour getBehaviourType()
	{
		return this.behaviourType;
	}
	
	/**
	 * Hook for custom wand sounds, return true for custom sound handling, false for default.
	 * 
	 * @param wand
	 * @param stack
	 * @param player
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 */
	public void wandSound(ItemStack wand, EntityPlayer player, World world, int x, int y, int z, boolean wasSuccessful)
	{
		ItemWandCasting wandCasting = (ItemWandCasting) wand.getItem();
		
		wandCasting.playWandSound("sorcerycraft.wand_use", player, world, 0.8f, 1.0f);
	}
	
	public abstract boolean useCustomWandSound();
}