package jcm2606.mods.sorcerycraft.item.tool;

import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCSword;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemSwordFire extends SCSword
{
    public ItemSwordFire(int par1)
    {
        super(par1, SCObjects.SWORD_GRIFFIN, "toolSwordFire");
        this.setNoRepair();
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
    }
    
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase living, EntityLivingBase living2)
    {
        super.hitEntity(stack, living, living2);
        
        living.setFire(10);
        
        return true;
    }
}
