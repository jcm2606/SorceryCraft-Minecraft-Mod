package jcm2606.mods.sorcerycraft.block.psyaic;

import java.util.List;

import jcm2606.mods.sorcerycraft.block.SCBlockMultiblock;
import jcm2606.mods.sorcerycraft.block.tile.psyaic.TileAstralCapacitorCPU;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.core.lib.RenderID;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPsyonicCapacitorCore extends SCBlockMultiblock
{
    public String[] textureList =
    { "astralCapacitorCPU_1K", "astralCapacitorCPU_2K", "astralCapacitorCPU_5K", "astralCapacitorCPU_10K", "astralCapacitorCPU_100K",
            "astralCapacitorCPU_1M" };
    
    public static String[] capacityList =
    { "1K", "2K", "5K", "10K", "100K", "1M" };
    
    public static int[] capacityIntList =
    { 1000, 2000, 5000, 10000, 100000, 1000000, };
    
    public BlockPsyonicCapacitorCore(int par1)
    {
        super(par1, Material.iron, "astralCapacitorCPU", Rarities.ADVANCED);
        this.setHardness(4.0f);
        this.setResistance(4.0f);
        this.renderID = RenderID.renderIDAstralCapacitorCPU;
        this.renderAsNormalBlock = false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralCapacitorCPU();
    }
    
    @Override
    public int damageDropped(int par1)
    {
        return par1;
    }
    
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < this.textureList.length; i++)
        {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
    
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconBuffer = new Icon[this.textureList.length];
        
        for (int i = 0; i < this.textureList.length; i++)
        {
            this.iconBuffer[i] = par1IconRegister.registerIcon("SorceryCraft:" + this.textureList[i]);
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return this.iconBuffer[par2];
    }
}
