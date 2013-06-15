package jcm2606.mods.sorcerycraft.keybind;

import java.util.EnumSet;

import jcm2606.mods.sorcerycraft.SorceryCraft;
import jcm2606.mods.sorcerycraft.item.IKeyBound;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ClientKeyBindingHandler extends KeyBindingRegistry.KeyHandler {
	
	//TODO: Fix the f***ing Invisibility Cloak bug.
	
//	static KeyBinding cloakInvisToggle = new KeyBinding("Invisibility Cloak Toggle", Keyboard.KEY_F);
	
	static KeyBinding[] keyList = new KeyBinding[] {
//		cloakInvisToggle
	};
	
	static boolean[] repeatList = new boolean[] {
//		false
	};

    public ClientKeyBindingHandler() {
        super(keyList, repeatList);
    }

    @Override
    public String getLabel() {
        return SorceryCraft.class.getAnnotation(Mod.class).modid() + ": " + this.getClass().getSimpleName();
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {

        // Only operate at the end of the tick
        if (tickEnd) {
            // If we are not in a GUI of any kind, continue execution
            if (FMLClientHandler.instance().getClient().inGameHasFocus) {
                EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
                if (player != null) {
                    ItemStack currentItem = FMLClientHandler.instance().getClient().thePlayer.getCurrentEquippedItem();

                    if (currentItem != null) {
                    	if(currentItem.getItem() instanceof IKeyBound)
                		{
                    		((IKeyBound) currentItem.getItem()).doKeyBindingAction(player, currentItem, kb.keyDescription);
                		}
                	}
                    
                    for(int i = 0; i < player.inventory.getHotbarSize(); i++)
                    {
                    	ItemStack hotBarItem = player.inventory.mainInventory[i];
                    	
                    	if(hotBarItem != null && currentItem != hotBarItem)
                    	{
                    		if(hotBarItem.getItem() instanceof IKeyBound)
                    		{
                    			((IKeyBound) hotBarItem.getItem()).doKeyBindingAction(player, hotBarItem, kb.keyDescription);
                    		}
                    	}
                    }
                }
            }
        }
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {

    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT);
    }

    private static String getLocalizedKey(String key) {

        return LanguageRegistry.instance().getStringLocalization(key);
    }
}
