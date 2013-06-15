package jcm2606.mods.sorcerycraft.gui;

import jcm2606.mods.sorcerycraft.inventory.ContainerPortableCrafting;
import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;


public class GuiPortableCrafting extends GuiContainer {

    public GuiPortableCrafting(EntityPlayer player, World world, int x, int y, int z) {
        super(new ContainerPortableCrafting(player.inventory, world, x, y, z));
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString(StatCollector.translateToLocal("Formulation Tablet"), 78, 6, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    @Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(Reference.PATH_TEXTURES + "gui/tablet_creation.png");
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
    }
}
