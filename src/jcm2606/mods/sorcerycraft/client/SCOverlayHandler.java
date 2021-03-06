package jcm2606.mods.sorcerycraft.client;

import jcm2606.mods.jccore.core.util.RenderUtil;
import jcm2606.mods.sorcerycraft.api.IExpandedSightHandler;
import jcm2606.mods.sorcerycraft.astral.AstralManager;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyManipulationDrive;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

public class SCOverlayHandler implements IExpandedSightHandler
{
    @Override
    public void renderOverlay(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        this.drawEnergyHUD(mc, player, hasMedallion);
        this.drawEnergyManipulationDriveHUD(mc, player, hasMedallion);
        this.drawWandOverlay(mc, player);
    }
    
    public void drawEnergyHUD(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_GUI_HUD + "overlay.png");
        
        GL11.glPushMatrix();
        
        RenderUtil.instance().drawTextureRect(RenderUtil.width - 130, 5, 0, 0, 124, 7, 1.0f);
        RenderUtil.instance().drawTextureRect(RenderUtil.width - 126, 6, 4, 10, AstralManager.getEnergyPercentage(player), 7, 1.0f);
        
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        
        GL11.glScaled(0.5, 0.5, 0.5);
        
        mc.fontRenderer.drawString("\2477" + AstralManager.getTotalEnergy(player), RenderUtil.width * 2 - 27 - (mc.fontRenderer
                .getStringWidth("" + AstralManager.getTotalEnergy(player))), 13, 0xffffff);
        
        GL11.glScaled(1, 1, 1);
        
        GL11.glPopMatrix();
    }
    
    public void drawEnergyManipulationDriveHUD(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        GL11.glPushMatrix();
        
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_GUI_HUD + "overlay.png");
        
        if (player.inventory.getCurrentItem() != null)
        {
            if (player.inventory.getCurrentItem().getItem() instanceof ItemAstralEnergyManipulationDrive)
            {
                ItemAstralEnergyManipulationDrive drive = (ItemAstralEnergyManipulationDrive) player.inventory.getCurrentItem().getItem();
                
                if (drive.getMode(player.inventory.getCurrentItem()) == "INJECT")
                {
                    RenderUtil.instance().drawTextureRect(RenderUtil.width - 138, 1, 13, 17, 24, 9, 1.0f);
                } else
                {
                    RenderUtil.instance().drawTextureRect(RenderUtil.width - 138, 1, 0, 17, 12, 10, 1.0f);
                }
            }
        }
        
        GL11.glPopMatrix();
    }
    
    public void drawWandOverlay(Minecraft mc, EntityPlayer player)
    {
        GL11.glPushMatrix();
        
        /*if(player.getCurrentEquippedItem() != null)
        {
            if(player.getCurrentEquippedItem().getItem() instanceof ItemWand)
            {
                ItemStack stack = player.getCurrentEquippedItem();
                ItemWand wand = ((ItemWand) stack.getItem());
                
                mc.fontRenderer.drawString(wand.getSelectedAbility(stack).getName(), 0, 0, 0xffffff);
            }
        }*/
        
        GL11.glPopMatrix();
    }
    
    @Override
    public boolean canRender(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        return true;
    }
}
