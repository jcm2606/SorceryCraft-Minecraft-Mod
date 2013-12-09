package jcm2606.mods.sorcerycraft.block.main;

import jcm2606.mods.sorcerycraft.api.ITransmutable;
import jcm2606.mods.sorcerycraft.block.SCBlock;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockArcaneSteel extends SCBlock implements ITransmutable
{
    public BlockArcaneSteel(int par1)
    {
        super(par1, Material.iron, "blockArcaneSteel", Rarities.BASIC);
        this.setHardness(7.0f);
        this.setResistance(4.0f);
    }
    
    @Override
    public Block getRequiredBlock(ItemStack stack)
    {
        return Block.blockIron;
    }
    
    @Override
    public int getTransmuteCost(ItemStack stack, Block block)
    {
        return 10;
    }
    
    @Override
    public void onTransmute(ItemStack stack, Block block, EntityPlayer player, World world, int x, int y, int z)
    {
    }
    
    @Override
    public int getRequiredBlockMetadata(ItemStack stack)
    {
        return 0;
    }
    
    @Override
    public int getMetadataToChangeTo()
    {
        return 0;
    }
}
