package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.SCBlockMultiblock;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralZoneCharger;
import jcm2606.mods.sorcerycraft.core.lib.RenderID;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralZoneCharger extends SCBlockMultiblock
{
    public BlockAstralZoneCharger(int par1)
    {
        super(par1, Material.iron, "astralZoneCharger");
        this.renderAsNormalBlock = false;
        this.renderID = RenderID.renderIDAstralZoneCharger;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralZoneCharger();
    }
}
