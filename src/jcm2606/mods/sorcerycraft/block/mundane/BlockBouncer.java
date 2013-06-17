package jcm2606.mods.sorcerycraft.block.mundane;

import jcm2606.mods.sorcerycraft.SCIconManager;
import jcm2606.mods.sorcerycraft.block.SCBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockBouncer extends SCBlock {
	
	private final double jumpHeightModifier;
	
	public BlockBouncer(int par1, double par3) {
		super(par1, Material.iron, "mundaneEntityBouncer");
		this.jumpHeightModifier = par3;
	}
	
	/**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    @Override
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {
    	par5Entity.motionY += this.jumpHeightModifier;
    }
    
    /**
	 * Returns the block texture based on the side being looked at. Args: side
	 */
    @Override
    public Icon getIcon(int blockSide, int par2)
    {
		return blockSide != 1 ? iconBuffer[0] : iconBuffer[1];
	}
	
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        iconBuffer = new Icon[2]; // 3 machines, 6 sides each, in ON and OFF states
        
        iconBuffer[0] = SCIconManager.getIcon("mundaneMechanismBase");
        iconBuffer[1] = SCIconManager.getIcon("mundaneBouncerTop"); // top
    }
}
