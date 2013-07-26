package jcm2606.mods.sorcerycraft.astral.gauntlet;

import java.util.List;

import jcm2606.mods.sorcerycraft.api.AstralManager;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.EnumUseType;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.GauntletMode;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModeBlockBreak extends GauntletMode {
    public ModeBlockBreak() {
        super(AstralManager.getNextAvailableId(), "Block Obliteration", "");
    }

    @Override
    public boolean onUse(EnumUseType type, ItemStack stack, World world, EntityPlayer player, EntityLivingBase living, int x, int y, int z, int side)
    {
        if(type.equals(EnumUseType.BLOCK_RIGHT_CLICK))
        {
            Block block = Block.blocksList[world.getBlockId(x, y, z)];
            
            if(block != null)
            {
                if(world.canMineBlock(player, x, y, z) && block.getBlockHardness(world, x, y, z) != -1)
                {
                    if(!world.isRemote)
                    {
                        world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(block.idDropped(0, world.rand, 0), block.quantityDropped(world.rand) + world.rand.nextInt(4), world.getBlockMetadata(x, y, z))));
                        world.setBlock(x, y, z, 0);
                    } else {
                        world.playSound(x, y, z, block.stepSound.getBreakSound(), 1.0f, 0.85f, false);
                    }
                    
                    this.useEnergy(player, type);
                    
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
    public int energyRequired(EnumUseType type, EntityPlayer player)
    {
        if(type.equals(EnumUseType.BLOCK_RIGHT_CLICK))
        {
            return 5;
        }
        
        return 0;
    }

    @Override
    public void addInfoToItemMouseover(EntityPlayer player, ItemStack stack, boolean isSneaking, List list)
    {
        list.add("Size: ");
        list.add(" 1x1x1");
    }
}
