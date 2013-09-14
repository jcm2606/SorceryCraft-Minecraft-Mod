package jcm2606.mods.sorcerycraft.block.main;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockGlowBrickItem extends ItemBlock
{
    public final String[] colours = new String[]
    { "Black", "Red", "Green", "Brown", "Blue", "Dark Purple", "Cyan", "Grey", "Dark Grey", "Pink", "Lime", "Yellow", "Light Blue", "Light Purple",
            "Orange", "White" };
    
    public BlockGlowBrickItem(int par1)
    {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
    }
    
    @Override
    public int getMetadata(int par1)
    {
        return par1;
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
    {
        list.add(colours[(stack.getItemDamage() % 16)]);
    }
}
