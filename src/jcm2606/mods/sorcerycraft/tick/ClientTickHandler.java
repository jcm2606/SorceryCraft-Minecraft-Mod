package jcm2606.mods.sorcerycraft.tick;

import java.util.HashMap;
import java.util.Map;

import jcm2606.mods.jccore.tick.TickHandlerClientBase;
import jcm2606.mods.jccore.util.RenderUtil;
import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.SorceryCraft;
import jcm2606.mods.sorcerycraft.config.Settings;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyCell;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.AstralGauntletManager;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.ItemAstralGauntlet;
import jcm2606.mods.sorcerycraft.item.main.ItemInvisCloak;
import jcm2606.mods.sorcerycraft.item.wand.ItemWandCasting;
import jcm2606.mods.sorcerycraft.item.wand.WandManager;
import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

public class ClientTickHandler extends TickHandlerClientBase {
    private static int playerCounter = -1;
    public final static Map capes = new HashMap();

    @Override
    public void onClientWorldLoad(Minecraft mc, World world)
    {
        SorceryCraft.instance.loadCapes();

        if (Settings.WORLD_LOAD_MESSAGE) {
            mc.thePlayer.addChatMessage("SorceryCraft v" + SorceryCraft.version + " loaded.");
        }
    }

    @Override
    public void onClientWorldUnload(Minecraft mc, World world)
    {
    }

    @Override
    public void onClientWorldTick(Minecraft mc, World world)
    {
        if ((world != null) && (world.playerEntities.size() > 0)) {
            playerCounter += 1;
            if (playerCounter >= world.playerEntities.size()) {
                playerCounter = 0;
            }

            EntityPlayer lplayer = (EntityPlayer) world.playerEntities.get(playerCounter);
            String cape = (String) capes.get(lplayer.username);
            if (cape != null) {
                String oldCape = lplayer.cloakUrl;
                lplayer.cloakUrl = (lplayer.cloakUrl = cape);

                if (oldCape != cape) {
                    mc.renderEngine.obtainImageData(lplayer.cloakUrl, null);
                }

            }
        }
    }

    @Override
    public void onHUDTick(Minecraft mc)
    {
        RenderUtil.sr = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth,
                Minecraft.getMinecraft().displayHeight);

        RenderUtil.width = RenderUtil.sr.getScaledWidth();
        RenderUtil.height = RenderUtil.sr.getScaledHeight();

        if (mc.currentScreen == null) {
            ItemStack currentItem = mc.thePlayer.inventory.getCurrentItem();

            if (mc.thePlayer.inventory.getCurrentItem() != null && (currentItem.getItem() == SCObjects.wandcasting)) {
                renderCastingWandOverlay(mc, mc.thePlayer, currentItem);
            }

            for (int i = 0; i < mc.thePlayer.inventory.getHotbarSize(); i++) {
                ItemStack stack = mc.thePlayer.inventory.getStackInSlot(i);

                if (mc.thePlayer.inventory.hasItem(SCObjects.inviscloak.itemID)) {
                    if (ItemInvisCloak.getState(stack)) {
                        renderCloakInvisOverlay(mc, stack);
                    }
                }
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
                
                // TODO: Once I have an Abilities system, move this over to a new ability.
                this.renderAstralEnergyOverlay(mc, mc.thePlayer);
            }
        }
    }

    @Override
    public void onGUITick(Minecraft mc, GuiScreen guiscreen)
    {
    }

    private static void renderCastingWandOverlay(Minecraft minecraft, EntityPlayer player, ItemStack stack)
    {
        ItemWandCasting wand = (ItemWandCasting) stack.getItem();
        String icon = Reference.PATH_TEXTURES_GUI_HUD + "wand/wand_normal.png";
        int slot = player.inventory.currentItem;

        if (wand.getActiveBehaviour(stack) != 0) {
            icon = WandManager.getBehaviour(wand.getActiveBehaviour(stack)).icon;
        }

        RenderUtil.renderEngine.bindTexture(icon);

        float colourR = 1.0f;
        float colourG = 1.0f;
        float colourB = 1.0f;

        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            colourR = 0.8f;
            colourG = 0.8f;
            colourB = 0.8f;
        }
        
        float scale = 0.05f;

        if (Settings.WAND_CASTING_BEHAVIOUR_ICON_ALIGNMENT == 1) {
            RenderUtil.getInstance().drawTextureRect((RenderUtil.width / 2 - 16 - 90) * 20, (RenderUtil.height - 34 + 20) * 20, 0, 0, 256, 256, scale, scale,
                    scale, colourR, colourG, colourB);
        } else
            if (Settings.WAND_CASTING_BEHAVIOUR_ICON_ALIGNMENT == 2) {
                RenderUtil.getInstance().drawTextureRect((RenderUtil.width / 2 - 6) * 20, (RenderUtil.height - 74 + 20) * 20, 0, 0, 256, 256, scale, scale, scale,
                        colourR, colourG, colourB);
            } else
                if (Settings.WAND_CASTING_BEHAVIOUR_ICON_ALIGNMENT == 3) {
                    RenderUtil.getInstance().drawTextureRect((RenderUtil.width / 2 - 16 + 108) * 20, (RenderUtil.height - 34 + 20) * 20, 0, 0, 256, 256, scale,
                            scale, scale, colourR, colourG, colourB);
                } else {
                    if (slot >= 5) {
                        RenderUtil.getInstance().drawTextureRect((RenderUtil.width / 2 - 16 + 108) * 20, (RenderUtil.height - 34 + 20) * 20, 0, 0, 256, 256, scale,
                                scale, scale, colourR, colourG, colourB);
                    } else
                        if (slot <= 3) {
                            RenderUtil.getInstance().drawTextureRect((RenderUtil.width / 2 - 16 - 90) * 20, (RenderUtil.height - 34 + 20) * 20, 0, 0, 256, 256,
                                    scale, scale, scale, colourR, colourG, colourB);
                        } else {
                            RenderUtil.getInstance().drawTextureRect((RenderUtil.width / 2 - 6) * 20, (RenderUtil.height - 74 + 20) * 20, 0, 0, 256, 256, scale,
                                    scale, scale, colourR, colourG, colourB);
                        }
                }

        /*
         * if(!wand.getMode(stack).equals("")) {
         * if(player.inventory.hasItem(ItemWandCasting
         * .modeList.get(wand.getMode(stack)).reqItem(stack).itemID)) { for(int
         * i = 0; i < player.inventory.getHotbarSize(); i++) { ItemStack
         * itemStack = player.inventory.getStackInSlot(i);
         * 
         * if(itemStack != null) { if(itemStack.getItem() instanceof IWandMode)
         * { IWandMode wandMode = (IWandMode) itemStack.getItem();
         * 
         * if(wandMode.getModeName().equals(wand.getMode(stack))) {
         * RenderUtil.itemRenderer
         * .renderItemAndEffectIntoGUI(RenderUtil.fontRenderer,
         * RenderUtil.renderEngine, itemStack, RenderUtil.width / 2 - 8 + 100,
         * RenderUtil.height - 18);
         * RenderUtil.itemRenderer.renderItemOverlayIntoGUI
         * (RenderUtil.fontRenderer, RenderUtil.renderEngine, itemStack,
         * RenderUtil.width / 2 - 8 + 100, RenderUtil.height - 18); break; } } }
         * } } }
         */
    }

    private static void renderCloakInvisOverlay(Minecraft minecraft, ItemStack stack)
    {
        RenderUtil.getInstance().renderVignette(0.0f, Minecraft.getMinecraft().displayWidth / 2, Minecraft.getMinecraft().displayHeight / 2);
    }
    
    private static void renderAstralEnergyOverlay(Minecraft minecraft, EntityPlayer player)
    {
        int maxEnergy = 0;
        int currentEnergy = 0;
        int cellsInInv = 0;
        int fullCellsInInv = 0;
        
        for(int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if(stack != null)
            {
                if(stack.getItem() == SCObjects.astralenergycell)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack.getItem();
                    
                    maxEnergy = maxEnergy + 1000;
                    currentEnergy = currentEnergy + (1000 - cell.getEnergy(stack));
                    cellsInInv++;
                    
                    if(cell.getEnergy(stack) < 1000)
                    {
                        fullCellsInInv++;
                    }
                }
            }
        }
        
        RenderUtil.renderEngine.bindTexture(Reference.PATH_TEXTURES_GUI_HUD + "astral_energy_cell_hud.png");
        RenderUtil.getInstance().drawTextureRect(1, 1, 0, 0, 256, 256, 0.1f);
        
        minecraft.fontRenderer.drawString("\2477" + fullCellsInInv, 11, 10, 0xffffff);
        
        minecraft.fontRenderer.drawStringWithShadow("\2477" + currentEnergy + " / " + maxEnergy, 30, 2, 0xffffff);
    }
    
    private static void renderAstralGauntletOverlay(Minecraft minecraft, EntityPlayer player, ItemStack stack)
    {
        ItemAstralGauntlet gauntlet = (ItemAstralGauntlet) stack.getItem();
        
        if(player.inventory.hasItem(SCObjects.astralenergycell.itemID))
        {
            minecraft.fontRenderer.drawStringWithShadow("\2477" + AstralGauntletManager.getMode(gauntlet.getMode(stack)).name, 30, 2, 0xffffff);
        } else {
            minecraft.fontRenderer.drawStringWithShadow("\247c" + AstralGauntletManager.getMode(gauntlet.getMode(stack)).name, 30, 2, 0xffffff);
        }
        
        int maxEnergy = 0;
        int currentEnergy = 0;
        int cellsInInv = 0;
        int fullCellsInInv = 0;
        
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
                    
                    if(cell.getEnergy(stack2) < 1000)
                    {
                        fullCellsInInv++;
                    }
                }
            }
        }
        
        RenderUtil.renderEngine.bindTexture(Reference.PATH_TEXTURES_GUI_HUD + "astral_energy_cell_hud.png");
        RenderUtil.getInstance().drawTextureRect(1, 1, 0, 0, 256, 256, 0.1f);
        
        minecraft.fontRenderer.drawString("\2477" + fullCellsInInv, 11, 10, 0xffffff);
        
        minecraft.fontRenderer.drawStringWithShadow("\2477" + currentEnergy + " / " + maxEnergy, 30, 13, 0xffffff);
    }
}
