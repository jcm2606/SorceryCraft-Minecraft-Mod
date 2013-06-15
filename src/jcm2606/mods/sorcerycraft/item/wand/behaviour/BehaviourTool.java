package jcm2606.mods.sorcerycraft.item.wand.behaviour;

import jcm2606.mods.sorcerycraft.item.SCPick;
import jcm2606.mods.sorcerycraft.item.SCShovel;
import jcm2606.mods.sorcerycraft.item.tool.ItemOmniPickaxe;
import jcm2606.mods.sorcerycraft.item.wand.EnumWandBehaviour;
import jcm2606.mods.sorcerycraft.item.wand.ItemWandCasting;
import jcm2606.mods.sorcerycraft.item.wand.WandManager;
import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BehaviourTool extends Behaviour implements IBlockInteractionHandler {
	public BehaviourTool() {
		super(WandManager.getNextAvailableBehaviourId(), "Block Shatter", EnumWandBehaviour.blockInteract);
		this.setBehaviourClass(this);
		this.icon = Reference.PATH_TEXTURES_GUI_HUD + "wand/wand_tool.png";
	}

	@Override
	public Block getBlock(ItemStack stack, EntityPlayer player) {
		return null;
	}

	@Override
	public boolean onBlockInteract(ItemStack wand, ItemStack stack,
			EntityPlayer player, World world, int x, int y, int z) {
		boolean breakRock = false;
		boolean breakEarth = false;
		
		for(int i = 0; i < player.inventory.getHotbarSize(); i++)
		{
			if(player.inventory.getStackInSlot(i) != null)
			{
				Item item = player.inventory.getStackInSlot(i).getItem();
				ItemStack itemStack = player.inventory.getStackInSlot(i);
				
				if(item instanceof ItemPickaxe || item instanceof SCPick || item instanceof ItemOmniPickaxe)
				{
					breakRock = true;
				}
				
				if(item instanceof ItemSpade || item instanceof SCShovel || item instanceof ItemOmniPickaxe)
				{
					breakEarth = true;
				}
			}
		}
		
		if(breakRock)
		{
			for(Block block : Block.blocksList)
			{
				if(block != null)
				{
					if(block.blockMaterial == Material.rock || block.blockMaterial == Material.iron)
					{
						if(block != Block.bedrock)
						{
							if(block.blockID == world.getBlockId(x, y, z))
							{
								world.setBlock(x, y, z, 0);
								block.dropBlockAsItem(world, x, y, z, 0, 0);
								
								for(int i = 0; i < player.inventory.getHotbarSize(); i++)
								{
									if(player.inventory.getStackInSlot(i) != null)
									{
										Item item = player.inventory.getStackInSlot(i).getItem();
										ItemStack itemStack = player.inventory.getStackInSlot(i);
										
										if(item instanceof ItemPickaxe || item instanceof SCPick)
										{
											itemStack.damageItem(4, player);
											break;
										}
									}
								}
								
								if(block instanceof BlockOre)
								{
									if(world.rand.nextInt(100) < 6)
									{
										block.dropBlockAsItem(world, x, y, z, 0, 0);
										wand.damageItem(1, player);
									}
								}
							}
						}
					}
				}
			}
		}
		
		if(breakEarth)
		{
			for(Block block : Block.blocksList)
			{
				if(block != null)
				{
					if(block.blockMaterial == Material.grass || block.blockMaterial == Material.ground)
					{
						if(block != Block.bedrock)
						{
							if(block.blockID == world.getBlockId(x, y, z))
							{
								world.setBlock(x, y, z, 0);
								block.dropBlockAsItem(world, x, y, z, 0, 0);
								
								for(int i = 0; i < player.inventory.getHotbarSize(); i++)
								{
									if(player.inventory.getStackInSlot(i) != null)
									{
										Item item = player.inventory.getStackInSlot(i).getItem();
										ItemStack itemStack = player.inventory.getStackInSlot(i);
										
										if(item instanceof ItemSpade || item instanceof SCShovel)
										{
											itemStack.damageItem(4, player);
										}
									}
								}
								
								if(block instanceof BlockOre)
								{
									if(world.rand.nextInt(100) < 6)
									{
										block.dropBlockAsItem(world, x, y, z, 0, 0);
										wand.damageItem(1, player);
									}
								}
							}
						}
					}
				}
			}
		}
		
		return breakRock || breakEarth;
	}

	@Override
	public Item reqItem(ItemStack wand) {
		return null;
	}

	@Override
	public void wandSound(ItemStack wand,
			EntityPlayer player, World world, int x, int y, int z, boolean wasSuccessful) {
		boolean breakRock = false;
		boolean breakEarth = false;
		
		for(int i = 0; i < player.inventory.getHotbarSize(); i++)
		{
			if(player.inventory.getStackInSlot(i) != null)
			{
				Item item = player.inventory.getStackInSlot(i).getItem();
				ItemStack itemStack = player.inventory.getStackInSlot(i);
				
				if(item instanceof ItemPickaxe || item instanceof SCPick)
				{
					breakRock = true;
				}
				
				if(item instanceof ItemSpade || item instanceof SCShovel)
				{
					breakEarth = true;
				}
			}
		}
		
		if(breakRock)
		{
			for(Block block : Block.blocksList)
			{
				if(block != null)
				{
					if(block.blockMaterial == Material.rock || block.blockMaterial == Material.iron)
					{
						if(block != Block.bedrock)
						{
							if(block.blockID == world.getBlockId(x, y, z))
							{
								ItemWandCasting wandItem = (ItemWandCasting) wand.getItem();
								
								wandItem.playWandSound(block.stepSound.getBreakSound(), player, world, 1.0f, 0.85f);
								wandItem.playWandSound("sorcerycraft.wand_use", player, world, 0.1f, 1.0f);
							}
						}
					}
				}
			}
		}
		
		if(breakEarth)
		{
			for(Block block : Block.blocksList)
			{
				if(block != null)
				{
					if(block.blockMaterial == Material.grass || block.blockMaterial == Material.ground)
					{
						if(block != Block.bedrock)
						{
							if(block.blockID == world.getBlockId(x, y, z))
							{
								ItemWandCasting wandItem = (ItemWandCasting) wand.getItem();
								
								wandItem.playWandSound(block.stepSound.getBreakSound(), player, world, 1.0f, 0.85f);
								wandItem.playWandSound("sorcerycraft.wand_use", player, world, 0.1f, 1.0f);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public boolean useCustomWandSound() {
		return true;
	}
}
