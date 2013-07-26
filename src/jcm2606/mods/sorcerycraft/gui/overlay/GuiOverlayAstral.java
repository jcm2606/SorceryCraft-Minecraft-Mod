package jcm2606.mods.sorcerycraft.gui.overlay;

import java.util.Collection;
import java.util.HashMap;

import jcm2606.mods.jccore.util.RenderUtil;
import jcm2606.mods.sorcerycraft.api.AstralManager;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.EnumUseType;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.GauntletMode;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import jcm2606.mods.sorcerycraft.core.config.Settings;
import jcm2606.mods.sorcerycraft.core.helper.RenderHelperSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyCell;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralGauntlet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;

public class GuiOverlayAstral extends Gui {
    private final Minecraft mc;
    
    public GuiOverlayAstral(Minecraft mc)
    {
        super();
        
        this.mc = mc;
    }
    
    @ForgeSubscribe(priority = EventPriority.NORMAL)
    public void drawOverlay(RenderGameOverlayEvent event)
    {
        if(event.isCancelable() || event.type != ElementType.HOTBAR || mc.gameSettings.showDebugInfo)
        {
            return;
        }
        
        if(mc.thePlayer.getCurrentEquippedItem() != null)
        {
            if(mc.thePlayer.getCurrentEquippedItem().getItem() == SCObjects.astralgauntlet)
            {
                renderAstralGauntletOverlay(mc, mc.thePlayer, mc.thePlayer.getCurrentEquippedItem());
            }
        }
        
        if(mc.thePlayer.inventory.hasItem(SCObjects.astralenergycell.itemID))
        {
            if(mc.thePlayer.getCurrentEquippedItem() != null)
            {
                if(mc.thePlayer.getCurrentEquippedItem().getItem() == SCObjects.astralgauntlet)
                {
                    return;
                }
            }
            
            this.renderAstralEnergyOverlay(mc, mc.thePlayer);
        }
    }
    
    private void renderAstralEnergyOverlay(Minecraft minecraft, EntityPlayer player)
    {
        int maxEnergy = 0;
        int currentEnergy = 0;
        int cellsInInv = 0;
        int fullCellsInInv = 0;
        
        int offsetX = 0;
        int offsetText = 0;
        
        HashMap<Integer, ItemStack> stackList = new HashMap<Integer, ItemStack>();
        
        for(int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack2 = player.inventory.mainInventory[i];
            
            if(stack2 != null)
            {
                if(stack2.getItem() == SCObjects.astralenergycell)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack2.getItem();
                    
                    maxEnergy = maxEnergy + 1000;
                    currentEnergy = currentEnergy + (1000 - cell.getEnergy(stack2));
                    cellsInInv++;
                    
                    if(!(cellsInInv > 4))
                    {
                        stackList.put(i, stack2);
                    }
                }
            }
        }
        
        Collection<ItemStack> stackCollection = stackList.values();
        
        for(ItemStack stack : stackCollection)
        {
            ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack.getItem();
            int currentCellEnergy = 1000 - cell.getEnergy(stack);
            
            RenderHelperSC.bindTexture(Reference.PATH_TEXTURES_GUI_HUD + "astral_energy_cell_hud.png");
            
            if(SorceryCraft.instance.specialPlayers.containsKey(player.username))
            {
                RenderUtil.instance().drawTextureRect(1, 1 + offsetX, 0, 0, 256, 256, 0.05f, 0.05f, 0.05f, Settings.SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_RED, Settings.SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_GREEN, Settings.SPECIAL_PLAYER_ASTRAL_ENERGY_ORB_EMPTY_COLOUR_BLUE);
            } else {
                RenderUtil.instance().drawTextureRect(1, 1 + offsetX, 0, 0, 256, 256, 0.05f, 0.05f, 0.05f, 0.5, 0.5, 0.5);
            }
            
            if(cell.getEnergy(stack) < 1000)
            {
                fullCellsInInv++;
                
                int scale = currentCellEnergy / 4;
                
                RenderHelperSC.bindTexture(Reference.PATH_TEXTURES_GUI_HUD + "astral_energy_cell_hud.png");
                RenderUtil.instance().drawTextureRect(1, 1 + offsetX, 0, 0, scale, 256, 0.05f, 0.05f, 0.05f, 1.0, 1.0, 1.0);
            }
            
            minecraft.fontRenderer.drawStringWithShadow("\2477" + currentCellEnergy, 16, 3 + offsetText, 0xffffff);
            
            offsetX += 256 + 48;
            offsetText += 15;
        }
    }
    
    private void renderAstralGauntletOverlay(Minecraft minecraft, EntityPlayer player, ItemStack stack)
    {
        int currentEnergy = 0;
        
        for(int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack2 = player.inventory.mainInventory[i];
            
            if(stack2 != null)
            {
                if(stack2.getItem() == SCObjects.astralenergycell)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack2.getItem();
                    
                    currentEnergy = currentEnergy + (1000 - cell.getEnergy(stack2));
                }
            }
        }
        
        ItemAstralGauntlet gauntlet = (ItemAstralGauntlet) stack.getItem();
        
        if(player.inventory.hasItem(SCObjects.astralenergycell.itemID))
        {
            minecraft.fontRenderer.drawStringWithShadow("\2477" + AstralManager.getMode(gauntlet.getMode(stack)).name, RenderUtil.width - minecraft.fontRenderer.getStringWidth(AstralManager.getMode(gauntlet.getMode(stack)).name) - 2, 2, 0xffffff);
        } else
            if(!AstralManager.getMode(gauntlet.getMode(stack)).hasErroredName)
            {
                minecraft.fontRenderer.drawStringWithShadow("\2477" + AstralManager.getMode(gauntlet.getMode(stack)).name, RenderUtil.width - minecraft.fontRenderer.getStringWidth(AstralManager.getMode(gauntlet.getMode(stack)).name) - 2, 2, 0xffffff);
            } else
                {
                    minecraft.fontRenderer.drawStringWithShadow("\247c" + AstralManager.getMode(gauntlet.getMode(stack)).name, RenderUtil.width - minecraft.fontRenderer.getStringWidth(AstralManager.getMode(gauntlet.getMode(stack)).name) - 2, 2, 0xffffff);
                }
        
        GauntletMode mode = AstralManager.getMode(gauntlet.getMode(stack));
        
        HashMap<EnumUseType, Integer> energyRequirementMap = new HashMap<EnumUseType, Integer>();
        
        for(EnumUseType type : EnumUseType.values())
        {
            energyRequirementMap.put(type, mode.energyRequired(type, player));
        }
        
        Collection<Integer> energyRequirementCollection = energyRequirementMap.values();
        
        int offsetText = 15;
        
        for(EnumUseType type : EnumUseType.values())
        {
            if(energyRequirementMap.get(type) <= 0)
            {
                continue;
            }
            
            if(currentEnergy < energyRequirementMap.get(type))
            {
                minecraft.fontRenderer.drawStringWithShadow("\247c\247o" + energyRequirementMap.get(type) + " [" + type.suffix + "]", RenderUtil.width - minecraft.fontRenderer.getStringWidth(energyRequirementMap.get(type) + " [" + type.suffix + "]") - 2, offsetText, 0xffffff);
            } else {
                minecraft.fontRenderer.drawStringWithShadow("\2477\247o" + energyRequirementMap.get(type) + " [" + type.suffix + "]", RenderUtil.width - minecraft.fontRenderer.getStringWidth(energyRequirementMap.get(type) + " [" + type.suffix + "]") - 2, offsetText, 0xffffff);
            }
            
            offsetText += 10;
        }
        
        this.renderAstralEnergyOverlay(minecraft, player);
    }
}
