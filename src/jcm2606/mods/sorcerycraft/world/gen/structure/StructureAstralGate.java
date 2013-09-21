package jcm2606.mods.sorcerycraft.world.gen.structure;

import java.util.Random;

import jcm2606.mods.sorcerycraft.core.SCObjects;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class StructureAstralGate extends WorldGenerator
{
    protected int[] GetValidSpawnBlocks()
    {
        return new int[]
        { Block.grass.blockID, Block.dirt.blockID, Block.sand.blockID };
    }
    
    public boolean LocationIsValidSpawn(World world, int i, int j, int k)
    {
        int distanceToAir = 0;
        int checkID = world.getBlockId(i, j, k);
        
        while (checkID != 0)
        {
            distanceToAir++;
            checkID = world.getBlockId(i, j + distanceToAir, k);
        }
        
        if (distanceToAir > 0)
        {
            return false;
        }
        j += distanceToAir - 1;
        
        int blockID = world.getBlockId(i, j, k);
        int blockIDAbove = world.getBlockId(i, j + 1, k);
        int blockIDBelow = world.getBlockId(i, j - 1, k);
        for (int x : GetValidSpawnBlocks())
        {
            if (blockIDAbove != 0)
            {
                return false;
            }
            if (blockID == x)
            {
                return true;
            } else
                if (blockID == Block.snow.blockID && blockIDBelow == x)
                {
                    return true;
                }
        }
        return false;
    }
    
    public StructureAstralGate()
    {
    }
    
    @Override
    public boolean generate(World world, Random rand, int i, int j, int k)
    {
        // check that each corner is one of the valid spawn blocks
        if (!LocationIsValidSpawn(world, i, j, k) || !LocationIsValidSpawn(world, i + 4, j, k) || !LocationIsValidSpawn(world, i + 4, j, k + 4) || !LocationIsValidSpawn(
                world, i, j, k + 4))
        {
            return false;
        }
        
        world.setBlock(i + 0, j - 1, k + 0, Block.obsidian.blockID);
        world.setBlock(i + 0, j - 1, k + 1, Block.obsidian.blockID);
        world.setBlock(i + 0, j - 1, k + 2, Block.obsidian.blockID);
        world.setBlock(i + 0, j - 1, k + 3, Block.obsidian.blockID);
        world.setBlock(i + 0, j - 1, k + 4, Block.obsidian.blockID);
        world.setBlock(i + 1, j + 0, k + 1, Block.obsidian.blockID);
        world.setBlock(i + 1, j + 0, k + 3, Block.obsidian.blockID);
        world.setBlock(i + 1, j + world.rand.nextInt(2), k + 1, SCObjects.astralTotem1.blockID);
        world.setBlock(i + 1, j + world.rand.nextInt(2), k + 3, SCObjects.astralTotem1.blockID);
        world.setBlock(i + 1, j - 1, k + 0, Block.obsidian.blockID);
        world.setBlock(i + 1, j - 1, k + 1, Block.obsidian.blockID);
        world.setBlock(i + 1, j - 1, k + 2, Block.obsidian.blockID);
        world.setBlock(i + 1, j - 1, k + 3, Block.obsidian.blockID);
        world.setBlock(i + 1, j - 1, k + 4, Block.obsidian.blockID);
        world.setBlock(i + 2, j - 1, k + 0, Block.obsidian.blockID);
        world.setBlock(i + 2, j - 1, k + 1, Block.obsidian.blockID);
        world.setBlock(i + 2, j - 1, k + 2, SCObjects.astralObsidian.blockID);
        world.setBlock(i + 2, j - 1, k + 3, Block.obsidian.blockID);
        world.setBlock(i + 2, j - 1, k + 4, Block.obsidian.blockID);
        world.setBlock(i + 3, j - 1, k + 0, Block.obsidian.blockID);
        world.setBlock(i + 3, j - 1, k + 1, Block.obsidian.blockID);
        world.setBlock(i + 3, j - 1, k + 2, Block.obsidian.blockID);
        world.setBlock(i + 3, j - 1, k + 3, Block.obsidian.blockID);
        world.setBlock(i + 3, j - 1, k + 4, Block.obsidian.blockID);
        world.setBlock(i + 4, j - 1, k + 0, Block.obsidian.blockID);
        world.setBlock(i + 4, j - 1, k + 1, Block.obsidian.blockID);
        world.setBlock(i + 4, j - 1, k + 2, Block.obsidian.blockID);
        world.setBlock(i + 4, j - 1, k + 3, Block.obsidian.blockID);
        world.setBlock(i + 4, j - 1, k + 4, Block.obsidian.blockID);
        world.setBlock(i + 3, j + 0, k + 1, Block.obsidian.blockID);
        world.setBlock(i + 3, j + 0, k + 3, Block.obsidian.blockID);
        world.setBlock(i + 3, j + world.rand.nextInt(2), k + 1, SCObjects.astralTotem1.blockID);
        world.setBlock(i + 3, j + world.rand.nextInt(2), k + 3, SCObjects.astralTotem1.blockID);
        System.out.println("ASTRAL GATE GENERATED AT " + i + ", " + k);
        return true;
    }
}
