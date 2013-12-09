package jcm2606.mods.sorcerycraft.astral.ability.abilities;

import jcm2606.mods.sorcerycraft.api.IAbility;
import jcm2606.mods.sorcerycraft.client.fx.FXFissure;
import jcm2606.mods.sorcerycraft.core.SCParticle;
import jcm2606.mods.sorcerycraft.core.config.Settings;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;

public class FocusedHeat implements IAbility
{
    @Override
    public String getName()
    {
        return "Focused Heat";
    }
    
    @Override
    public boolean rightClickBlock(World world, EntityPlayer player, ItemStack stack, int x, int y, int z, int side)
    {
        int id = world.getBlockId(x, y, z);
        
        if (player.isSneaking())
        {
            this.attemptSmeltingAt(world, player, stack, x, y, z, id);
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
                    
                    this.attemptSmeltingAt(world, player, stack, xCur, yCur, zCur, id);
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
                        
                        this.attemptSmeltingAt(world, player, stack, xCur, yCur, zCur, id);
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
                            
                            this.attemptSmeltingAt(world, player, stack, xCur, yCur, zCur, id);
                        }
                    }
                }
        
        world.playSound(x, y, z, "mob.ghast.fireball", 1f, 1.0f, false);
        return true;
    }
    
    @Override
    public boolean rightClick(EntityPlayer player, ItemStack stack)
    {
        return false;
    }
    
    @Override
    public boolean itemUseTick(ItemStack stack, EntityPlayer player, int count)
    {
        return false;
    }
    
    @Override
    public boolean handleItemUseTick(ItemStack stack, EntityPlayer player)
    {
        return false;
    }
    
    @Override
    public EnumAction getUseAction(ItemStack stack)
    {
        return null;
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
    }
}
