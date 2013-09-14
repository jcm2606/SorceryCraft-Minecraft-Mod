package jcm2606.mods.sorcerycraft.world.biome;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenValerianForest extends BiomeGenBase
{
    public BiomeGenValerianForest(int par1)
    {
        super(par1);
        this.setBiomeName("ValerianForest");
        this.color = 0x6CC417;
        this.waterColorMultiplier = 0x78C7C7;
        this.setTemperatureRainfall(0.6F, 0.4F);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 3, 1, 4));
        
        this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.bigMushroomsPerChunk = 2;
        this.theBiomeDecorator.flowersPerChunk = 12;
    }
}
