package jcm2606.mods.sorcerycraft.block.astral;

import java.util.List;

import jcm2606.mods.sorcerycraft.api.IEnergyInfused;
import jcm2606.mods.sorcerycraft.api.ITransmutable;
import jcm2606.mods.sorcerycraft.block.SCBlock;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAstralSteel extends SCBlock implements ITransmutable, IEnergyInfused
{
    public BlockAstralSteel(int par1)
    {
        super(par1, Material.iron, "blockAstralSteel", Rarities.BASIC);
        this.setHardness(4.0f);
        this.setResistance(32.0f);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconBuffer = new Icon[2];
        
        this.iconBuffer[0] = par1IconRegister.registerIcon("SorceryCraft:" + this.name);
        this.iconBuffer[1] = par1IconRegister.registerIcon("SorceryCraft:" + this.name + "Partial");
    }
    
    @Override
    public int damageDropped(int par1)
    {
        return par1;
    }
    
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < 2; i++)
        {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        return this.iconBuffer[meta];
    }
    
    @Override
    public Block getRequiredBlock(ItemStack stack)
    {
        return this;
    }
    
    @Override
    public int getRequiredBlockMetadata(ItemStack stack)
    {
        return 1;
    }
    
    @Override
    public int getTransmuteCost(ItemStack stack, Block block)
    {
        return 16;
    }
    
    @Override
    public void onTransmute(ItemStack stack, Block block, EntityPlayer player, World world, int x, int y, int z)
    {
    }
    
    @Override
    public int getMetadataToChangeTo()
    {
        return 0;
    }
    
    @Override
    public boolean destroyBlockWhenExtracted()
    {
        return true;
    }
    
    @Override
    public int getCharge()
    {
        return 20;
    }
    
    @Override
    public int getDestroyChance()
    {
        return 50;
    }
}
