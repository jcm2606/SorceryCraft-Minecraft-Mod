package jcm2606.mods.sorcerycraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCube extends ModelBase
{
    // fields
    ModelRenderer Shape1;
    
    public ModelCube()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(-8F, -8F, -8F, 16, 16, 16);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0F, 0F, 0F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.Shape1.render(f5);
    }
    
    public void renderModel()
    {
        this.Shape1.render(0.0625f);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
