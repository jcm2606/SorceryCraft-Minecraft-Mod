package jcm2606.mods.sorcerycraft.item.main;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.handler.AlchemyHandler;
import jcm2606.mods.sorcerycraft.helper.SCHelper;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAlchStone extends SCItem {
	public ItemAlchStone(int par1) {
		super(par1, "alchStone");
		setFull3D();
		setNoRepair();
		setMaxDamage(256);
		setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Return an item rarity from EnumRarity
	 */
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return RarityHelper.getCustomRarityType(Rarities.BASIC);
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
		return false;
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	/*
	 * @Override
	 * 
	 * @SideOnly(Side.CLIENT) public boolean requiresMultipleRenderPasses() {
	 * return true; }
	 * 
	 * @Override
	 * 
	 * @SideOnly(Side.CLIENT) public int func_82790_a(ItemStack par1ItemStack,
	 * int par2) { return 0xB1FB17; }
	 */

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		return AlchemyHandler.performInWorldTransmutation(stack, player, world, x, y, z);
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps
	 * to check if is on a player hand and update it's contents.
	 */
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {}
	
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
                list.add("A more mundane but still");
                list.add("arcane device which serves");
                list.add("one main purpose, reconstruction.");
                list.add("This stone has been imbued with a");
                list.add("light amount of energy, but enough");
                list.add("to allow the stone to break down");
                list.add("and reconstruct blocks and items.");
            } else {
                list.add("<Hold SHIFT>");
            }
        }
    }
}
