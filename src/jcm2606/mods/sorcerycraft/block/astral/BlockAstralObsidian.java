package jcm2606.mods.sorcerycraft.block.astral;

import java.util.Random;

import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralObsidian;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAstralObsidian extends SCBlockContainer {
	public BlockAstralObsidian(int par1) {
		super(par1, Material.rock, "astralObsidian", Rarities.BASIC);
		this.useIconIndex = false;
        this.renderAsNormalBlock = false;
        this.isOpaqueCube = false;
        this.renderID = -1;
		this.setHardness(3.0f);
		this.setResistance(10.0f);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
	   /* if(par1World.getClosestPlayer(par2, par3, par4, 10) == null) return;
	    
        double px = par1World.getClosestPlayer(par2, par3, par4, 10).posX;
        double py = par1World.getClosestPlayer(par2, par3, par4, 10).posY;
        double pz = par1World.getClosestPlayer(par2, par3, par4, 10).posZ;
        double vx = par2 - px + par5Random.nextFloat() / 2.0F;
        double vy = par3 - py + par5Random.nextFloat() / 2.0F;
        double vz = par4 - pz + par5Random.nextFloat() / 2.0F;
        SCParticle.spawnParticle("astralRune", px, py, pz, vx, vy, vz);*/
	}

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityAstralObsidian();
    }
}
