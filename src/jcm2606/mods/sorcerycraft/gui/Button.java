package jcm2606.mods.sorcerycraft.gui;

import jcm2606.mods.jccore.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Button extends GuiButton {
	/** Button width in pixels */
	protected int width;

	/** Button height in pixels */
	protected int height;

	/** The x position of this control. */
	public int xPosition;

	/** The y position of this control. */
	public int yPosition;

	/** The string displayed on this control. */
	public String displayStringIdle;
	public String displayStringHover;

	/** ID for this control. */
	public int id;

	/** True if this control is enabled, false to disable. */
	public boolean enabled;

	/** Hides the button completely if false. */
	public boolean drawButton;
	protected boolean field_82253_i;

	private int colour1;
	private int colour2;

	private final boolean customColours;

	public Button(int par1, int par2, int par3, int par4, int par5,
			String par6Str) {
		super(par1, par2, par3, par4, par5, par6Str);
		width = 200;
		height = 20;
		enabled = true;
		drawButton = true;
		id = par1;
		xPosition = par2;
		yPosition = par3;
		width = par4;
		height = par5;
		displayStringIdle = par6Str;
		displayStringHover = "\247e" + par6Str;
		customColours = false;
	}

	public Button(int par1, int par2, int par3, int par4, int par5,
			String par6Str, int par7Colour1, int par8Colour2) {
		super(par1, par2, par3, par4, par5, par6Str);
		width = 200;
		height = 20;
		enabled = true;
		drawButton = true;
		id = par1;
		xPosition = par2;
		yPosition = par3;
		width = par4;
		height = par5;
		displayStringIdle = par6Str;
		displayStringHover = "\247e" + par6Str;
		colour1 = par7Colour1;
		colour2 = par8Colour2;
		customColours = true;
	}

	public Button(int par1, int par2, int par3, int par4, int par5,
			String par6StrIdle, String par6StrActive, int par7Colour1,
			int par8Colour2) {
		super(par1, par2, par3, par4, par5, par6StrIdle);
		width = 200;
		height = 20;
		enabled = true;
		drawButton = true;
		id = par1;
		xPosition = par2;
		yPosition = par3;
		width = par4;
		height = par5;
		displayStringIdle = par6StrIdle;
		displayStringHover = par6StrActive;
		colour1 = par7Colour1;
		colour2 = par8Colour2;
		customColours = true;
	}

	public Button(int par1, int par2, int par3, int par4, int par5,
			String par6StrIdle, String par6StrActive) {
		super(par1, par2, par3, par4, par5, par6StrIdle);
		width = 200;
		height = 20;
		enabled = true;
		drawButton = true;
		id = par1;
		xPosition = par2;
		yPosition = par3;
		width = par4;
		height = par5;
		displayStringIdle = par6StrIdle;
		displayStringHover = par6StrActive;
		customColours = false;
	}

	/**
	 * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over
	 * this button and 2 if it IS hovering over this button.
	 */
	@Override
	protected int getHoverState(boolean par1) {
		byte var2 = 1;

		if (!enabled) {
			var2 = 0;
		} else if (par1) {
			var2 = 2;
		}

		return var2;
	}

	/**
	 * Draws this button to the screen.
	 */
	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
		if (drawButton) {
			FontRenderer var4 = par1Minecraft.fontRenderer;
			if (!customColours) {
			    RenderUtil.instance().bindTexture("minecraft", "gui/gui.png");
			}
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			field_82253_i = par2 >= xPosition && par3 >= yPosition
					&& par2 < xPosition + width && par3 < yPosition + height;
			int var5 = getHoverState(field_82253_i);
			if (customColours) {
				drawRect(xPosition, yPosition, 0, 46 + var5 * 20, colour1);
			} else {
				drawTexturedModalRect(xPosition, yPosition, 0, 46 + var5 * 20,
						width / 2, height);
				drawTexturedModalRect(xPosition + width / 2, yPosition,
						200 - width / 2, 46 + var5 * 20, width / 2, height);
			}
			mouseDragged(par1Minecraft, par2, par3);
			int var6 = 14737632;

			if (!enabled) {
				drawCenteredString(var4, "\2478" + displayStringIdle, xPosition
						+ width / 2, yPosition + (height - 8) / 2, var6);
			} else {
				if (getHoverState(field_82253_i) == 2) {
					drawCenteredString(var4, displayStringHover, xPosition
							+ width / 2, yPosition + (height - 8) / 2, var6);
				} else {
					drawCenteredString(var4, displayStringIdle, xPosition
							+ width / 2, yPosition + (height - 8) / 2, var6);
				}
			}
		}
	}

	/**
	 * Fired when the mouse button is dragged. Equivalent of
	 * MouseListener.mouseDragged(MouseEvent e).
	 */
	@Override
	protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {
	}

	/**
	 * Fired when the mouse button is released. Equivalent of
	 * MouseListener.mouseReleased(MouseEvent e).
	 */
	@Override
	public void mouseReleased(int par1, int par2) {
	}

	/**
	 * Returns true if the mouse has been pressed on this control. Equivalent of
	 * MouseListener.mousePressed(MouseEvent e).
	 */
	@Override
	public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {
		return enabled && drawButton && par2 >= xPosition && par3 >= yPosition
				&& par2 < xPosition + width && par3 < yPosition + height;
	}

	@Override
	public boolean func_82252_a() {
		return field_82253_i;
	}

	@Override
	public void func_82251_b(int par1, int par2) {
	}
}
