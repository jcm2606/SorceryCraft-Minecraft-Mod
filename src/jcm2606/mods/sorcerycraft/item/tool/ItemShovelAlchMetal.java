package jcm2606.mods.sorcerycraft.item.tool;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCShovel;
import net.minecraft.client.Minecraft;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemShovelAlchMetal extends SCShovel
{
    
    public ItemShovelAlchMetal(int par1)
    {
        super(par1, SCObjects.ALCH_METAL, "toolShovelAlchMetal");
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        if (Minecraft.getMinecraft().thePlayer.username.equals("Asyncronous"))
        {
            return RarityHelper.getCustomRarityType(Rarities.EXOTIC);
        }
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
    }
}
