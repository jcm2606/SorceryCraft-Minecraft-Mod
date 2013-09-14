package jcm2606.mods.sorcerycraft.client.gui;

import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.inventory.Container;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiAlchBook extends GuiContainer
{
    public static int pageNum = 1;
    
    public static RenderItem itemRenderer = new RenderItem();
    
    public GuiAlchBook(Container par1Container)
    {
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
    public void initGui()
    {
        updateCounter2 = 0;
        int i1 = width / 2;
        int i2 = height / 2;
        this.buttonList.add(new Button(0, i1 - 186, i2 + (146 / 2), 16, 16, "\2478<<", "\2470<<", 0x000000, 0x000000));
        this.buttonList.add(new Button(1, i1 + 169, i2 + (146 / 2), 16, 16, "\2478>>", "\2470>>", 0x000000, 0x000000));
    }
    
    /**
     * Fired when a control is clicked. This is the equivalent of
     * ActionListener.actionPerformed(ActionEvent e).
     */
    @Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.id == 0)
        {
            if (pageNum > 1)
            {
                pageNum -= 2;
            }
        }
        
        if (button.id == 1)
        {
            pageNum += 2;
        }
    }
    
    /**
     * Fired when a key is typed. This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e).
     */
    @Override
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == 1 || par2 == mc.gameSettings.keyBindInventory.keyCode)
        {
            mc.displayGuiScreen((GuiScreen) null);
            mc.setIngameFocus();
        }
        
        if (par2 == Keyboard.KEY_LEFT)
        {
            if (pageNum > 1)
            {
                pageNum -= 2;
            }
        }
        
        if (par2 == Keyboard.KEY_RIGHT)
        {
            pageNum += 2;
        }
    }
    
    @Override
    public void onGuiClosed()
    {
        pageNum = 1;
    }
    
    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen()
    {
        super.updateScreen();
        ++updateCounter;
    }
    
    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int i, int j, float f)
    {
        int i1 = width / 2;
        int i2 = height / 2;
        
        super.drawScreen(i, j, f);
    }
    
    public void drawLeftPage()
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_GUI + "/book_alch/book_page_left.png");
        int i1 = width / 2 - 182;
        int i2 = height / 2 - 96;
        drawTexturedModalRect(i1 - 10, i2, 0, 0, 192, 192);
        if (pageNum == 1)
        {
            fontRenderer.drawString("\2478\247o\247nHermetical Compendium", i1 + 32, i2 + 13, 0xffffff);
            fontRenderer.drawString("\2478\247nIntroduction", i1, i2 + 26, 0xffffff);
            fontRenderer.drawString("\2478All throughout the realm of", i1, i2 + 36, 0xffffff);
            fontRenderer.drawString("\2478Minecraftia, the are a number of", i1, i2 + 46, 0xffffff);
            fontRenderer.drawString("\2478forces that keep the land running,", i1, i2 + 56, 0xffffff);
            fontRenderer.drawString("\2478perfectly. You will learn how to", i1, i2 + 66, 0xffffff);
            fontRenderer.drawString("\2478master and control some of these.", i1, i2 + 76, 0xffffff);
        }
        
        if (pageNum == 3)
        {
            fontRenderer.drawString("\2478\247nEnergy Types", i1, i2 + 26, 0xffffff);
            fontRenderer.drawString("\2479\247oVordic\247r\2478: Essential magical energy", i1, i2 + 36, 0xffffff);
            fontRenderer.drawString("\2478 that most magical items use.", i1, i2 + 46, 0xffffff);
            
            fontRenderer.drawString("\2479\247oMyst\247r\2478: The magical energy which", i1, i2 + 66, 0xffffff);
            fontRenderer.drawString("\2478fuels the thing people call", i1, i2 + 76, 0xffffff);
            fontRenderer.drawString("\2478Experience.", i1, i2 + 86, 0xffffff);
            
            fontRenderer.drawString("\2479\247oInfernal\247r\2478: The energy which rages", i1, i2 + 106, 0xffffff);
            fontRenderer.drawString("\2478inside wild fires.", i1, i2 + 116, 0xffffff);
            
            fontRenderer.drawString("\2479\247oFervor\247r\2478: The magical energy which", i1, i2 + 136, 0xffffff);
            fontRenderer.drawString("\2478gives living things life.", i1, i2 + 146, 0xffffff);
        }
    }
    
    public void drawRightPage()
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_GUI + "/book_alch/book_page_right.png");
        int i1 = width / 2;
        int i2 = height / 2 - 96;
        drawTexturedModalRect(i1, i2, 0, 0, 192, 192);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        drawLeftPage();
        drawRightPage();
        
        int i1_1 = width / 2;
        int i2_2 = height / 2;
        
        int pageNumRight = pageNum + 1;
        
        fontRenderer.drawString("\2478" + pageNum, i1_1 - (fontRenderer.getStringWidth("" + pageNum) + 3), i2_2 + (160 / 2), 0xffffff);
        fontRenderer.drawString("\2478" + pageNumRight, i1_1 + 4, i2_2 + (160 / 2), 0xffffff);
    }
}
