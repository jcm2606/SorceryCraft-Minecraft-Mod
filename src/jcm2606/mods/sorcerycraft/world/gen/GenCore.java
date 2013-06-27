package jcm2606.mods.sorcerycraft.world.gen;

import java.util.Random;

import jcm2606.mods.jccore.util.GeneratorBase;
import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.world.gen.structure.StructureAstralGate;
import jcm2606.mods.sorcerycraft.world.gen.structure.StructureAstralPlatform;
import net.minecraft.world.World;

public class GenCore extends GeneratorBase {
	@Override
    public void generateSurface(World world, Random rand, int blockX, int blockZ)
	{
		util.addOreGen(32, SCObjects.orevordic.blockID, 8, 7, blockX, blockZ, rand, world);
		util.addOreGen(16, SCObjects.orevordicgem.blockID, 5, 4, blockX, blockZ, rand, world);
		util.addOreGen(32, SCObjects.oreastral.blockID, 8, 2, blockX, blockZ, rand, world);
		util.addStructureGen(new StructureAstralGate(), blockX, blockZ, 10, rand, world);
		util.addStructureGen(new StructureAstralPlatform(), blockX, blockZ, 15, rand, world);
	}
	
	@Override
    public void generateNether(World world, Random rand, int blockX, int blockZ) {
	    util.addOreGen(18, SCObjects.emberstone.blockID, 9, 12 + rand.nextInt(3), blockX, blockZ, rand, world);
	}

    @Override
    public void generateEnd(World world, Random rand, int blockX, int blockZ) {}

    @Override
    public void generateOther(int dimensionID, World world, Random rand, int blockX, int blockZ) {}
}
