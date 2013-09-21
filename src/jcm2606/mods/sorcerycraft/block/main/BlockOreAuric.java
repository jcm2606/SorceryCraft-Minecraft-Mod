package jcm2606.mods.sorcerycraft.block.main;

import java.util.Random;

import jcm2606.mods.sorcerycraft.api.IEnergyInfused;
import jcm2606.mods.sorcerycraft.api.ITransmutable;
import jcm2606.mods.sorcerycraft.block.SCOre;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockOreAuric extends SCOre implements ITransmutable, IEnergyInfused
{
    public BlockOreAuric(int par1)
    {
        super(par1, "oreAuric");
        this.setHardness(5f);
    }
    
    @Override
    public int quantityDropped(Random rand)
    {
        return 1 + rand.nextInt(2);
    }
    
    @Override
    public int damageDropped(int par1)
    {
        Random rand = new Random();
        
        return rand.nextInt(100) <= 25 ? 1 : 2;
    }
    
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return SCObjects.gemAura.itemID;
    }
    
    @Override
    public Block getRequiredBlock(ItemStack stack)
    {
        return Block.oreDiamond;
    }
    
    @Override
    public Item[] getRequiredDevices()
    {
        return new Item[]
        { SCObjects.stoneAstral };
    }
    
    @Override
    public int getTransmuteCost(ItemStack stack, Block block)
    {
        return 32;
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
    
    @Override
    public boolean destroyBlockWhenExtracted()
    {
        return true;
    }
    
    @Override
    public int getCharge()
    {
        return 2;
    }
    
    @Override
    public int getDestroyChance()
    {
        return 10;
    }
}
