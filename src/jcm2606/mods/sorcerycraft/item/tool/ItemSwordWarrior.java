package jcm2606.mods.sorcerycraft.item.tool;

import jcm2606.mods.sorcerycraft.item.SCSword;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemSwordWarrior extends SCSword {
    public ItemSwordWarrior(int par1, EnumToolMaterial par2EnumToolMaterial, String par3) {
        super(par1, par2EnumToolMaterial, par3);
    }
    
    /**
     * Current implementations of this method in child classes do not use the
     * entry argument beside ev. They just raise the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack stack, EntityLiving living, EntityLiving living2) {
        System.out.println(living2.getHealth());
        living2.attackEntityFrom(DamageSource.generic, 4);
        System.out.println(living2.getHealth());
        
        return true;
    }
}
