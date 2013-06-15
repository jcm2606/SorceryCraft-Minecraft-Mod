package jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode;

import jcm2606.mods.sorcerycraft.item.astral.gauntlet.AstralGauntletManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModeStrength extends GauntletMode {
    public ModeStrength() {
        super(AstralGauntletManager.getNextAvailableId(), "Enhanced Strength", "");
    }

    @Override
    public boolean onUse(String type, ItemStack stack, World world, EntityPlayer player, EntityLiving living, int x, int y, int z, int side)
    {
        if(type.equals(this.USE_ENTITY_ATTACK))
        {
            living.knockBack(living, 50000, 1000.0f, 1000.0f);
            
            return true;
        }
        
        return false;
    }

    @Override
    public void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem)
    {}

    @Override
    public int energyRequired(String type)
    {
        if(type.equals(this.USE_ENTITY_ATTACK))
        {
            return 5;
        }
        
        return 0;
    }
}
