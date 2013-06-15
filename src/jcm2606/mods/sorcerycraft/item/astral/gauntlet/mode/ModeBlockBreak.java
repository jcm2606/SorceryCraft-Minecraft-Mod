package jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode;

import jcm2606.mods.sorcerycraft.item.astral.gauntlet.AstralGauntletManager;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModeBlockBreak extends GauntletMode {
    public ModeBlockBreak() {
        super(AstralGauntletManager.getNextAvailableId(), "Block Destruction", "");
    }

    @Override
    public boolean onUse(String type, ItemStack stack, World world, EntityPlayer player, EntityLiving living, int x, int y, int z, int side)
    {
        if(type.equals(this.USE_BLOCK_ACTIVATE))
        {
            if(world.canMineBlock(player, x, y, z) && Block.blocksList[world.getBlockId(x, y, z)].getBlockHardness(world, x, y, z) != -1)
            {
                if(player.isSneaking())
                {
                    Block block = Block.blocksList[world.getBlockId(x, y, z)];
                    
                    if(block != null)
                    {
                        if(!world.isRemote)
                        {
                            world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(block.idDropped(0, world.rand, 0), 1 + world.rand.nextInt(3), world.getBlockMetadata(x, y, z))));
                            world.setBlock(x, y, z, 0);
                        }
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
        
        return false;
    }

    @Override
    public void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem)
    {}

    @Override
    public int energyRequired(String type)
    {
        return 4;
    }
}
