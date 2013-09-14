package jcm2606.mods.sorcerycraft.fluid;

import jcm2606.mods.sorcerycraft.core.SCObjects;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidVordic extends Fluid
{
    public FluidVordic()
    {
        super("vordicFluid");
        this.setDensity(FluidRegistry.WATER.getDensity());
        this.setViscosity(1000);
        this.setBlockID(SCObjects.ID_FLUID_VORDIC);
        FluidRegistry.registerFluid(this);
    }
}
