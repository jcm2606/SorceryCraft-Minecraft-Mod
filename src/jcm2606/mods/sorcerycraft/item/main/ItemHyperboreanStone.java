package jcm2606.mods.sorcerycraft.item.main;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHyperboreanStone extends ItemVordicDevice
{
    public ItemHyperboreanStone(int par1)
    {
        super(par1, "stoneHyperborean");
        this.setMaxDamage(121);
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
    {
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase living = (EntityLiving) entity;
            
            if (living instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) living;
                
                for (int i = 0; i <= 8; i++)
                {
                    ItemStack item = player.inventory.getStackInSlot(i);
                    
                    if (item != null && item == stack && item.getItemDamage() < item.getMaxDamage())
                    {
                        if (player.isBurning())
                        {
                            player.extinguish();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Callback for item usage. If the item does something special on right
     * clicking, he will have one of those. Return True if something happen and
     * false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
        /*
         * int id = world.getBlockId(x, y, z);
         * 
         * if(id == Block.waterStill.blockID || id == Block.waterMoving.blockID)
         * { world.setBlock(x, y, z, Block.ice.blockID);
         * 
         * performEffects(world, player, x, y, z);
         * 
         * stack.damageItem(1, player); } else if(id == Block.lavaStill.blockID
         * || id == Block.lavaMoving.blockID) { world.setBlock(x, y, z,
         * Block.obsidian.blockID);
         * 
         * performEffects(world, player, x, y, z);
         * 
         * stack.damageItem(1, player); } else { world.playSoundAtEntity(player,
         * "sorcerycraft.magic_fail", 1.0f, 1.0f); }
         */
        
        if (par7 == 5)
        {
            int id = world.getBlockId(x + 1, y, z);
            
            if (id == Block.waterStill.blockID || id == Block.waterMoving.blockID)
            {
                world.setBlock(x + 1, y, z, Block.ice.blockID, 0, 0x02);
                
                performEffects(world, player, x + 1, y, z);
                
                stack.damageItem(1, player);
            } else
                if (id == Block.lavaStill.blockID)
                {
                    world.setBlock(x + 1, y, z, Block.obsidian.blockID, 0, 0x02);
                    
                    performEffects(world, player, x + 1, y, z);
                    
                    stack.damageItem(1, player);
                } else
                    if (id == Block.lavaMoving.blockID)
                    {
                        world.setBlock(x + 1, y, z, Block.cobblestone.blockID, 0, 0x02);
                        
                        performEffects(world, player, x + 1, y, z);
                        
                        stack.damageItem(1, player);
                    }
        }
        
        if (par7 == 2)
        {
            int id = world.getBlockId(x, y, z - 1);
            
            if (id == Block.waterStill.blockID || id == Block.waterMoving.blockID)
            {
                world.setBlock(x, y, z - 1, Block.ice.blockID, 0, 0x02);
                
                performEffects(world, player, x, y, z - 1);
                
                stack.damageItem(1, player);
            } else
                if (id == Block.lavaStill.blockID)
                {
                    world.setBlock(x, y, z - 1, Block.obsidian.blockID, 0, 0x02);
                    
                    performEffects(world, player, x, y, z - 1);
                    
                    stack.damageItem(1, player);
                } else
                    if (id == Block.lavaMoving.blockID)
                    {
                        world.setBlock(x + 1, y, z, Block.cobblestone.blockID, 0, 0x02);
                        
                        performEffects(world, player, x + 1, y, z);
                        
                        stack.damageItem(1, player);
                    }
        }
        
        if (par7 == 4)
        {
            int id = world.getBlockId(x - 1, y, z);
            
            if (id == Block.waterStill.blockID || id == Block.waterMoving.blockID)
            {
                world.setBlock(x - 1, y, z, Block.ice.blockID, 0, 0x02);
                
                performEffects(world, player, x - 1, y, z);
                
                stack.damageItem(1, player);
            } else
                if (id == Block.lavaStill.blockID)
                {
                    world.setBlock(x - 1, y, z, Block.obsidian.blockID, 0, 0x02);
                    
                    performEffects(world, player, x - 1, y, z);
                    
                    stack.damageItem(1, player);
                } else
                    if (id == Block.lavaMoving.blockID)
                    {
                        world.setBlock(x + 1, y, z, Block.cobblestone.blockID, 0, 0x02);
                        
                        performEffects(world, player, x + 1, y, z);
                        
                        stack.damageItem(1, player);
                    }
        }
        
        if (par7 == 3)
        {
            int id = world.getBlockId(x, y, z + 1);
            
            if (id == Block.waterStill.blockID || id == Block.waterMoving.blockID)
            {
                world.setBlock(x, y, z + 1, Block.ice.blockID, 0, 0x02);
                
                performEffects(world, player, x, y, z + 1);
                
                stack.damageItem(1, player);
            } else
                if (id == Block.lavaStill.blockID)
                {
                    world.setBlock(x, y, z + 1, Block.obsidian.blockID, 0, 0x02);
                    
                    performEffects(world, player, x, y, z + 1);
                    
                    stack.damageItem(1, player);
                } else
                    if (id == Block.lavaMoving.blockID)
                    {
                        world.setBlock(x + 1, y, z, Block.cobblestone.blockID, 0, 0x02);
                        
                        performEffects(world, player, x + 1, y, z);
                        
                        stack.damageItem(1, player);
                    }
        }
        
        return true;
    }
    
    public void performEffects(World world, EntityPlayer player, int x, int y, int z)
    {
        Random rand = new Random();
        
        for (int k = 0; k < 8; k++)
        {
            world.spawnParticle("cloud", x + 0.5 + (rand.nextGaussian() / 4), y + 0.9, z + 0.5 + (rand.nextGaussian() / 4), 0, 0.00000001, 0);
            world.spawnParticle("cloud", x + 0.5 - (rand.nextGaussian() / 4), y + 0.9, z + 0.5 - (rand.nextGaussian() / 4), 0, 0.00000001, 0);
        }
        
        world.spawnParticle("largeexplode", x + 0.5, y + 0.5, z + 0.5, 0, 0, 0);
    }
}
