package jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode;

import java.util.List;

import jcm2606.mods.sorcerycraft.astral.AstralManager;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.AstralGauntletManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModeEnergyAbsorb extends GauntletMode {
    public ModeEnergyAbsorb() {
        super(AstralGauntletManager.getNextAvailableId(), "Energy Gathering", "");
        this.useAction = EnumAction.bow;
        this.useActionDuration = 72000;
        this.hasItemUse = true;
    }
    
    @Override
    public boolean onUse(String type, ItemStack stack, World world, EntityPlayer player, EntityLiving living, int x, int y, int z, int side)
    {
        if(type.equals(this.USE_ITEM_USE))
        {
            AstralManager.chargeCellsInInv(stack, player, world);
        }
        
        return false;
    }

    @Override
    public void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem)
    {}

    @Override
    public int energyRequired(String type, EntityPlayer player)
    {
        return 0;
    }

    @Override
    public void addInfoToItemMouseover(EntityPlayer player, ItemStack stack, boolean isSneaking, List list)
    {}
}
