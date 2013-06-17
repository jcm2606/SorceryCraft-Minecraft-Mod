package jcm2606.mods.sorcerycraft.item.main;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.jccore.item.ItemMeta;
import jcm2606.mods.sorcerycraft.SCIconManager;
import jcm2606.mods.sorcerycraft.SorceryCraft;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemCog extends ItemMeta {
    public static String[] names = {
        "sc_cogStone",
        "sc_cogMetal",
        "sc_cogAstral",
        "sc_cogArcane"
    };
    
    public static String[] localisedNames = {
        "Stone",
        "Metal",
        "Astral",
        "Arcane"
    };
    
    public static Icon[] textures = {
        SCIconManager.getIcon("cogStone"),
        SCIconManager.getIcon("cogMetal"),
        SCIconManager.getIcon("cogAstral"),
        SCIconManager.getIcon("cogArcane")
    };
    
    public ItemCog(int id) {
        super(id, names, textures);
        this.setCreativeTab(SorceryCraft.tab);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        if(stack.getItemDamage() == 2 || stack.getItemDamage() == 3)
        {
            return RarityHelper.getCustomRarityType(Rarities.BASIC);
        }
        
        return EnumRarity.common;
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        if(stack.getItemDamage() == 2 || stack.getItemDamage() == 3)
        {
            return true;
        }
        
        return false;
    }
}
