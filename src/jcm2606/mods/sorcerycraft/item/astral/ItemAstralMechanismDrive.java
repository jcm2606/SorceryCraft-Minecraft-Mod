package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemAstralMechanismDrive extends SCItem
{
    public ItemAstralMechanismDrive(int par1)
    {
        super(par1, "astralMechanismDrive");
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return false;
    }
    
    @Override
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
    }
}
