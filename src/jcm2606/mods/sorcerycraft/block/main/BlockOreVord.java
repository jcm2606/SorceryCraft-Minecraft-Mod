package jcm2606.mods.sorcerycraft.block.main;

import java.util.Random;

import jcm2606.mods.sorcerycraft.block.SCOre;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.RenderID;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOreVord extends SCOre
{
    public Icon textureGlow;
    
    public BlockOreVord(int par1)
    {
        super(par1, "oreVord");
        this.setResistance(5.0F);
        this.setHardness(2.2f);
        this.renderAsNormalBlock = false;
        this.renderID = RenderID.renderIDVordicOre;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("SorceryCraft:" + name);
        this.textureGlow = par1IconRegister.registerIcon("SorceryCraft:oreVordGlow");
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return SCObjects.dustVordic.itemID;
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1 + par1Random.nextInt(2);
    }
    
    /**
     * Return true from this function if the player with silk touch can harvest
     * this block directly, and not it's normal drops.
     * 
     * @param world
     *            The world
     * @param player
     *            The player doing the harvesting
     * @param x
     *            X Position
     * @param y
     *            Y Position
     * @param z
     *            Z Position
     * @param metadata
     *            The metadata
     * @return True if the block can be directly harvested using silk touch
     */
    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        return true;
    }
}
