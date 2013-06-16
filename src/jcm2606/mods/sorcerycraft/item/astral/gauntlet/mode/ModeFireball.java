package jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode;

import java.util.Random;

import jcm2606.mods.sorcerycraft.SCParticle;
import jcm2606.mods.sorcerycraft.config.Settings;
import jcm2606.mods.sorcerycraft.fx.EntitySearFlameFX;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.AstralGauntletManager;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;

public class ModeFireball extends GauntletMode {
    public ModeFireball() {
        super(AstralGauntletManager.getNextAvailableId(), "Handheld Fireball", "");
    }

    @Override
    public boolean onUse(String type, ItemStack stack, World world, EntityPlayer player, EntityLiving living, int x, int y, int z, int side)
    {
        if(type.equals(this.USE_ENTITY_ATTACK))
        {
            living.setFire(10);
            
            return false;
        } else
            if(type.equals(this.USE_BLOCK_ACTIVATE))
            {
                world.playSound(x, y, z, "mob.ghast.fireball", 0.5f, 1.0f, false);
                
                return attemptSmeltingAt(world, player, stack, x, y, z, world.getBlockId(x, y, z));
            }
        
        return false;
    }

    @Override
    public void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem)
    {
        if(isCurrentItem && world.isRemote)
        {
            double adjAngle = 35.0D;
            double dist = 0.4D;
        
            EntityPlayer center = player;
        
            double posX = center.posX - Math.cos((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
            double posY = center.posY - Math.sin(center.rotationPitch / 540.0F * Math.PI) * dist;
            double posZ = center.posZ + Math.sin((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
            
            Random rand = new Random();
            float speed = 0.02F;
            
            EntitySearFlameFX fx = new EntitySearFlameFX(Minecraft.getMinecraft().theWorld, posX, posY, posZ, (rand.nextFloat() - rand.nextFloat()) * speed, (rand.nextFloat() - rand.nextFloat()) * speed, (rand.nextFloat() - rand.nextFloat()) * speed);
            fx.maxAge = 80;
            
            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
        }
    }
    
    public boolean attemptSmeltingAt(World world, EntityPlayer player, ItemStack stack, int x, int y, int z, int idR)
    {
        int id = world.getBlockId(x, y, z);
        
        if(id == 0)
        {
            return false;
        }
        
        if(id != idR)
        {
            return false;
        }
        
        ItemStack smelted = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(Block.blocksList[id]));
        
        if (smelted == null) {
            return false;
        }
        
        if (!world.isRemote) {
            if (smelted.getItem() instanceof ItemBlock) {
                world.setBlock(x, y, z, smelted.getItem().itemID);
              }
              else {
                player.entityDropItem(new ItemStack(smelted.getItem().itemID, 1, smelted.getItemDamage()), 0.0F);
                world.setBlock(x, y, z, 0);
              }
        }
        
        for(int i = 0; i < 32; i++)
        {
            world.spawnParticle("smoke", x + 0.5 - (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3), z + 0.5 - (world.rand.nextGaussian() / 3), 0, 0, 0);
            world.spawnParticle("smoke", x + 0.5 + (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3), z + 0.5 + (world.rand.nextGaussian() / 3), 0, 0, 0);
        }
        
        for(int i = 0; i < 8; i++)
        {
            if(Minecraft.isFancyGraphicsEnabled() || Settings.RING_MAGMA_FANCY_PARTICLES)
            {
                SCParticle.spawnSearFlameFX(x + 0.5 - (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3), z + 0.5 - (world.rand.nextGaussian() / 3), 0, 0.001, 0, false);
                SCParticle.spawnSearFlameFX(x + 0.5 + (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3), z + 0.5 + (world.rand.nextGaussian() / 3), 0, 0.001, 0, false);
            } else {
                world.spawnParticle("flame", x + 0.5 - (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3), z + 0.5 - (world.rand.nextGaussian() / 3), 0, 0, 0);
                world.spawnParticle("flame", x + 0.5 + (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3), z + 0.5 + (world.rand.nextGaussian() / 3), 0, 0, 0);
            }
        }
        
        stack.damageItem(1, player);
        
        return true;
    }

    @Override
    public int energyRequired(String type, EntityPlayer player)
    {
        if(type.equals(this.USE_BLOCK_ACTIVATE))
        {
            return 20;
        }
        
        if(type.equals(this.USE_ENTITY_ATTACK))
        {
            return 5;
        }
        
        return 0;
    }
}
