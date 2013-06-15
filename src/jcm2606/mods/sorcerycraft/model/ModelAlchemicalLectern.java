package jcm2606.mods.sorcerycraft.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAlchemicalLectern extends ModelBase {
	// fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;

	public ModelAlchemicalLectern() {
		textureWidth = 256;
		textureHeight = 256;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 16, 3, 16);
		Shape1.setRotationPoint(-8F, 21F, -8F);
		Shape1.setTextureSize(256, 256);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 66, 0);
		Shape2.addBox(0F, 0F, 0F, 15, 1, 15);
		Shape2.setRotationPoint(-7.5F, 20F, -7.5F);
		Shape2.setTextureSize(256, 256);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 129, 0);
		Shape3.addBox(0F, 0F, 0F, 14, 7, 14);
		Shape3.setRotationPoint(-7F, 13F, -7F);
		Shape3.setTextureSize(256, 256);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 22);
		Shape4.addBox(0F, 0F, 0F, 13, 3, 13);
		Shape4.setRotationPoint(-6.5F, 11F, -6.5F);
		Shape4.setTextureSize(256, 256);
		Shape4.mirror = true;
		setRotation(Shape4, 0.0698132F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 56, 22);
		Shape5.addBox(0F, 0F, 0F, 12, 2, 12);
		Shape5.setRotationPoint(-6F, 10F, -6F);
		Shape5.setTextureSize(256, 256);
		Shape5.mirror = true;
		setRotation(Shape5, 0.1047198F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
	}

	public void renderModel(float f5) {
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	/**
	 * Sets the model's various rotation angles. For bipeds, par1 and par2 are
	 * used for animating the movement of arms and legs, where par1 represents
	 * the time(so that arms and legs swing back and forth) and par2 represents
	 * how "far" arms and legs can swing at most.
	 */
	public void setRotationAngles(float par1, float par2, float par3,
			float par4, float par5, float par6, Entity par7Entity) {
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
	}

}
