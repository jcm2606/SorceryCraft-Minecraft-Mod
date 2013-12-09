package jcm2606.mods.sorcerycraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAstralEnergyNode extends ModelBase
{
    //fields
    ModelRenderer Plate_4;
    ModelRenderer Plate_3;
    ModelRenderer Plate_2;
    ModelRenderer Plate_1;
    ModelRenderer Leg_1;
    ModelRenderer Leg_2;
    ModelRenderer Leg_3;
    ModelRenderer Leg_4;
    ModelRenderer Centre_1;
    ModelRenderer Centre_2;
    ModelRenderer Centre_3;
    ModelRenderer Centre_4;
    
    public ModelAstralEnergyNode()
    {
        textureWidth = 128;
        textureHeight = 32;
        
        Plate_4 = new ModelRenderer(this, 48, 19);
        Plate_4.addBox(0F, 0F, 0F, 4, 1, 4);
        Plate_4.setRotationPoint(-2F, 19F, -2F);
        Plate_4.setTextureSize(128, 32);
        Plate_4.mirror = true;
        setRotation(Plate_4, 0F, 0F, 0F);
        Plate_3 = new ModelRenderer(this, 65, 17);
        Plate_3.addBox(0F, 0F, 0F, 14, 1, 14);
        Plate_3.setRotationPoint(-7F, 20F, -7F);
        Plate_3.setTextureSize(128, 32);
        Plate_3.mirror = true;
        setRotation(Plate_3, 0F, 0F, 0F);
        Plate_2 = new ModelRenderer(this, 65, 0);
        Plate_2.addBox(0F, 0F, 0F, 15, 1, 15);
        Plate_2.setRotationPoint(-7.5F, 21F, -7.5F);
        Plate_2.setTextureSize(128, 32);
        Plate_2.mirror = true;
        setRotation(Plate_2, 0F, 0F, 0F);
        Plate_1 = new ModelRenderer(this, 0, 0);
        Plate_1.addBox(0F, 0F, 0F, 16, 2, 16);
        Plate_1.setRotationPoint(-8F, 22F, -8F);
        Plate_1.setTextureSize(128, 32);
        Plate_1.mirror = true;
        setRotation(Plate_1, 0F, 0F, 0F);
        Leg_1 = new ModelRenderer(this, 0, 19);
        Leg_1.addBox(0F, 0F, 0F, 2, 4, 2);
        Leg_1.setRotationPoint(3.5F, 16F, -5F);
        Leg_1.setTextureSize(128, 32);
        Leg_1.mirror = true;
        setRotation(Leg_1, 0F, 0.7853982F, 0F);
        Leg_2 = new ModelRenderer(this, 0, 19);
        Leg_2.addBox(0F, 0F, 0F, 2, 4, 2);
        Leg_2.setRotationPoint(3.5F, 16F, 5F);
        Leg_2.setTextureSize(128, 32);
        Leg_2.mirror = true;
        setRotation(Leg_2, 0F, 0.7853982F, 0F);
        Leg_3 = new ModelRenderer(this, 0, 19);
        Leg_3.addBox(0F, 0F, 0F, 2, 4, 2);
        Leg_3.setRotationPoint(-6.5F, 16F, -5F);
        Leg_3.setTextureSize(128, 32);
        Leg_3.mirror = true;
        setRotation(Leg_3, 0F, 0.7853982F, 0F);
        Leg_4 = new ModelRenderer(this, 0, 19);
        Leg_4.addBox(0F, 0F, 0F, 2, 4, 2);
        Leg_4.setRotationPoint(-6.5F, 16F, 5F);
        Leg_4.setTextureSize(128, 32);
        Leg_4.mirror = true;
        setRotation(Leg_4, 0F, 0.7853982F, 0F);
        Centre_1 = new ModelRenderer(this, 9, 19);
        Centre_1.addBox(0F, 0F, 0F, 1, 1, 1);
        Centre_1.setRotationPoint(3.5F, 16F, 4.2F);
        Centre_1.setTextureSize(128, 32);
        Centre_1.mirror = true;
        setRotation(Centre_1, 0.7853982F, 0.7853982F, 0F);
        Centre_2 = new ModelRenderer(this, 9, 19);
        Centre_2.addBox(0F, 0F, 0F, 1, 1, 1);
        Centre_2.setRotationPoint(-5.3F, 16F, -4.5F);
        Centre_2.setTextureSize(128, 32);
        Centre_2.mirror = true;
        setRotation(Centre_2, 0.7853982F, 0.7853982F, 0F);
        Centre_3 = new ModelRenderer(this, 9, 19);
        Centre_3.addBox(0F, 0F, 0F, 1, 1, 1);
        Centre_3.setRotationPoint(-5.3F, 16F, 4.4F);
        Centre_3.setTextureSize(128, 32);
        Centre_3.mirror = true;
        setRotation(Centre_3, 0F, 0.7853982F, -0.7853982F);
        Centre_4 = new ModelRenderer(this, 9, 19);
        Centre_4.addBox(0F, 0F, 0F, 1, 1, 1);
        Centre_4.setRotationPoint(3.4F, 16F, -4.1F);
        Centre_4.setTextureSize(128, 32);
        Centre_4.mirror = true;
        setRotation(Centre_4, 0F, 0.7853982F, -0.7853982F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        Plate_4.render(f5);
        Plate_3.render(f5);
        Plate_2.render(f5);
        Plate_1.render(f5);
        Leg_1.render(f5);
        Leg_2.render(f5);
        Leg_3.render(f5);
        Leg_4.render(f5);
        Centre_1.render(f5);
        Centre_2.render(f5);
        Centre_3.render(f5);
        Centre_4.render(f5);
    }
    
    public void renderModel()
    {
        float f5 = 0.0625f;
        
        Plate_4.render(f5);
        Plate_3.render(f5);
        Plate_2.render(f5);
        Plate_1.render(f5);
        Leg_1.render(f5);
        Leg_2.render(f5);
        Leg_3.render(f5);
        Leg_4.render(f5);
        Centre_1.render(f5);
        Centre_2.render(f5);
        Centre_3.render(f5);
        Centre_4.render(f5);
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
}
