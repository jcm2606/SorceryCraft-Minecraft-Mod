package jcm2606.mods.sorcerycraft.gui.note;

import jcm2606.mods.jccore.util.RenderUtil;
import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.inventory.Container;

import org.lwjgl.opengl.GL11;

public class GuiNoteCharm extends GuiContainer {
	public static RenderItem itemRenderer = new RenderItem();

	public GuiNoteCharm(Container par1Container) {
		super(par1Container);
	}

	private float xSize_lo;
	private float ySize_lo;

	/** Also counts the number of updates, not certain as to why yet. */
	private int updateCounter2 = 0;

	/** Counts the number of screen updates. */
	private int updateCounter = 0;

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@Override
	public void initGui() {
		updateCounter2 = 0;
		int i1 = width / 2;
		int i2 = height / 2;
	}

	/**
	 * Fired when a control is clicked. This is the equivalent of
	 * ActionListener.actionPerformed(ActionEvent e).
	 */
	@Override
	protected void actionPerformed(GuiButton button) {}

	/**
	 * Fired when a key is typed. This is the equivalent of
	 * KeyListener.keyTyped(KeyEvent e).
	 */
	@Override
	protected void keyTyped(char par1, int par2) {
		if (par2 == 1 || par2 == mc.gameSettings.keyBindInventory.keyCode) {
			mc.displayGuiScreen((GuiScreen) null);
			mc.setIngameFocus();
		}
	}

	@Override
	public void onGuiClosed() {}

	/**
	 * Called from the main game loop to update the screen.
	 */
	@Override
	public void updateScreen() {
		super.updateScreen();
		++updateCounter;
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int i, int j, float f) {
		int i1 = width / 2;
		int i2 = height / 2;

		super.drawScreen(i, j, f);
	}

	private void drawNote() {
		RenderEngine renderEngine = Minecraft.getMinecraft().renderEngine;
		
		renderEngine.bindTexture(Reference.PATH_TEXTURES + "gui/note/note_bg.png");
		RenderUtil.getInstance().drawTextureRect(RenderUtil.width / 2 - 110, RenderUtil.height / 2 - 110, 0, 0, 256, 256, 1.3f);
		
		FontRenderer fontRenderer = RenderUtil.fontRenderer;
		
		fontRenderer.drawString("\2478" + "\247o" + "Charmology", RenderUtil.width / 2 - (fontRenderer.getStringWidth("Charms") / 2 + 10), RenderUtil.height / 2 - 100, 0xffffff);
		
		GL11.glPushMatrix();
		GL11.glScaled(0.8, 0.8, 0.8);
		
		fontRenderer.drawString("\2478Charmology, a sidetrack of arconolgy,", RenderUtil.width / 2 - 38, RenderUtil.height / 2 - 80, 0xffffff);
		fontRenderer.drawString("\2478is the study and usage of magical", RenderUtil.width / 2 - 38, RenderUtil.height / 2 - 68, 0xffffff);
		fontRenderer.drawString("\2478devices called Charms. Charms come", RenderUtil.width / 2 - 38, RenderUtil.height / 2 - 56, 0xffffff);
		fontRenderer.drawString("\2478in many different types. One of the", RenderUtil.width / 2 - 38, RenderUtil.height / 2 - 44, 0xffffff);
		fontRenderer.drawString("\2478features of Charms that allow the", RenderUtil.width / 2 - 38, RenderUtil.height / 2 - 32, 0xffffff);
		fontRenderer.drawString("\2478amounts of energy that these", RenderUtil.width / 2 - 38, RenderUtil.height / 2 - 20, 0xffffff);
		fontRenderer.drawString("\2478wonderous devices hold to be", RenderUtil.width / 2 - 38, RenderUtil.height / 2 - 8, 0xffffff);
		fontRenderer.drawString("\2478contained within them is the use", RenderUtil.width / 2 - 38, RenderUtil.height / 2 + 4, 0xffffff);
		fontRenderer.drawString("\2478of Arcane Alloy.", RenderUtil.width / 2 - 38, RenderUtil.height / 2 + 16, 0xffffff);
		fontRenderer.drawString("\2478Arcane Alloy is a rather magical", RenderUtil.width / 2 - 38, RenderUtil.height / 2 + 40, 0xffffff);
		fontRenderer.drawString("\2478and hard-to-come-by object that", RenderUtil.width / 2 - 38, RenderUtil.height / 2 + 52, 0xffffff);
		fontRenderer.drawString("\2478allows sorcerers to create some of", RenderUtil.width / 2 - 38, RenderUtil.height / 2 + 64, 0xffffff);
		fontRenderer.drawString("\2478the most powerful devices craftable,", RenderUtil.width / 2 - 38, RenderUtil.height / 2 + 76, 0xffffff);
		fontRenderer.drawString("\2478even in arcane crafting devices...", RenderUtil.width / 2 - 38, RenderUtil.height / 2 + 88, 0xffffff);
		
		GL11.glScaled(1.0, 1.0, 1.0);
		GL11.glPopMatrix();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		drawNote();

		int i1_1 = width / 2;
		int i2_2 = height / 2;
	}
}