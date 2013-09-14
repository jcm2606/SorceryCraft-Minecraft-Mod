package jcm2606.mods.sorcerycraft.api;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ITransmutable
{
    public int getMetadataToChangeTo();
    
    public Block getRequiredBlock(ItemStack stack);
    
    public int getRequiredBlockMetadata(ItemStack stack);
    
    public Item[] getRequiredDevices();
    
    public int getTransmuteCost(ItemStack stack, Block block);
    
    public void onTransmute(ItemStack stack, Block block, EntityPlayer player, World world, int x, int y, int z);
}
