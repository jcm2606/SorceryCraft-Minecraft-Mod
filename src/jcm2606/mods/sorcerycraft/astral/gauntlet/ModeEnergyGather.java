package jcm2606.mods.sorcerycraft.astral.gauntlet;

import java.util.List;

import jcm2606.mods.sorcerycraft.api.AstralManager;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.EnumUseType;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.GauntletMode;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModeEnergyGather extends GauntletMode
{
    public ModeEnergyGather()
    {
        super(AstralManager.getNextAvailableId(), "Energy Gathering", "");
        this.useAction = EnumAction.bow;
        this.useActionDuration = 72000;
        this.hasItemUse = true;
    }
    
    @Override
    public boolean onUse(EnumUseType type, ItemStack stack, World world, EntityPlayer player, EntityLivingBase living, int x, int y, int z, int side)
    {
        if (type.equals(EnumUseType.ITEM_USE))
        {
            AstralManager.chargeCellsInInvFromBlocks(stack, player, world);
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
        return 0;
    }
    
    @Override
    public void addInfoToItemMouseover(EntityPlayer player, ItemStack stack, boolean isSneaking, List list)
    {
    }
}
