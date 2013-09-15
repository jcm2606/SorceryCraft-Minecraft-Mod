package jcm2606.mods.sorcerycraft.item.main;

import java.util.List;

import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.api.ElementManager;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import jcm2606.mods.sorcerycraft.core.helper.SCHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemElementalGem extends Item
{
    public ItemElementalGem(int par1)
    {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setCreativeTab(SorceryCraft.tabItems);
    }
    
    @Override
    public void registerIcons(IconRegister register)
    {
        this.itemIcon = register.registerIcon("SorceryCraft:elementGem");
    }
    
    @Override
    public Icon getIconFromDamage(int meta)
    {
        return this.itemIcon;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        if (ElementManager.elementList[stack.getItemDamage()] == null)
        {
            return null;
        }
        
        return "sc_elementGem" + ElementManager.elementList[stack.getItemDamage()].getDescrambledName();
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return true;
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
        list.add("\247o" + ElementManager.getElementList()[stack.getItemDamage()].getName());
        
        if (SCHelper.playerHasPerceptionMedallion(player))
        {
            list.add("\2478\247o" + ElementManager.getElementList()[stack.getItemDamage()].getDescrambledName());
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(int itemID, CreativeTabs tab, List list)
    {
        for (int i = 0; i < ElementManager.getTotalNumberOfElements(); i++)
        {
            list.add(new ItemStack(itemID, 1, i));
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
    {
        return ElementManager.elementList[par1ItemStack.getItemDamage()].getColour();
    }
}
