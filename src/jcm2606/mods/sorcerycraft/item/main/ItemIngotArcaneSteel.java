package jcm2606.mods.sorcerycraft.item.main;

import java.util.List;

import jcm2606.mods.sorcerycraft.item.SCItemShine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemIngotArcaneSteel extends SCItemShine
{
    public ItemIngotArcaneSteel(int par1)
    {
        super(par1, "ingotArcaneSteel");
    }
    
    @Override
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
    }
}
