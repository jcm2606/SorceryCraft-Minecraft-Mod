package jcm2606.mods.sorcerycraft.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class EnchantmentRottingTouch extends Enchantment
{
    public EnchantmentRottingTouch(int par1)
    {
        super(par1, 3, EnumEnchantmentType.weapon);
    }
    
    /**
     * Returns the correct traslated name of the enchantment and the level in roman numbers.
     */
    @Override
    public String getTranslatedName(int par1)
    {
        String s = StatCollector.translateToLocal("Rotting Touch");
        return s + " " + StatCollector.translateToLocal("enchantment.level." + par1);
    }
    
    @ForgeSubscribe
    public void handle(LivingDropsEvent event)
    {
        if(event.source == DamageSource.generic)
        {
            EntityDamageSource entitySource = (EntityDamageSource) event.source;
            
            if(entitySource.getSourceOfDamage() instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer) entitySource.getSourceOfDamage();
                
                if(player.getCurrentEquippedItem() != null)
                {
                    if(EnchantmentHelper.getEnchantmentLevel(Enchantments.rottingTouch.effectId, player.getCurrentEquippedItem()) > 0)
                    {
                        event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(Item.rottenFlesh, 1 + event.entityLiving.worldObj.rand.nextInt(event.lootingLevel))));
                    }
                }
            }
        }
    }
}
