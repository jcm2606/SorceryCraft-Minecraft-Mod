package jcm2606.mods.sorcerycraft.client.model;

import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelAstralGauntlet
{
    private final IModelCustom gauntletModel;
    
    public ModelAstralGauntlet()
    {
        gauntletModel = AdvancedModelLoader.loadModel("/assets/sorcerycraft/" + Reference.PATH_MODELS + "AstralGauntlet.obj");
    }
    
    private void render()
    {
        gauntletModel.renderAll();
    }
    
    public void render(double x, double y, double z)
    {
        this.render();
    }
}
