package jcm2606.mods.sorcerycraft.block.main;

import java.util.List;

import jcm2606.mods.sorcerycraft.block.SCBlock;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDarkQuartzBrick extends SCBlock {
    public BlockDarkQuartzBrick(int par1) {
        super(par1, Material.rock, "darkQuartzBrick");
        this.setHardness(3.0f);
        this.setResistance(6.0f);
        this.setCreativeTab(SorceryCraft.tabBlocksDeco);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconBuffer = new Icon[2];
        
        this.iconBuffer[0] = par1IconRegister.registerIcon("SorceryCraft:" + name);
        this.iconBuffer[1] = par1IconRegister.registerIcon("SorceryCraft:" + name + "Top");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if(meta == 0)
        {
            return side == 0 || side == 1 ? this.iconBuffer[1] : this.iconBuffer[0];
        } else {
            return this.iconBuffer[1];
        }
    }
    
    @Override
    public int damageDropped(int par1)
    {
      return par1;
    }
    
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for(int i = 0; i < 2; i++)
        {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
}
