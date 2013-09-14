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

public class ModeHealing extends GauntletMode
{
    public ModeHealing()
    {
        super(AstralManager.getNextAvailableId(), "Wounded Healing", "");
        this.hasItemUse = true;
        this.useAction = EnumAction.eat;
        this.useActionDuration = 72000;
    }
    
    @Override
    public boolean onUse(EnumUseType type, ItemStack stack, World world, EntityPlayer player, EntityLivingBase living, int x, int y, int z, int side)
    {
        if (type.equals(EnumUseType.ITEM_USE))
        {
            if (!(player.prevHealth >= 20))
            {
                player.setEntityHealth(player.prevHealth + 1);
                
                if (world.isRemote)
                {
                    for (int i = 0; i < 8; ++i)
                    {
                        world.spawnParticle("portal", player.posX, player.posY - 1 + world.rand.nextDouble() * 2.0D, player.posZ, world.rand
                                .nextGaussian(), 0.0D, world.rand.nextGaussian());
                    }
                }
                
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
        if (type.equals(EnumUseType.ITEM_USE))
        {
            return 10;
        }
        
        return 0;
    }
    
    @Override
    public void addInfoToItemMouseover(EntityPlayer player, ItemStack stack, boolean isSneaking, List list)
    {
    }
}
