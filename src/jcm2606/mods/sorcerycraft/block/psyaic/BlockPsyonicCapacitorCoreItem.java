package jcm2606.mods.sorcerycraft.block.psyaic;

import java.util.List;

import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockPsyonicCapacitorCoreItem extends ItemBlock
{
    public BlockPsyonicCapacitorCoreItem(int par1)
    {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
    }
    
    @Override
    public int getMetadata(int par1)
    {
        return par1;
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
    {
        list.add("Capacity: \247o" + BlockPsyonicCapacitorCore.capacityList[stack.getItemDamage() % BlockPsyonicCapacitorCore.capacityList.length]);
    }
}
