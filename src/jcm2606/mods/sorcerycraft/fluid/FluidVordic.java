package jcm2606.mods.sorcerycraft.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidVordic extends Fluid {
    public FluidVordic() {
        super("vordicFluid");
        this.setDensity(FluidRegistry.LAVA.getDensity());
        this.setViscosity(1500);
        FluidRegistry.registerFluid(this);
    }
}
