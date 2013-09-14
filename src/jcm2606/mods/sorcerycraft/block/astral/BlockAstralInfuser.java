package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralInfuser;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import jcm2606.mods.sorcerycraft.core.lib.GuiIDs;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralInfuser extends SCBlockContainer
{
    public BlockAstralInfuser(int par1)
    {
        super(par1, Material.rock, "astralInfuser", Rarities.BASIC);
        this.setHardness(3.0f);
        this.setResistance(5.0f);
        this.isOpaqueCube = false;
        this.renderAsNormalBlock = false;
        this.renderID = -1;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralInfuser();
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (world.blockHasTileEntity(x, y, z))
        {
            /*
             * TileEntityAstralInfuser te = (TileEntityAstralInfuser)
             * world.getBlockTileEntity(x, y, z);
             * 
             * if(player.getCurrentEquippedItem() != null) { te.heldItemID =
             * player.getCurrentEquippedItem().itemID; te.heldItemMeta =
             * player.getCurrentEquippedItem().getItemDamage();
             * 
             * if(!world.isRemote) { world.playSoundEffect(x, y, z,
             * "random.orb", 1.0f, 0.01f); } } else { te.heldItemID = 0;
             * te.heldItemMeta = 0; }
             */
            
            player.openGui(SorceryCraft.instance, GuiIDs.ASTRAL_INFUSER, world, x, y, z);
        }
        
        return true;
    }
}
