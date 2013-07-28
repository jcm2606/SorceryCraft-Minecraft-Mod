package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralEnergyNode;
import jcm2606.mods.sorcerycraft.block.tile.energy.TileEntityWirelessLink;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

public class BlockAstralEnergyNode extends BlockAstral {
    public BlockAstralEnergyNode(int par1) {
        super(par1, Material.iron, "astralEnergyNode", Rarities.BASIC, "astral_energy_node");
        this.isOpaqueCube = false;
        this.renderAsNormalBlock = false;
        this.renderID = -1;
        this.useIconIndex = false;
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityAstralEnergyNode();
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if(world.blockHasTileEntity(x, y, z))
        {
            if(world.getBlockTileEntity(x, y, z) instanceof TileEntityWirelessLink)
            {
                if(((TileEntityWirelessLink) world.getBlockTileEntity(x, y, z)).connectedCoordSet != null)
                {
                    player.addChatMessage("" + ((TileEntityWirelessLink) world.getBlockTileEntity(x, y, z)).hasPower + ", " + ((TileEntityWirelessLink) world.getBlockTileEntity(x, y, z)).connectedCoordSet.x + ", " + ((TileEntityWirelessLink) world.getBlockTileEntity(x, y, z)).connectedCoordSet.y + ", " + ((TileEntityWirelessLink) world.getBlockTileEntity(x, y, z)).connectedCoordSet.z + ", " + FMLCommonHandler.instance().getEffectiveSide());
                }
            }
        }
        
        return true;
    }
}
