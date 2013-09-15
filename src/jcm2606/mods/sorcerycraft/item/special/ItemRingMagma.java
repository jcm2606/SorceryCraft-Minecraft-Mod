package jcm2606.mods.sorcerycraft.item.special;

import java.util.List;

import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.api.compat.CompatContainerSC;
import jcm2606.mods.sorcerycraft.api.compat.HandlerMethodID;
import jcm2606.mods.sorcerycraft.client.fx.FXFissure;
import jcm2606.mods.sorcerycraft.core.SCParticle;
import jcm2606.mods.sorcerycraft.core.config.Settings;
import jcm2606.mods.sorcerycraft.core.helper.SCHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;

public class ItemRingMagma extends SCItem
{
    public ItemRingMagma(int par1)
    {
        super(par1, "ringMagma");
        this.setMaxStackSize(1);
        this.setMaxDamage(820);
    }
    
    @Override
    public boolean shouldRotateAroundWhenRendering()
    {
        return false;
    }
    
    /**
     * Called each tick as long the item is on a player inventory. Uses by maps
     * to check if is on a player hand and update it's contents.
     */
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean currentItem)
    {
        if (entity instanceof EntityLivingBase)
        {
            if ((EntityLivingBase) entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) (EntityLivingBase) entity;
                
                if (player.isInsideOfMaterial(Material.lava))
                {
                    if (world.rand.nextInt(100) <= 5)
                    {
                        stack.damageItem(-1, player);
                    }
                }
            }
        }
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
    }
    
    /**
     * Callback for item usage. If the item does something special on right
     * clicking, he will have one of those. Return True if something happen and
     * false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        int id = world.getBlockId(x, y, z);
        
        if (player.isSneaking())
        {
            attemptSmeltingAt(world, player, stack, x, y, z, id);
            world.playSound(x, y, z, "mob.ghast.fireball", 0.5f, 1.0f, false);
            return true;
        }
        
        int r = 8;
        
        if ((side == 1) || (side == 0))
        {
            for (int i = -r; i <= r; i++)
            {
                for (int n = -r; n <= r; n++)
                {
                    int xCur = x + i;
                    int zCur = z + n;
                    int yCur = y;
                    
                    if (stack.getItemDamage() >= stack.getMaxDamage())
                    {
                        stack.damageItem(1, player);
                        break;
                    }
                    
                    attemptSmeltingAt(world, player, stack, xCur, yCur, zCur, id);
                }
            }
        } else
            if ((side == 2) || (side == 3))
            {
                for (int i = -r; i <= r; i++)
                {
                    for (int n = -r; n <= r; n++)
                    {
                        int xCur = x + i;
                        int zCur = z;
                        int yCur = y + n;
                        
                        if (stack.getItemDamage() >= stack.getMaxDamage())
                        {
                            stack.damageItem(1, player);
                            break;
                        }
                        
                        attemptSmeltingAt(world, player, stack, xCur, yCur, zCur, id);
                    }
                }
            } else
                if ((side == 4) || (side == 5))
                {
                    for (int i = -r; i <= r; i++)
                    {
                        for (int n = -r; n <= r; n++)
                        {
                            int xCur = x;
                            int zCur = z + i;
                            int yCur = y + n;
                            
                            if (stack.getItemDamage() >= stack.getMaxDamage())
                            {
                                stack.damageItem(1, player);
                                break;
                            }
                            
                            attemptSmeltingAt(world, player, stack, xCur, yCur, zCur, id);
                        }
                    }
                }
        
        world.playSound(x, y, z, "mob.ghast.fireball", 1f, 1.0f, false);
        return true;
    }
    
    public void attemptSmeltingAt(World world, EntityPlayer player, ItemStack stack, int x, int y, int z, int idR)
    {
        int id = world.getBlockId(x, y, z);
        
        if (id == 0)
        {
            return;
        }
        
        if (id != idR)
        {
            return;
        }
        
        ItemStack smelted = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(Block.blocksList[id]));
        
        if (smelted == null)
        {
            return;
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
        
        if (world.isRemote)
        {
            FXFissure fx = new FXFissure(world, x + 0.5, y + 0.5, z + 0.5, 20);
            fx.innerColour = 0xF88017;
            fx.outterColour = 0xE42217;
            
            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
            
            for (int i = 0; i < 32; i++)
            {
                world.spawnParticle("smoke", x + 0.5 - (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3),
                        z + 0.5 - (world.rand.nextGaussian() / 3), 0, 0, 0);
                world.spawnParticle("smoke", x + 0.5 + (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3),
                        z + 0.5 + (world.rand.nextGaussian() / 3), 0, 0, 0);
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
        }
        
        CompatContainerSC.postUpdate(HandlerMethodID.RING_MAGMA_USE, null);
        
        stack.damageItem(1, player);
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        if (SCHelper.playerHasPerceptionMedallion(player))
        {
            list.add("\2478\247o" + (stack.getMaxDamage() - stack.getItemDamage()) + " / " + stack.getMaxDamage());
        }
    }
}
