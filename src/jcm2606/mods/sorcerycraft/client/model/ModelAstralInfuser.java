package jcm2606.mods.sorcerycraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAstralInfuser extends ModelBase
{
    // fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    
    public ModelAstralInfuser()
    {
        textureWidth = 256;
        textureHeight = 128;
        
        Shape1 = new ModelRenderer(this, 66, 18);
        Shape1.addBox(0F, 0F, 0F, 16, 3, 16);
        Shape1.setRotationPoint(-8F, 21F, -8F);
        Shape1.setTextureSize(256, 128);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 25);
        Shape2.addBox(0F, 0F, 0F, 3, 4, 3);
        Shape2.setRotationPoint(-7F, 16F, -7F);
        Shape2.setTextureSize(256, 128);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 25);
        Shape3.addBox(0F, 0F, 0F, 3, 4, 3);
        Shape3.setRotationPoint(4F, 16F, 4F);
        Shape3.setTextureSize(256, 128);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 25);
        Shape4.addBox(0F, 0F, 0F, 3, 4, 3);
        Shape4.setRotationPoint(-7F, 16F, 4F);
        Shape4.setTextureSize(256, 128);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 25);
        Shape5.addBox(0F, 0F, 0F, 3, 4, 3);
        Shape5.setRotationPoint(4F, 16F, -7F);
        Shape5.setTextureSize(256, 128);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 0, 0);
        Shape6.addBox(0F, 0F, 0F, 16, 5, 16);
        Shape6.setRotationPoint(-8F, 10F, -8F);
        Shape6.setTextureSize(256, 128);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 66, 0);
        Shape7.addBox(0F, 0F, 0F, 15, 1, 15);
        Shape7.setRotationPoint(-7.5F, 20F, -7.5F);
        Shape7.setTextureSize(256, 128);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 66, 0);
        Shape8.addBox(0F, 0F, 0F, 15, 1, 15);
        Shape8.setRotationPoint(-7.5F, 15F, -7.5F);
        Shape8.setTextureSize(256, 128);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 66, 0);
        Shape9.addBox(0F, 0F, 0F, 15, 1, 15);
        Shape9.setRotationPoint(-7.5F, 9F, -7.5F);
        Shape9.setTextureSize(256, 128);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        Shape9.render(f5);
    }
    
    public void render(float f5)
    {
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        Shape9.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
