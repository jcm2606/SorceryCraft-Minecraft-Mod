package jcm2606.mods.sorcerycraft.client.gui;

import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralInfuser;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.inventory.ContainerAstralInfuser;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class GuiAstralInfuser extends GuiContainer
{
    int x;
    int y;
    int z;
    
    public GuiAstralInfuser(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5)
    {
        super(new ContainerAstralInfuser(par1InventoryPlayer, par2World, par3, par4, par5));
        this.ySize = 239;
        this.xSize = 202;
        
        this.x = par3;
        this.y = par4;
        this.z = par5;
    }
    
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of
     * the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(StatCollector.translateToLocal("\2478\247oAstral"), 8, 7, 0xA74AC7);
        this.fontRenderer.drawString(StatCollector.translateToLocal("\2478\247oInfuser"), 8, 17, 0xA74AC7);
    }
    
    /**
     * Draw the background layer for the GuiContainer (everything behind the
     * items)
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES + "gui/astralInfuser.png");
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        
        float f = 0.25f;
        if (this.mc.theWorld.getBlockTileEntity(this.x, this.y, this.z) != null)
        {
            TileAstralInfuser tile = (TileAstralInfuser) this.mc.theWorld.getBlockTileEntity(this.x, this.y, this.z);
            
            if (tile.hasSource())
            {
                f = 1.0f;
            }
        }
        GL11.glColor4f(f, f, f, 1.0F);
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES + "gui/astralInfuserGlow.png");
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
    }
}
