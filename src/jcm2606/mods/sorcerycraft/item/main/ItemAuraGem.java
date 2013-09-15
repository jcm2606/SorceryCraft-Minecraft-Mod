package jcm2606.mods.sorcerycraft.item.main;

import java.util.List;

import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.jccore.item.ItemMeta;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import jcm2606.mods.sorcerycraft.core.helper.SCHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAuraGem extends ItemMeta
{
    public static String[] names =
    { "sc_auraGemPure", "sc_auraGemImpure", "sc_auraGemDamaged" };
    
    public static String[] types =
    { "Pure", "Impure", "Damaged" };
    
    public static String[] textures =
    { "SorceryCraft:auraGemPure", "SorceryCraft:auraGemImpure", "SorceryCraft:auraGemDamaged" };
    
    public ItemAuraGem(int par1)
    {
        super(par1, names, textures);
        this.setCreativeTab(SorceryCraft.tabItems);
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return stack.getItemDamage() < 2 ? true : false;
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        if (SCHelper.playerHasPerceptionMedallion(player))
        {
            list.add("\2478\247o" + this.types[stack.getItemDamage()]);
        }
    }
}
