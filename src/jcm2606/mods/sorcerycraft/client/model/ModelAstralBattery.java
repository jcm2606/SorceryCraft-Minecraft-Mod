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
        this.textureWidth = 128;
        this.textureHeight = 64;
        
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(0F, 0F, 0F, 15, 4, 15);
        this.Shape1.setRotationPoint(-7.5F, 20F, -7.5F);
        this.Shape1.setTextureSize(128, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0F, 0F, 0F);
        this.Shape2 = new ModelRenderer(this, 0, 20);
        this.Shape2.addBox(0F, 0F, 0F, 14, 2, 14);
        this.Shape2.setRotationPoint(-7F, 18F, -7F);
        this.Shape2.setTextureSize(128, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0F, 0F, 0F);
        this.Shape3 = new ModelRenderer(this, 61, 0);
        this.Shape3.addBox(0F, 0F, 0F, 2, 3, 2);
        this.Shape3.setRotationPoint(-6.5F, 15F, 4.5F);
        this.Shape3.setTextureSize(128, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0F, 0F, 0F);
        this.Shape4 = new ModelRenderer(this, 61, 0);
        this.Shape4.addBox(0F, 0F, 0F, 2, 3, 2);
        this.Shape4.setRotationPoint(-6.5F, 15F, -6.5F);
        this.Shape4.setTextureSize(128, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0F, 0F, 0F);
        this.Shape5 = new ModelRenderer(this, 61, 0);
        this.Shape5.addBox(0F, 0F, 0F, 2, 3, 2);
        this.Shape5.setRotationPoint(4.5F, 15F, -6.5F);
        this.Shape5.setTextureSize(128, 64);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0F, 0F, 0F);
        this.Shape6 = new ModelRenderer(this, 61, 0);
        this.Shape6.addBox(0F, 0F, 0F, 2, 3, 2);
        this.Shape6.setRotationPoint(4.5F, 15F, 4.5F);
        this.Shape6.setTextureSize(128, 64);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0F, 0F, 0F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
    }
    
    public void renderModel(float f5)
    {
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
