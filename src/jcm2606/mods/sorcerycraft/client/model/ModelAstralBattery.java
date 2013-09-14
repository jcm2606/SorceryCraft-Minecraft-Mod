package jcm2606.mods.sorcerycraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAstralBattery extends ModelBase
{
    // fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    
    public ModelAstralBattery()
    {
        textureWidth = 128;
        textureHeight = 64;
        
        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 15, 4, 15);
        Shape1.setRotationPoint(-7.5F, 20F, -7.5F);
        Shape1.setTextureSize(128, 64);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 20);
        Shape2.addBox(0F, 0F, 0F, 14, 2, 14);
        Shape2.setRotationPoint(-7F, 18F, -7F);
        Shape2.setTextureSize(128, 64);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 61, 0);
        Shape3.addBox(0F, 0F, 0F, 2, 3, 2);
        Shape3.setRotationPoint(-6.5F, 15F, 4.5F);
        Shape3.setTextureSize(128, 64);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 61, 0);
        Shape4.addBox(0F, 0F, 0F, 2, 3, 2);
        Shape4.setRotationPoint(-6.5F, 15F, -6.5F);
        Shape4.setTextureSize(128, 64);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 61, 0);
        Shape5.addBox(0F, 0F, 0F, 2, 3, 2);
        Shape5.setRotationPoint(4.5F, 15F, -6.5F);
        Shape5.setTextureSize(128, 64);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 61, 0);
        Shape6.addBox(0F, 0F, 0F, 2, 3, 2);
        Shape6.setRotationPoint(4.5F, 15F, 4.5F);
        Shape6.setTextureSize(128, 64);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
    }
    
    public void renderModel(float f5)
    {
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
