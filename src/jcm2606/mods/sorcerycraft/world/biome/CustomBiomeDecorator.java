package jcm2606.mods.sorcerycraft.world.biome;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

class CustomBiomeDecorator extends BiomeDecorator
{
    
    static class Builder
    {
        
        // required parms
        private final BiomeGenBase biome;
        
        // optional parms - initialized to defaults
        private int waterlilyPerChunk = 0;
        private int treesPerChunk = 0;
        private int flowersPerChunk = 2;
        private int grassPerChunk = 1;
        private int deadBushPerChunk = 0;
        private int mushroomsPerChunk = 0;
        private int reedsPerChunk = 0;
        private int cactiPerChunk = 0;
        private int sandPerChunk = 1;
        private int sandPerChunk2 = 3;
        private int clayPerChunk = 1;
        private int bigMushroomsPerChunk = 0;
        
        Builder(BiomeGenBase biome)
        {
            this.biome = biome;
        }
        
        Builder bigMushroomsPerChunk(int val)
        {
            this.bigMushroomsPerChunk = val;
            return this;
        }
        
        CustomBiomeDecorator build()
        {
            return new CustomBiomeDecorator(this);
        }
        
        Builder cactiPerChunk(int val)
        {
            this.cactiPerChunk = val;
            return this;
        }
        
        Builder clayPerChunk(int val)
        {
            this.clayPerChunk = val;
            return this;
        }
        
        Builder deadBushPerChunk(int val)
        {
            this.deadBushPerChunk = val;
            return this;
        }
        
        Builder flowersPerChunk(int val)
        {
            this.flowersPerChunk = val;
            return this;
        }
        
        Builder grassPerChunk(int val)
        {
            this.grassPerChunk = val;
            return this;
        }
        
        Builder mushroomsPerChunk(int val)
        {
            this.mushroomsPerChunk = val;
            return this;
        }
        
        Builder reedsPerChunk(int val)
        {
            this.reedsPerChunk = val;
            return this;
        }
        
        Builder sandPerChunk(int val, int val2)
        {
            this.sandPerChunk = val;
            this.sandPerChunk2 = val2;
            return this;
        }
        
        Builder treesPerChunk(int val)
        {
            this.treesPerChunk = val;
            return this;
        }
        
        Builder waterlilyPerChunk(int val)
        {
            this.waterlilyPerChunk = val;
            return this;
        }
    }
    
    private CustomBiomeDecorator()
    {
        super(null);
    }
    
    private CustomBiomeDecorator(Builder builder)
    {
        super(builder.biome);
        
        this.waterlilyPerChunk = builder.waterlilyPerChunk;
        this.treesPerChunk = builder.treesPerChunk;
        this.flowersPerChunk = builder.flowersPerChunk;
        this.grassPerChunk = builder.grassPerChunk;
        this.deadBushPerChunk = builder.deadBushPerChunk;
        this.mushroomsPerChunk = builder.mushroomsPerChunk;
        this.reedsPerChunk = builder.reedsPerChunk;
        this.cactiPerChunk = builder.cactiPerChunk;
        this.sandPerChunk = builder.sandPerChunk;
        this.sandPerChunk2 = builder.sandPerChunk2;
        this.clayPerChunk = builder.clayPerChunk;
        this.bigMushroomsPerChunk = builder.bigMushroomsPerChunk;
    }
    
}
