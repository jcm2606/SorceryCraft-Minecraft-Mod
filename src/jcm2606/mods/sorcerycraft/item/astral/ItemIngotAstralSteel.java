package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.sorcerycraft.item.SCItemShine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemIngotAstralSteel extends SCItemShine
{
    public ItemIngotAstralSteel(int par1)
    {
        super(par1, "ingotAstralSteel");
    }
    
    @Override
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
    }
}
