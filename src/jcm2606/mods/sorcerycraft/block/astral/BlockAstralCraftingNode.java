package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAstralCraftingNode extends BlockAstral
{
    public BlockAstralCraftingNode(int par1)
    {
        super(par1, Material.rock, "astralCraftingNode", Rarities.ADVANCED, "astral_crafting_node");
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return null;
    }
}
