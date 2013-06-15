package jcm2606.mods.sorcerycraft.item;

import jcm2606.mods.sorcerycraft.IconManager;
import jcm2606.mods.sorcerycraft.SorceryCraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SCItem extends Item {
	String name;
	
	public SCItem(int par1, String par2) {
		super(par1);
		setCreativeTab(SorceryCraft.tab);
		this.setUnlocalizedName("sc" + par2);
		this.name = par2;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = IconManager.getIcon(this.name);
    }
}