package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralStructure;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.core.lib.RenderID;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralStructure extends SCBlockContainer
{
    public BlockAstralStructure(int par1)
    {
        super(par1, Material.iron, "astralStructure", Rarities.BASIC);
        this.setHardness(4.0f);
        this.setResistance(6.0f);
        this.renderID = RenderID.renderIDAstralStructure;
        this.renderAsNormalBlock = false;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileAstralStructure();
    }
}
