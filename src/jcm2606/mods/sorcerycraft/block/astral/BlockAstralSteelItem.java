package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockAstralSteelItem extends ItemBlock
{
    public BlockAstralSteelItem(int par1)
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
}
