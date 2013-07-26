package jcm2606.mods.sorcerycraft.block.main;

import jcm2606.mods.sorcerycraft.api.IWorkable;
import jcm2606.mods.sorcerycraft.block.SCBlock;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class BlockObsidianFalse extends SCBlock implements IWorkable {
	public BlockObsidianFalse(int par1, Material par3Material) {
		super(par1, par3Material, "falseObsidian");
		this.setHardness(2.5f);
		this.setResistance(2.0f);
	}
	
	@Override
	public Block getBlockForWorking(ItemStack stack) {
		return Block.obsidian;
	}

	@Override
	public int getWorkCost(ItemStack stack, Block block) {
		return 32;
	}

	@Override
	public void onWorking(ItemStack stack, Block block, EntityPlayer player, World world, int x, int y, int z) {
		if(!world.isRemote)
		{
			EntityItem i = new EntityItem(world, x, y, z, new ItemStack(SCObjects.obsidianingot, 1 + world.rand.nextInt(3)));
			
			i.motionX = 0.0;
			i.motionZ = 0.0;
			
			world.spawnEntityInWorld(i);
		}
		
		world.playSoundEffect( x + 0.5D, y + 0.5D, z + 0.5D, "dig.stone", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
		world.setBlock(x, y, z, 0, 0, 0x02);
	}
}
