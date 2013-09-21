package jcm2606.mods.sorcerycraft.block.main;

import jcm2606.mods.sorcerycraft.api.ITransmutable;
import jcm2606.mods.sorcerycraft.block.SCBlock;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockAlchMetal extends SCBlock implements ITransmutable
{
    public BlockAlchMetal(int par1)
    {
        super(par1, Material.iron, "alchMetalBlock", Rarities.BASIC);
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
    public Item[] getRequiredDevices()
    {
        return new Item[]
        { SCObjects.stoneArcane, SCObjects.stoneAstral };
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
