package jcm2606.mods.sorcerycraft.client.gui;

import java.util.HashMap;
import java.util.Map;

import jcm2606.mods.jccore.core.util.RenderUtil;
import jcm2606.mods.sorcerycraft.api.book.BookTab;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.research.ResearchData;
import jcm2606.mods.sorcerycraft.skill.SkillData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.opengl.GL11;

public class GuiBookKnowledge extends GuiScreen
{
    private final String PAGE_LEFT = Reference.PATH_TEXTURES_GUI + "bookKnowledge/pageLeft.png";
    private final String PAGE_RIGHT = Reference.PATH_TEXTURES_GUI + "bookKnowledge/pageRight.png";
    
    private Map tabs = new HashMap();
    
    private int currentTab = BookTab.playerInformation.getID();
    
    public GuiBookKnowledge()
    {
    }
    
    @Override
    protected void mouseClicked(int par1, int par2, int par3)
    {
        int tabX = RenderUtil.width / 2 - 174;
        int tabY = RenderUtil.height / 2 - 80;
        int separate = 0;
        
        int mouseX = par1;
        int mouseY = par2;
        
        for(BookTab tab : BookTab.getList())
        {
            if(tab == null)
            {
                continue;
            }
            
            if(mouseX >= tabX && mouseX <= tabX + 16 && mouseY >= tabY && mouseY <= tabY + 16)
            {
                this.currentTab = tab.getID();
            }
            
            tabY += 16;
        }
    }
    
    @Override
    protected void keyTyped(char par1, int par2)
    {
        super.keyTyped(par1, par2);
        
        if (par2 == Minecraft.getMinecraft().gameSettings.keyBindInventory.keyCode)
        {
            this.mc.displayGuiScreen((GuiScreen) null);
            this.mc.setIngameFocus();
        }
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        super.drawScreen(par1, par2, par3);
        
        GL11.glPushMatrix();
        
        this.drawLeftPage();
        this.drawRightPage();
        
        int width = RenderUtil.width / 2 + 20;
        int height = RenderUtil.height / 2 + 10;
        
        GL11.glPushMatrix();
        
        int tabX = RenderUtil.width / 2 - 176;
        int tabY = RenderUtil.height / 2 - 80;
        int separate = 0;
        
        int mouseX = par1;
        int mouseY = par2;
        
        RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_GUI + "bookKnowledge/icons.png");
        
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        FontRenderer fontUnknown = Minecraft.getMinecraft().standardGalacticFontRenderer;
        
        for(BookTab tab : BookTab.getList())
        {
            if(tab == null)
            {
                continue;
            }
            
            GL11.glPushMatrix();
            
            if(this.currentTab == tab.getID())
            {
                RenderUtil.instance().drawTextureRect(tabX, tabY, 0, 16, 18, 16, 1);
            } else {
                RenderUtil.instance().drawTextureRect(tabX, tabY, 0, 0, 18, 16, 1);
            }
            
            RenderUtil.instance().drawTextureRect(tabX + 6, tabY + 3, 0, tab.iconY, 10, 10, 1);
            
            GL11.glPopMatrix();
            
            GL11.glPushMatrix();
            
            GL11.glPopMatrix();
            
            tabY += 16;
        }
        
        GL11.glPopMatrix();
        
        if(currentTab == BookTab.playerInformation.getID())
        {
            font.drawString("\247nPlayer", width - 115, height - 86, 0x464646);
            
            GL11.glScaled(0.8, 0.8, 0.8);
            
            boolean hasWandAttributes = ((SkillData) Minecraft.getMinecraft().thePlayer.getExtendedProperties(SkillData.NAME)).getWandSkill() > 0;
            boolean hasResearchPoints = ((ResearchData) Minecraft.getMinecraft().thePlayer.getExtendedProperties(ResearchData.NAME)).getResearchPoints() > 0;
            
            this.drawKnowledgeText("\247n\247oAttributes", hasWandAttributes || hasResearchPoints, width - 140, height - 58, 0x464646);
            this.drawKnowledgeText("\2478 Wand Experience: " + ((SkillData) Minecraft.getMinecraft().thePlayer.getExtendedProperties(SkillData.NAME))
                    .getWandSkill(), hasWandAttributes, width - 140, height - 46, 0xffffff);
            this.drawKnowledgeText("\2478 Research Points: \247o" + ((ResearchData) Minecraft.getMinecraft().thePlayer
                    .getExtendedProperties(ResearchData.NAME)).getResearchPoints(), hasResearchPoints, width - 140, height - 34, 0xffffff);
        }
        
        if(currentTab == BookTab.lore.getID())
        {
            font.drawString("\247nLore", width - 114, height - 86, 0x464646);
            
            GL11.glScaled(0.8, 0.8, 0.8);
            
            font.drawString("\247n\247oPsy Energy", width - 140, height - 58, 0x464646);
            
            String[] loreText = {
                    "Our world is home to all different",
                    "forms of energy. We can tap into",
                    "these types of energy and use",
                    "them to make devices that we",
                    "cannot even dream of. However,",
                    "most of these forms of energy",
                    "are too dangerous / difficult to",
                    "acquire.",
                    "",
                    "One form of energy we can",
                    "acquire and use is one known",
                    "as Psy. Psy is a safe energy",
                    "form to use, at low levels, at",
                    "higher levels, it can be quite",
                    "troublesome.",
                    "",
                    "There are two main types of Psy,",
                    "Pure Psy, and Composite Psy."
            };
            
            String[] loreText2 = {
                    "Pure Psy is Psy in it's base form.",
                    "Composite Psy is a mixture of two",
                    "different energy types. We know",
                    "one is certainly Psy, however we",
                    "simply do not have the means to",
                    "accurately define what the other",
                    "type is.",
                    "",
                    "Composite Psy is not necessarily",
                    "bad. Think of Composite Psy as",
                    "just gunk that blocks Pure Psy",
                    "from taking effect. That is what",
                    "is bad. If Pure Psy cannot effect",
                    "the universe in the way it's meant",
                    "to, that's when things start to go",
                    "dark."
            };
            
            int x = width - 140;
            int y = height - 47;
            
            for(String s : loreText)
            {
                font.drawString("\2478" + s, x, y, 0xffffff);
                
                y += 9;
            }
            
            x = width + 40;
            y = height - 47;
            
            for(String s : loreText2)
            {
                font.drawString("\2478" + s, x, y, 0xffffff);
                
                y += 9;
            }
        }
        
        GL11.glPopMatrix();
    }
    
    @Override
    public void initGui()
    {
    }
    
    public void drawLeftPage()
    {
        RenderHandlerSC.bindTexture(this.PAGE_LEFT);
        
        RenderUtil.instance().drawTextureRect(RenderUtil.width / 2 - 159, RenderUtil.height / 2 - 90, 0, 0, 159, 180, 1.0f);
    }
    
    public void drawRightPage()
    {
        RenderHandlerSC.bindTexture(this.PAGE_RIGHT);
        
        RenderUtil.instance().drawTextureRect(RenderUtil.width / 2, RenderUtil.height / 2 - 90, 0, 0, 159, 180, 1.0f);
    }
    
    public void drawKnowledgeText(String text, boolean isValid, int x, int y, int colour)
    {
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        FontRenderer fontUnknown = Minecraft.getMinecraft().standardGalacticFontRenderer;
        
        if (isValid)
        {
            font.drawString(text, x, y, colour);
        } else
        {
            fontUnknown.drawString(text, x, y, colour);
        }
    }
    
    public void drawKnowledgeTextWithShadow(String text, boolean isValid, int x, int y, int colour)
    {
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        FontRenderer fontUnknown = Minecraft.getMinecraft().standardGalacticFontRenderer;
        
        if (isValid)
        {
            font.drawStringWithShadow(text, x, y, colour);
        } else
        {
            fontUnknown.drawStringWithShadow(text, x, y, colour);
        }
    }
}
