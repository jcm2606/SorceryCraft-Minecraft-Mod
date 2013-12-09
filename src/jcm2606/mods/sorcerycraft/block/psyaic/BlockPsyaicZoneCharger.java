package jcm2606.mods.sorcerycraft.block.psyaic;

import jcm2606.mods.sorcerycraft.block.SCBlockMultiblock;
import jcm2606.mods.sorcerycraft.block.tile.psyaic.TilePsyaicZoneCharger;
import jcm2606.mods.sorcerycraft.core.lib.RenderID;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPsyaicZoneCharger extends SCBlockMultiblock
{
    public BlockPsyaicZoneCharger(int par1)
    {
        super(par1, Material.iron, "astralZoneCharger");
        this.renderAsNormalBlock = false;
        this.renderID = RenderID.renderIDAstralZoneCharger;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TilePsyaicZoneCharger();
    }
}
