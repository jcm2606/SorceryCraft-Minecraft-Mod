package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralCapacitorIOInterface;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.core.lib.RenderID;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralCapacitorIOInterface extends SCBlockContainer
{
    public BlockAstralCapacitorIOInterface(int par1)
    {
        super(par1, Material.iron, "astralCapacitorIOInterface", Rarities.ADVANCED);
        this.setHardness(5.0f);
        this.setResistance(8.0f);
        this.renderID = RenderID.renderIDAstralCapacitor;
        this.renderAsNormalBlock = false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralCapacitorIOInterface();
    }
}
