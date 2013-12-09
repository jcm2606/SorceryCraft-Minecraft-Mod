package jcm2606.mods.sorcerycraft.item;

import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SCItem extends Item
{
    String name;
    boolean isShiny = false;
    String rarityName = "";
    
    public SCItem(int par1, String par2)
    {
        super(par1);
        this.setCreativeTab(SorceryCraft.tabItems);
        this.setUnlocalizedName("sc" + par2);
        this.name = par2;
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(this.rarityName);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("SorceryCraft:" + this.name);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return this.isShiny;
    }
    
    public SCItem setShiny(boolean b)
    {
        this.isShiny = b;
        
        return this;
    }
    
    public SCItem setRarityName(String s)
    {
        this.rarityName = s;
        
        return this;
    }
}
