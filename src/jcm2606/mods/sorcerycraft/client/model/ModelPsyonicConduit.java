package jcm2606.mods.sorcerycraft.client.model;

import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.IConduitConnector;
import jcm2606.mods.sorcerycraft.block.tile.psyonic.TilePsyonicConduit;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.ForgeDirection;

public class ModelPsyonicConduit extends ModelBase
{
    //fields
    ModelRenderer Base;
    ModelRenderer Front;
    ModelRenderer Top;
    ModelRenderer Right;
    ModelRenderer Bottom;
    ModelRenderer Back;
    ModelRenderer Left;
    
    public ModelPsyonicConduit()
    {
        textureWidth = 64;
        textureHeight = 32;
        
        Base = new ModelRenderer(this, 0, 0);
        Base.addBox(0F, 0F, 0F, 5, 5, 5);
        Base.setRotationPoint(-2.5F, 13.5F, -2.5F);
        Base.setTextureSize(64, 32);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        Front = new ModelRenderer(this, 0, 11);
        Front.addBox(0F, 0F, 0F, 6, 4, 4);
        Front.setRotationPoint(-2F, 14F, -2F);
        Front.setTextureSize(64, 32);
        Front.mirror = true;
        setRotation(Front, 0F, 1.570796F, 0F);
        Top = new ModelRenderer(this, 0, 11);
        Top.addBox(0F, 0F, 0F, 6, 4, 4);
        Top.setRotationPoint(-2F, 14F, -2F);
        Top.setTextureSize(64, 32);
        Top.mirror = true;
        setRotation(Top, 0F, 0F, -1.570796F);
        Right = new ModelRenderer(this, 0, 11);
        Right.addBox(0F, 0F, 0F, 6, 4, 4);
        Right.setRotationPoint(-8F, 14F, -2F);
        Right.setTextureSize(64, 32);
        Right.mirror = true;
        setRotation(Right, 0F, 0F, 0F);
        Bottom = new ModelRenderer(this, 0, 11);
        Bottom.addBox(0F, 0F, 0F, 6, 4, 4);
        Bottom.setRotationPoint(-2F, 24F, -2F);
        Bottom.setTextureSize(64, 32);
        Bottom.mirror = true;
        setRotation(Bottom, 0F, 0F, -1.570796F);
        Back = new ModelRenderer(this, 0, 11);
        Back.addBox(0F, 0F, 0F, 6, 4, 4);
        Back.setRotationPoint(-2F, 14F, 8F);
        Back.setTextureSize(64, 32);
        Back.mirror = true;
        setRotation(Back, 0F, 1.570796F, 0F);
        Left = new ModelRenderer(this, 0, 11);
        Left.addBox(0F, 0F, 0F, 6, 4, 4);
        Left.setRotationPoint(2F, 14F, -2F);
        Left.setTextureSize(64, 32);
        Left.mirror = true;
        setRotation(Left, 0F, 0F, 0F);
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        Base.render(f5);
        Front.render(f5);
        Top.render(f5);
        Right.render(f5);
        Bottom.render(f5);
        Back.render(f5);
        Left.render(f5);
    }
    
    public void renderModel(TilePsyonicConduit tile)
    {
        float f5 = 0.0625f;
        
        Base.render(f5);
        
        for(ForgeDirection side : ForgeDirection.VALID_DIRECTIONS)
        {
            if(GeneralUtil.getBlockTileFromNeighbour(tile.xCoord, tile.yCoord, tile.zCoord, side, tile.worldObj) instanceof IConduitConnector)
            {
                this.getRectForSide(side).render(f5);
            }
        }
    }
    
    public ModelRenderer getRectForSide(ForgeDirection side)
    {
        ModelRenderer model = Base;
        
        if(side != null)
        {
            if(side.equals(ForgeDirection.UP))
            {
                model = this.Top;
            }
            
            if(side.equals(ForgeDirection.DOWN))
            {
                model = this.Bottom;
            }
            
            if(side.equals(ForgeDirection.EAST))
            {
                model = this.Left;
            }
            
            if(side.equals(ForgeDirection.WEST))
            {
                model = this.Right;
            }
            
            if(side.equals(ForgeDirection.NORTH))
            {
                model = this.Back;
            }
            
            if(side.equals(ForgeDirection.SOUTH))
            {
                model = this.Front;
            }
        }
        
        return model;
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
