package jcm2606.mods.sorcerycraft.client.gui;

import jcm2606.mods.jccore.core.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Button extends GuiButton
{
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
    
    public Button(int par1, int par2, int par3, int par4, int par5, String par6Str)
    {
        super(par1, par2, par3, par4, par5, par6Str);
        this.width = 200;
        this.height = 20;
        this.enabled = true;
        this.drawButton = true;
        this.id = par1;
        this.xPosition = par2;
        this.yPosition = par3;
        this.width = par4;
        this.height = par5;
        this.displayStringIdle = par6Str;
        this.displayStringHover = "\247e" + par6Str;
        this.customColours = false;
    }
    
    public Button(int par1, int par2, int par3, int par4, int par5, String par6Str, int par7Colour1, int par8Colour2)
    {
        super(par1, par2, par3, par4, par5, par6Str);
        this.width = 200;
        this.height = 20;
        this.enabled = true;
        this.drawButton = true;
        this.id = par1;
        this.xPosition = par2;
        this.yPosition = par3;
        this.width = par4;
        this.height = par5;
        this.displayStringIdle = par6Str;
        this.displayStringHover = "\247e" + par6Str;
        this.colour1 = par7Colour1;
        this.colour2 = par8Colour2;
        this.customColours = true;
    }
    
    public Button(int par1, int par2, int par3, int par4, int par5, String par6StrIdle, String par6StrActive, int par7Colour1, int par8Colour2)
    {
        super(par1, par2, par3, par4, par5, par6StrIdle);
        this.width = 200;
        this.height = 20;
        this.enabled = true;
        this.drawButton = true;
        this.id = par1;
        this.xPosition = par2;
        this.yPosition = par3;
        this.width = par4;
        this.height = par5;
        this.displayStringIdle = par6StrIdle;
        this.displayStringHover = par6StrActive;
        this.colour1 = par7Colour1;
        this.colour2 = par8Colour2;
        this.customColours = true;
    }
    
    public Button(int par1, int par2, int par3, int par4, int par5, String par6StrIdle, String par6StrActive)
    {
        super(par1, par2, par3, par4, par5, par6StrIdle);
        this.width = 200;
        this.height = 20;
        this.enabled = true;
        this.drawButton = true;
        this.id = par1;
        this.xPosition = par2;
        this.yPosition = par3;
        this.width = par4;
        this.height = par5;
        this.displayStringIdle = par6StrIdle;
        this.displayStringHover = par6StrActive;
        this.customColours = false;
    }
    
    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over
     * this button and 2 if it IS hovering over this button.
     */
    @Override
    protected int getHoverState(boolean par1)
    {
        byte var2 = 1;
        
        if (!this.enabled)
        {
            var2 = 0;
        } else
            if (par1)
            {
                var2 = 2;
            }
        
        return var2;
    }
    
    /**
     * Draws this button to the screen.
     */
    @Override
    public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.drawButton)
        {
            FontRenderer var4 = par1Minecraft.fontRenderer;
            if (!this.customColours)
            {
                RenderUtil.instance().bindTexture("minecraft", "gui/gui.png");
            }
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_82253_i = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            int var5 = this.getHoverState(this.field_82253_i);
            if (this.customColours)
            {
                drawRect(this.xPosition, this.yPosition, 0, 46 + var5 * 20, this.colour1);
            } else
            {
                this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + var5 * 20, this.width / 2, this.height);
                this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + var5 * 20, this.width / 2,
                        this.height);
            }
            this.mouseDragged(par1Minecraft, par2, par3);
            int var6 = 14737632;
            
            if (!this.enabled)
            {
                this.drawCenteredString(var4, "\2478" + this.displayStringIdle, this.xPosition + this.width / 2,
                        this.yPosition + (this.height - 8) / 2, var6);
            } else
            {
                if (this.getHoverState(this.field_82253_i) == 2)
                {
                    this.drawCenteredString(var4, this.displayStringHover, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2,
                            var6);
                } else
                {
                    this.drawCenteredString(var4, this.displayStringIdle, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2,
                            var6);
                }
            }
        }
    }
    
    /**
     * Fired when the mouse button is dragged. Equivalent of
     * MouseListener.mouseDragged(MouseEvent e).
     */
    @Override
    protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3)
    {
    }
    
    /**
     * Fired when the mouse button is released. Equivalent of
     * MouseListener.mouseReleased(MouseEvent e).
     */
    @Override
    public void mouseReleased(int par1, int par2)
    {
    }
    
    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of
     * MouseListener.mousePressed(MouseEvent e).
     */
    @Override
    public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
    {
        return this.enabled && this.drawButton && par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
    }
    
    @Override
    public boolean func_82252_a()
    {
        return this.field_82253_i;
    }
    
    @Override
    public void func_82251_b(int par1, int par2)
    {
    }
}
