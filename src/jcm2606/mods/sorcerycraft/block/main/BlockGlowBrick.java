package jcm2606.mods.sorcerycraft.block.main;

import java.util.List;

import jcm2606.mods.sorcerycraft.block.SCBlock;
import jcm2606.mods.sorcerycraft.core.lib.RenderID;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockGlowBrick extends SCBlock {
    public Icon textureBackground;
    
    public String texture;
    
    public int[] colours = new int[] {
        0x191919,
        0x993333,
        0x667F33,
        0x664C33,
        0x334CB2,
        0x7F3FB2,
        0x4C7F99,
        0x999999,
        0x4C4C4C,
        0xF27FA5,
        0x7FCC19,
        0xE5E533,
        0x6699D8,
        0xB24CD8,
        0xD87F33,
        0xFFFFFF
    };
    
    public BlockGlowBrick(int par1, String name, String texture) {
        super(par1, Material.rock, name);
        this.renderAsNormalBlock = false;
        this.renderID = RenderID.renderIDGlowBrick;
        this.texture = texture;
        this.setLightOpacity(255);
    }
    
    @Override
    public int damageDropped(int par1)
    {
      return par1;
    }
    
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for(int i = 0; i < 16; i++)
        {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
    
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("SorceryCraft:" + texture);
        this.textureBackground = par1IconRegister.registerIcon("SorceryCraft:glowBrickBG");
    }
}
