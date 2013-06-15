package jcm2606.mods.sorcerycraft.world.biome;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenValerianForest extends BiomeGenBase {
	public BiomeGenValerianForest(int par1) {
		super(par1);
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 3, 1, 4));
		this.setBiomeName("ValerianForest");
		this.color = 0x6CC417;
		this.waterColorMultiplier = 0x736AFF;
		this.setTemperatureRainfall(0.6F, 0.4F);
		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.grassPerChunk = 2;
		this.topBlock = (byte) Block.grass.blockID;
		this.fillerBlock = (byte) Block.dirt.blockID;
	}

	@Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).treesPerChunk(3).grassPerChunk(2).flowersPerChunk(8).build();
    }
	
	@Override
	public float getSpawningChance() {
		return 0.5F;
	}
}
