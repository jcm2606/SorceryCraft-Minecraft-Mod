package jcm2606.mods.sorcerycraft.block.mundane;

import jcm2606.mods.jccore.util.ServerUtil;
import jcm2606.mods.sorcerycraft.SCIconManager;
import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.SorceryCraft;
import jcm2606.mods.sorcerycraft.block.SCBlock;
import jcm2606.mods.sorcerycraft.config.Settings;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockEntityDetector extends SCBlock {

	public BlockEntityDetector(int par1) {
		super(par1, Material.iron, "mundaneEntityDetector");
		this.setHardness(3.0f);
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
        iconBuffer[1] = SCIconManager.getIcon("mundaneEntityDetectorTop"); // top
    }
	
	/**
	 * Called whenever an entity is walking on top of this block. Args: world,
	 * x, y, z, entity
	 */
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		
		boolean doesEntityHaveShield = false;
		
		if(entity instanceof EntityLiving)
		{
			EntityLiving living = (EntityLiving) entity;
			
			if(living instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) living;
				
				for (int i = 0; i <= 8; i++) {
					ItemStack item = player.inventory.getStackInSlot(i);
					
					if(item != null && item.itemID == SCObjects.protectionstone.itemID)
					{
						doesEntityHaveShield = true;
					}
				}
				
				if(Settings.ENTITY_DETECTOR_PLAYER_DAMAGE && !player.capabilities.isCreativeMode)
				{
					if(!doesEntityHaveShield)
					{
						world.playSoundAtEntity(entity, "sorcerycraft.magic", 0.2f, 1.0f);
						
						player.attackEntityFrom(SorceryCraft.entityDetectorDamageSource, Settings.ENTITY_DETECTOR_DAMAGE);
					}
					else
					{
						if(world.isRemote && Settings.ENTITY_DETECTOR_MESSAGE_REVACTION_STONE)
						{
							ServerUtil.sendChatTo((EntityPlayerMP) player, "\247oYou have an Aegis Stone on you, your are safe whilst holding the stone.");
						}
					}
				}
			} else {
				living.attackEntityFrom(SorceryCraft.entityDetectorDamageSource, Settings.ENTITY_DETECTOR_DAMAGE);
			}
		}
	}
}
