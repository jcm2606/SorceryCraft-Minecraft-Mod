package jcm2606.mods.sorcerycraft.client.render.item.astral;

import jcm2606.mods.jccore.render.ItemRendererBase;
import jcm2606.mods.sorcerycraft.block.SCBlock;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;

public class AstralCraftingNodeRenderItem extends ItemRendererBase
{
    public String side = "textures/blocks/astralCraftingNodeSide.png";
    public String top = "textures/blocks/astralCraftingNodeTop.png";
    
    @Override
    public void renderEquipped(float posX, float posY, float posZ, ItemStack stack)
    {
        Block block = Block.blocksList[stack.itemID];
        
        if (block != null)
        {
            if (block instanceof SCBlock)
            {
                SCBlock scBlock = (SCBlock) block;
                
                scBlock.renderID = 0;
                scBlock.renderAsNormalBlock = true;
                scBlock.isOpaqueCube = true;
                
                RenderHelper.disableStandardItemLighting();
                RenderHandlerSC.drawAstralParticleFieldStationary(posX, posY, posZ, 2.0f, side, side, side, side, top, side);
                RenderHelper.enableStandardItemLighting();
                
                scBlock.renderID = -1;
                scBlock.renderAsNormalBlock = true;
                scBlock.isOpaqueCube = true;
            }
        }
    }
    
    @Override
    public void renderEquippedFirstPerson(float posX, float posY, float posZ, ItemStack stack)
    {
        Block block = Block.blocksList[stack.itemID];
        
        if (block != null)
        {
            if (block instanceof SCBlock)
            {
                SCBlock scBlock = (SCBlock) block;
                
                scBlock.renderID = 0;
                scBlock.renderAsNormalBlock = true;
                scBlock.isOpaqueCube = true;
                
                RenderHelper.disableStandardItemLighting();
                RenderHandlerSC.drawAstralParticleFieldStationary(posX, posY, posZ, 2.0f, side, side, side, side, top, side);
                RenderHelper.enableStandardItemLighting();
                
                scBlock.renderID = -1;
                scBlock.renderAsNormalBlock = true;
                scBlock.isOpaqueCube = true;
            }
        }
    }
    
    @Override
    public void renderEntity(float posX, float posY, float posZ, ItemStack stack)
    {
        Block block = Block.blocksList[stack.itemID];
        
        if (block != null)
        {
            if (block instanceof SCBlock)
            {
                SCBlock scBlock = (SCBlock) block;
                
                scBlock.renderID = 0;
                scBlock.renderAsNormalBlock = true;
                scBlock.isOpaqueCube = true;
                
                RenderHelper.disableStandardItemLighting();
                RenderHandlerSC.drawAstralParticleFieldStationary(posX + 0.5, posY + 0.5, posZ + 0.5, 2.0f, side, side, side, side, top, side);
                RenderHelper.enableStandardItemLighting();
                
                scBlock.renderID = -1;
                scBlock.renderAsNormalBlock = true;
                scBlock.isOpaqueCube = true;
            }
        }
    }
    
    @Override
    public void renderInventory(float posX, float posY, float posZ, ItemStack stack)
    {
        Block block = Block.blocksList[stack.itemID];
        
        if (block != null)
        {
            if (block instanceof SCBlock)
            {
                SCBlock scBlock = (SCBlock) block;
                
                scBlock.renderID = 0;
                scBlock.renderAsNormalBlock = true;
                scBlock.isOpaqueCube = true;
                
                RenderHelper.disableStandardItemLighting();
                RenderHandlerSC.drawAstralParticleFieldStationary(posX, posY, posZ, 2.0f, side, side, side, side, top, side);
                RenderHelper.enableStandardItemLighting();
                
                scBlock.renderID = -1;
                scBlock.renderAsNormalBlock = true;
                scBlock.isOpaqueCube = true;
            }
        }
    }
}
