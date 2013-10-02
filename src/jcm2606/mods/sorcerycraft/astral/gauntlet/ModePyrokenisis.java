// TODO: Remaining as future reference for code.


/*package jcm2606.mods.sorcerycraft.astral.gauntlet;

import java.util.List;
import java.util.Random;

import jcm2606.mods.sorcerycraft.api.astral.gauntlet.EnumUseType;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.GauntletMode;
import jcm2606.mods.sorcerycraft.astral.AstralManager;
import jcm2606.mods.sorcerycraft.client.fx.FXSearFlame;
import jcm2606.mods.sorcerycraft.core.SCParticle;
import jcm2606.mods.sorcerycraft.core.config.Settings;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;

public class ModePyrokenisis extends GauntletMode
{
    public ModePyrokenisis()
    {
        super(AstralManager.getNextAvailableId(), "Pyrokenisis", "");
    }
    
    @Override
    public boolean onUse(EnumUseType type, ItemStack stack, World world, EntityPlayer player, EntityLivingBase living, int x, int y, int z, int side)
    {
        if (type.equals(EnumUseType.ENTITY_HIT))
        {
            living.setFire(10);
            
            this.useEnergy(player, type);
            
            return true;
        } else
            if (type.equals(EnumUseType.BLOCK_RIGHT_CLICK))
            {
                return attemptPlaceFire(player, world, x, y, z);
            } else
                if (type.equals(EnumUseType.BLOCK_LEFT_CLICK))
                {
                    return attemptSmeltingAt(world, player, stack, x, y, z, world.getBlockId(x, y, z));
                }
        
        return false;
    }
    
    @Override
    public void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem)
    {
        if (isCurrentItem && world.isRemote && AstralManager.getTotalEnergy(player) > 0)
        {
            double adjAngle = 25.0D;
            double dist = 0.4D;
            
            EntityPlayer center = player;
            
            double posX = center.posX - Math.cos((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
            double posY = center.posY - 0.2 - Math.sin(center.rotationPitch / 540.0F * Math.PI) * dist;
            double posZ = center.posZ + Math.sin((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
            
            Random rand = new Random();
            float speed = 0.02F;
            
            FXSearFlame fx = new FXSearFlame(Minecraft.getMinecraft().theWorld, posX, posY, posZ, (rand.nextFloat() - rand.nextFloat()) * speed,
                    (rand.nextFloat() - rand.nextFloat()) * speed, (rand.nextFloat() - rand.nextFloat()) * speed);
            fx.maxAge = 40;
            
            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
        }
    }
    
    public boolean attemptSmeltingAt(World world, EntityPlayer player, ItemStack stack, int x, int y, int z, int idR)
    {
        int id = world.getBlockId(x, y, z);
        
        if (id == 0)
        {
            return false;
        }
        
        if (id != idR)
        {
            return false;
        }
        
        ItemStack smelted = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(Block.blocksList[id]));
        
        if (smelted == null)
        {
            return false;
        }
        
        if (!world.isRemote)
        {
            if (smelted.getItem() instanceof ItemBlock)
            {
                world.setBlock(x, y, z, smelted.getItem().itemID);
            } else
            {
                player.entityDropItem(new ItemStack(smelted.getItem().itemID, 1, smelted.getItemDamage()), 0.0F);
                world.setBlock(x, y, z, 0);
            }
        }
        
        for (int i = 0; i < 32; i++)
        {
            world.spawnParticle("smoke", x + 0.5 - (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3), z + 0.5 - (world.rand
                    .nextGaussian() / 3), 0, 0, 0);
            world.spawnParticle("smoke", x + 0.5 + (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3), z + 0.5 + (world.rand
                    .nextGaussian() / 3), 0, 0, 0);
        }
        
        for (int i = 0; i < 8; i++)
        {
            if (Minecraft.isFancyGraphicsEnabled() || Settings.RING_MAGMA_FANCY_PARTICLES)
            {
                SCParticle.spawnSearFlameFX(x + 0.5 - (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3),
                        z + 0.5 - (world.rand.nextGaussian() / 3), 0, 0.001, 0, false);
                SCParticle.spawnSearFlameFX(x + 0.5 + (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3),
                        z + 0.5 + (world.rand.nextGaussian() / 3), 0, 0.001, 0, false);
            } else
            {
                world.spawnParticle("flame", x + 0.5 - (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3),
                        z + 0.5 - (world.rand.nextGaussian() / 3), 0, 0, 0);
                world.spawnParticle("flame", x + 0.5 + (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3),
                        z + 0.5 + (world.rand.nextGaussian() / 3), 0, 0, 0);
            }
        }
        
        world.playSound(x, y, z, "mob.ghast.fireball", 0.5f, 1.0f, false);
        
        this.useEnergy(player, EnumUseType.BLOCK_LEFT_CLICK);
        
        return true;
    }
    
    public boolean attemptPlaceFire(EntityPlayer player, World world, int x, int y, int z)
    {
        if (world.getBlockId(x, y + 1, z) == 0)
        {
            world.setBlock(x, y + 1, z, Block.fire.blockID);
            
            if (world.isRemote)
            {
                for (int i = 0; i < 32; i++)
                {
                    world.spawnParticle("smoke", x + 0.5 - (world.rand.nextGaussian() / 3), y + 1 + 0.5 + (world.rand.nextGaussian() / 3),
                            z + 0.5 - (world.rand.nextGaussian() / 3), 0, 0, 0);
                    world.spawnParticle("smoke", x + 0.5 + (world.rand.nextGaussian() / 3), y + 1 + 0.5 + (world.rand.nextGaussian() / 3),
                            z + 0.5 + (world.rand.nextGaussian() / 3), 0, 0, 0);
                }
                
                for (int i = 0; i < 8; i++)
                {
                    if (Minecraft.isFancyGraphicsEnabled() || Settings.RING_MAGMA_FANCY_PARTICLES)
                    {
                        SCParticle.spawnSearFlameFX(x + 0.5 - (world.rand.nextGaussian() / 3), y + 1 + 0.5 + (world.rand.nextGaussian() / 3),
                                z + 0.5 - (world.rand.nextGaussian() / 3), 0, 0.001, 0, false);
                        SCParticle.spawnSearFlameFX(x + 0.5 + (world.rand.nextGaussian() / 3), y + 1 + 0.5 + (world.rand.nextGaussian() / 3),
                                z + 0.5 + (world.rand.nextGaussian() / 3), 0, 0.001, 0, false);
                    } else
                    {
                        world.spawnParticle("flame", x + 0.5 - (world.rand.nextGaussian() / 3), y + 1 + 0.5 + (world.rand.nextGaussian() / 3),
                                z + 0.5 - (world.rand.nextGaussian() / 3), 0, 0, 0);
                        world.spawnParticle("flame", x + 0.5 + (world.rand.nextGaussian() / 3), y + 1 + 0.5 + (world.rand.nextGaussian() / 3),
                                z + 0.5 + (world.rand.nextGaussian() / 3), 0, 0, 0);
                    }
                }
            }
            
            this.useEnergy(player, EnumUseType.BLOCK_RIGHT_CLICK);
            
            return true;
        }
        
        return false;
    }
    
    @Override
    public int energyRequired(EnumUseType type, EntityPlayer player)
    {
        if (type.equals(EnumUseType.BLOCK_RIGHT_CLICK))
        {
            return 50;
        }
        
        if (type.equals(EnumUseType.BLOCK_LEFT_CLICK))
        {
            return 20;
        }
        
        if (type.equals(EnumUseType.ENTITY_HIT))
        {
            return 5;
        }
        
        return 0;
    }
    
    @Override
    public void addInfoToItemMouseover(EntityPlayer player, ItemStack stack, boolean isSneaking, List list)
    {
    }
}
 */