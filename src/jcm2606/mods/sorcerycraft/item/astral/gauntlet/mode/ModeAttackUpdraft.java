package jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode;

import java.util.List;

import jcm2606.mods.sorcerycraft.item.astral.gauntlet.AstralGauntletManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModeAttackUpdraft extends GauntletMode {
    public ModeAttackUpdraft() {
        super(AstralGauntletManager.getNextAvailableId(), "Updrafting Attack", "");
        this.hasErroredName = false;
    }

    @Override
    public boolean onUse(String type, ItemStack stack, World world, EntityPlayer player, EntityLiving living, int x, int y, int z, int side)
    {
        if(type.equals(this.USE_ENTITY_ATTACK))
        {
            living.motionY += 0.8f;
            world.spawnParticle("cloud", living.posX, living.posY, living.posZ, 0, 0, 0);
            
            return true;
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
