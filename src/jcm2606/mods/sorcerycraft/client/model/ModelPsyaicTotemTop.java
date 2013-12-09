package jcm2606.mods.sorcerycraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPsyaicTotemTop extends ModelBase
{
    //fields
    ModelRenderer Base;
    ModelRenderer Center;
    ModelRenderer Top;
    
    public ModelPsyaicTotemTop()
    {
        textureWidth = 128;
        textureHeight = 64;
        
        Base = new ModelRenderer(this, 0, 0);
        Base.addBox(0F, 0F, 0F, 16, 6, 16);
        Base.setRotationPoint(-8F, 18F, -8F);
        Base.setTextureSize(128, 64);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        Center = new ModelRenderer(this, 0, 23);
        Center.addBox(0F, 0F, 0F, 12, 4, 12);
        Center.setRotationPoint(-6F, 14F, -6F);
        Center.setTextureSize(128, 64);
        Center.mirror = true;
        setRotation(Center, 0F, 0F, 0F);
        Top = new ModelRenderer(this, 0, 0);
        Top.addBox(0F, 0F, 0F, 16, 6, 16);
        Top.setRotationPoint(-8F, 8F, -8F);
        Top.setTextureSize(128, 64);
        Top.mirror = true;
        setRotation(Top, 0F, 0F, 0F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        Base.render(f5);
        Center.render(f5);
        Top.render(f5);
    }
    
    public void renderModel()
    {
        float f5 = 0.0625f;
        
        Base.render(f5);
        //Center.render(f5);
        //Top.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
