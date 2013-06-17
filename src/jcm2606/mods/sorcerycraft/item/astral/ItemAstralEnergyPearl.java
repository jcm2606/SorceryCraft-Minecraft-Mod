package jcm2606.mods.sorcerycraft.item.astral;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.astral.AstralManager;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAstralEnergyPearl extends SCItemShine {
    public ItemAstralEnergyPearl(int par1) {
        super(par1, "astralEnergyPearl");
        this.setMaxStackSize(1);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.MAGICAL);
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isCurrentItem) {
        if(slot <= 8)
        {
            if(entity instanceof EntityLiving)
            {
                EntityLiving living = (EntityLiving) entity;
                
                if(living instanceof EntityPlayer)
                {
                    EntityPlayer player = (EntityPlayer) living;
                    
                    AstralManager.chargeCellsInInv(stack, player, world, 11, 5, 11);
                }
            }
        }
    }
}
