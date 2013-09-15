package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.api.AstralManager;
import jcm2606.mods.sorcerycraft.core.helper.SCHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAstralEnergyPearl extends SCItemShine
{
    public ItemAstralEnergyPearl(int par1)
    {
        super(par1, "astralEnergyPearl");
        this.setMaxStackSize(1);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isCurrentItem)
    {
        if (slot <= 8)
        {
            if (entity instanceof EntityLivingBase)
            {
                EntityLivingBase living = (EntityLivingBase) entity;
                
                if (living instanceof EntityPlayer)
                {
                    EntityPlayer player = (EntityPlayer) living;
                    
                    AstralManager.chargeCellsInInvFromBlocks(stack, player, world, 11, 8, 11);
                }
            }
        }
    }
    
    @Override
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        if (SCHelper.playerHasPerceptionMedallion(player))
        {
            list.add("\2478\247oScans 11x8x11 area");
        }
    }
}
