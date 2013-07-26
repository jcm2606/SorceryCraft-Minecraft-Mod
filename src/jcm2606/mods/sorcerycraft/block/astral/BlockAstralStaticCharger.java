package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralStaticCharger;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.energy.IEnergyConsumer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralStaticCharger extends SCBlockContainer {
    public BlockAstralStaticCharger(int par1) {
        super(par1, Material.rock, "astralStaticCharger", Rarities.ADVANCED);
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityAstralStaticCharger();
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if(world.blockHasTileEntity(x, y, z))
        {
            if(world.getBlockTileEntity(x, y, z) instanceof IEnergyConsumer)
            {
                TileEntityAstralStaticCharger chargerTE = (TileEntityAstralStaticCharger) world.getBlockTileEntity(x, y, z);
                
                chargerTE.transferEnergyToPlayer(player);
            }
        }
        
        return true;
    }
}
