package jcm2606.mods.sorcerycraft.astral.gauntlet;

import java.util.List;

import jcm2606.mods.sorcerycraft.api.AstralManager;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.EnumUseType;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.GauntletMode;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModeBlockBreak extends GauntletMode
{
    public ModeBlockBreak()
    {
        super(AstralManager.getNextAvailableId(), "Block Obliteration", "");
    }
    
    @Override
    public boolean onUse(EnumUseType type, ItemStack stack, World world, EntityPlayer player, EntityLivingBase living, int x, int y, int z, int side)
    {
        if (type.equals(EnumUseType.BLOCK_RIGHT_CLICK))
        {
            if (world.canMineBlock(player, x, y, z))
            {
                world.destroyBlock(x, y, z, true);
                
                this.useEnergy(player, type);
                
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem)
    {
    }
    
    @Override
    public int energyRequired(EnumUseType type, EntityPlayer player)
    {
        if (type.equals(EnumUseType.BLOCK_RIGHT_CLICK))
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
