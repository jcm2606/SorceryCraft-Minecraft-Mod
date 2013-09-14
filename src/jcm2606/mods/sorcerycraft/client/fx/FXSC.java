package jcm2606.mods.sorcerycraft.client.fx;

import jcm2606.mods.jccore.fx.EntityFXJC;
import net.minecraft.world.World;

public class FXSC extends EntityFXJC
{
    public FXSC(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        super(par1World, par2, par4, par6, par8, par10, par12);
        this.modid = "sorcerycraft";
    }
    
    public FXSC(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
        this.modid = "sorcerycraft";
    }
}
