package jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode;

import java.util.List;

import jcm2606.mods.sorcerycraft.item.astral.gauntlet.AstralGauntletManager;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModeBlockBreak extends GauntletMode {
    public ModeBlockBreak() {
        super(AstralGauntletManager.getNextAvailableId(), "Block Obliteration", "");
    }

    @Override
    public boolean onUse(String type, ItemStack stack, World world, EntityPlayer player, EntityLiving living, int x, int y, int z, int side)
    {
        if(type.equals(this.USE_BLOCK_ACTIVATE))
        {
            Block block = Block.blocksList[world.getBlockId(x, y, z)];
            
            if(block != null)
            {
                if(world.canMineBlock(player, x, y, z) && block.getBlockHardness(world, x, y, z) != -1)
                {
                    if(player.isSneaking())
                    {
                        if(!world.isRemote)
                        {
                            world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(block.idDropped(0, world.rand, 0), block.quantityDropped(world.rand) + world.rand.nextInt(4), world.getBlockMetadata(x, y, z))));
                            world.setBlock(x, y, z, 0);
                        } else {
                            world.playSound(x, y, z, block.stepSound.getBreakSound(), 1.0f, 0.85f, false);
                        }
                    } else {
                        if(!world.isRemote)
                        {
                            world.destroyBlock(x, y, z, true);
                        }
                    }
                    
                    return true;
                }
            }
        }
        
        return false;
    }

    @Override
    public void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem)
    {}

    @Override
    public int energyRequired(String type, EntityPlayer player)
    {
        if(player.isSneaking())
        {
            return 10;
        }
        
        return 4;
    }

    @Override
    public void addInfoToItemMouseover(EntityPlayer player, ItemStack stack, boolean isSneaking, List list)
    {
        list.add("Size: ");
        list.add(" 1x1x1");
    }
}
