package jcm2606.mods.sorcerycraft.item.main;

import java.util.List;

import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.jccore.item.ItemMeta;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemCog extends ItemMeta
{
    public static String[] names =
    { "sc_cogStone", "sc_cogMetal", "sc_cogAstral", "sc_cogArcane" };
    
    public static String[] localisedNames =
    { "Stone", "Metal", "Astral", "Arcane" };
    
    public static String[] textures =
    { "SorceryCraft:cogStone", "SorceryCraft:cogMetal", "SorceryCraft:cogAstral", "SorceryCraft:cogArcane" };
    
    public ItemCog(int id)
    {
        super(id, names, textures);
        this.setCreativeTab(SorceryCraft.tabItems);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        if (stack.getItemDamage() == 2 || stack.getItemDamage() == 3)
        {
            return RarityHelper.getCustomRarityType(Rarities.BASIC);
        }
        
        return EnumRarity.common;
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        if (stack.getItemDamage() == 2 || stack.getItemDamage() == 3)
        {
            return true;
        }
        
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
