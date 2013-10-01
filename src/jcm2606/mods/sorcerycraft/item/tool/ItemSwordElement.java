package jcm2606.mods.sorcerycraft.item.tool;

import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.enchant.Enchantments;
import jcm2606.mods.sorcerycraft.item.SCSword;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemSwordElement extends SCSword
{
    public ItemSwordElement(int par1)
    {
        super(par1, SCObjects.SWORD_ELEMENT, "toolSwordElement");
        this.setNoRepair();
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
    
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        int damage = (int) this.toolMaterial.getDamageVsEntity();
        
        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.elementalDamage.effectId, stack) > 0)
        {
            damage = (int) this.toolMaterial.getDamageVsEntity() * 2;
        }
        
        entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
        
        return true;
    }
}
