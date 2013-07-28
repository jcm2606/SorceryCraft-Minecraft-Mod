package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralStaticCharger;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralStaticCharger extends BlockAstral {
    public BlockAstralStaticCharger(int par1) {
        super(par1, Material.rock, "astralStaticCharger", Rarities.ADVANCED, "astral_static_charger");
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityAstralStaticCharger();
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        return true;
    }
}
