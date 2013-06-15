package jcm2606.mods.sorcerycraft.item.charm;

import java.util.HashMap;
import java.util.List;

import jcm2606.mods.jccore.helper.NBTHelper;
import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCharm extends SCItem {
	public static HashMap<String, ICharmCurse> curseList = new HashMap<String, ICharmCurse>();
	boolean displayInfo = true;
	boolean applyCursesOnTick = false;
	
	public ItemCharm(int par1, String par2) {
		super(par1, par2);
		this.setMaxStackSize(1);
		this.setNoRepair();
	}
	
	public static void registerCurses()
	{
		registerCurse(new Class<?>[] {
				CurseHunger.class,
				CurseBurn.class,
				CurseBlindness.class,
				CurseDisarm.class
				});
	}
	
	/**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
    	if(this.applyCursesOnTick)
    	{
    		EntityLiving living = (EntityLiving) par3Entity;
    		EntityPlayer player = (EntityPlayer) living;
    		this.onCursedUse(par1ItemStack, player, par2World, this.getCurseName(par1ItemStack));
    	}
    }
	
	public static void registerCurse(Class<?> clas)
	{
		Object obj = null;
		try {
			obj = clas.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if(obj != null)
		{
			if(obj instanceof ICharmCurse)
			{
				ICharmCurse curse = (ICharmCurse) obj;
				
				curseList.put(curse.getCurseName(), curse);
			} else {
				throw new RuntimeException("Class parsed not an instance of ICharmCurse.");
			}
		} else {
			throw new RuntimeException("Unexpected error occured, could not get new instance of class parsed.");
		}
	}
	
	public static void registerCurse(Class<?>[] clasList)
	{
		for(Class<?> clas : clasList)
		{
			registerCurse(clas);
		}
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return RarityHelper.getCustomRarityType(Rarities.CHARM);
	}
	
	public static void setCurseOwner(ItemStack itemStack, String name)
    {
       NBTTagCompound nbtTagCompound = NBTHelper.getNBTCompoundForItemStack(itemStack);
       nbtTagCompound.setString("CurseOwner", name);
    }
    
    public static String getCurseOwner(ItemStack itemstack)
    {
		NBTTagCompound nbtTagCompound = NBTHelper.getNBTCompoundForItemStack(itemstack);
		
		if(nbtTagCompound != null)
		{
			return nbtTagCompound.getString("CurseOwner");
		}
		
		return "";
    }
    
    public static void setCurseName(ItemStack itemStack, String name)
    {
       NBTTagCompound nbtTagCompound = NBTHelper.getNBTCompoundForItemStack(itemStack);
       nbtTagCompound.setString("Curse", name);
    }
    
    public static String getCurseName(ItemStack itemstack)
    {
		NBTTagCompound nbtTagCompound = NBTHelper.getNBTCompoundForItemStack(itemstack);
		
		if(nbtTagCompound != null)
		{
			return nbtTagCompound.getString("Curse");
		}
		
		return "";
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	this.onCursedUse(par1ItemStack, par3EntityPlayer, par2World, this.getCurseName(par1ItemStack));
    	
        return par1ItemStack;
    }
    
    public void onCursedUse(ItemStack stack, EntityPlayer player, World world, String curseName) {
    	if(curseName.equals("hunger"))
    	{
    		player.addPotionEffect(new PotionEffect(Potion.hunger.id, 400, 10));
    	}
    	
    	if(curseName.equals("burn"))
    	{
    		player.setFire(10);
    	}
    	
    	if(curseName.equals("blindness"))
    	{
    		player.addPotionEffect(new PotionEffect(Potion.blindness.id, 200, 20));
    	}
    	
    	if(curseName.equals("disarm"))
    	{
		    if(stack != null && player.getCurrentEquippedItem() == stack && stack.getItem() instanceof ItemCharm)
		    {
		        player.dropPlayerItem(stack);
                player.inventory.mainInventory[player.inventory.currentItem] = null;
		    }
    	}
    }
    
    @Override
	@SideOnly(Side.CLIENT)
	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
    	if(this.displayInfo || this.getCurseName(stack) != "")
    	{
    		if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode)) {
        		this.getSubtext(stack, player, player.worldObj, list);
        		
        		if(getCurseOwner(stack).equals(player.username) || getCurseOwner(stack).equals("~CHEST_GENERATED"))
        		{
        			if(!getCurseName(stack).equals(""))
        			{
        				String localName = "ERROR";
        				String colourPrefix = "\247c";
        				
        				for(int i = 0; i < curseList.size(); i++)
        				{
        					if(curseList.get(getCurseName(stack)) != null)
        					{
        						ICharmCurse curse = curseList.get(getCurseName(stack));
        						
        						localName = curse.getCurseNameLocal();
        						
        						if(curse.isCursePositive())
        						{
        							colourPrefix = "\247a";
        						}
        					}
        				}
        				
        				
        				list.add("\247oThis charm has been cursed with " + colourPrefix + "\247o" + localName + "\2477.");
        			} else {
        				list.add("\247oThis charm is clean.");
        			}
        		}
    		} else {
    			list.add("Hold <SHIFT>");
    		}
    	}
	}
    
    public void getSubtext(ItemStack stack, EntityPlayer player, World world, List list) {}
}
