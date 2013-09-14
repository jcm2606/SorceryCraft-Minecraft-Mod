package jcm2606.mods.sorcerycraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAstralEnergyNodeBaseplate extends ModelBase
{
    // fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    
    public ModelAstralEnergyNodeBaseplate()
    {
        textureWidth = 64;
        textureHeight = 32;
        
        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 16, 3, 16);
        Shape1.setRotationPoint(-8F, 21F, -8F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 19);
        Shape2.addBox(0F, 0F, 0F, 8, 1, 8);
        Shape2.setRotationPoint(-4F, 20F, -4F);
        Shape2.setTextureSize(64, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
        Shape1.render(f5);
        Shape2.render(f5);
    }
    
    public void renderModel(float f5)
    {
        Shape1.render(f5);
        Shape2.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
