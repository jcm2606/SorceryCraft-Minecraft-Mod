package jcm2606.mods.sorcerycraft.item.main;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemStoneAstral extends ItemTransmutationDevice
{
    public ItemStoneAstral(int par1)
    {
        super(par1, "stoneTransAstral");
        setMaxDamage(1024);
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("SorceryCraft:" + "stoneTransAlch");
    }
    
    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
    {
        return false;
    }
    
    @Override
    public boolean getShareTag()
    {
        return true;
    }
    
    /**
     * Called each tick as long the item is on a player inventory. Uses by maps
     * to check if is on a player hand and update it's contents.
     */
    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
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
        return 0x25143b;
    }
}
