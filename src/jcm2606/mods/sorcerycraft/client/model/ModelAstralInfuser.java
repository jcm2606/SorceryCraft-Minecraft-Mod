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
        this.textureWidth = 256;
        this.textureHeight = 128;
        
        this.Shape1 = new ModelRenderer(this, 66, 18);
        this.Shape1.addBox(0F, 0F, 0F, 16, 3, 16);
        this.Shape1.setRotationPoint(-8F, 21F, -8F);
        this.Shape1.setTextureSize(256, 128);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0F, 0F, 0F);
        this.Shape2 = new ModelRenderer(this, 0, 25);
        this.Shape2.addBox(0F, 0F, 0F, 3, 4, 3);
        this.Shape2.setRotationPoint(-7F, 16F, -7F);
        this.Shape2.setTextureSize(256, 128);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0F, 0F, 0F);
        this.Shape3 = new ModelRenderer(this, 0, 25);
        this.Shape3.addBox(0F, 0F, 0F, 3, 4, 3);
        this.Shape3.setRotationPoint(4F, 16F, 4F);
        this.Shape3.setTextureSize(256, 128);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0F, 0F, 0F);
        this.Shape4 = new ModelRenderer(this, 0, 25);
        this.Shape4.addBox(0F, 0F, 0F, 3, 4, 3);
        this.Shape4.setRotationPoint(-7F, 16F, 4F);
        this.Shape4.setTextureSize(256, 128);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0F, 0F, 0F);
        this.Shape5 = new ModelRenderer(this, 0, 25);
        this.Shape5.addBox(0F, 0F, 0F, 3, 4, 3);
        this.Shape5.setRotationPoint(4F, 16F, -7F);
        this.Shape5.setTextureSize(256, 128);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0F, 0F, 0F);
        this.Shape6 = new ModelRenderer(this, 0, 0);
        this.Shape6.addBox(0F, 0F, 0F, 16, 5, 16);
        this.Shape6.setRotationPoint(-8F, 10F, -8F);
        this.Shape6.setTextureSize(256, 128);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0F, 0F, 0F);
        this.Shape7 = new ModelRenderer(this, 66, 0);
        this.Shape7.addBox(0F, 0F, 0F, 15, 1, 15);
        this.Shape7.setRotationPoint(-7.5F, 20F, -7.5F);
        this.Shape7.setTextureSize(256, 128);
        this.Shape7.mirror = true;
        this.setRotation(this.Shape7, 0F, 0F, 0F);
        this.Shape8 = new ModelRenderer(this, 66, 0);
        this.Shape8.addBox(0F, 0F, 0F, 15, 1, 15);
        this.Shape8.setRotationPoint(-7.5F, 15F, -7.5F);
        this.Shape8.setTextureSize(256, 128);
        this.Shape8.mirror = true;
        this.setRotation(this.Shape8, 0F, 0F, 0F);
        this.Shape9 = new ModelRenderer(this, 66, 0);
        this.Shape9.addBox(0F, 0F, 0F, 15, 1, 15);
        this.Shape9.setRotationPoint(-7.5F, 9F, -7.5F);
        this.Shape9.setTextureSize(256, 128);
        this.Shape9.mirror = true;
        this.setRotation(this.Shape9, 0F, 0F, 0F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
        this.Shape7.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
    }
    
    public void render(float f5)
    {
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
        this.Shape7.render(f5);
        this.Shape8.render(f5);
        this.Shape9.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
