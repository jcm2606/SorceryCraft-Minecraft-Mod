package jcm2606.mods.sorcerycraft.keybind;

import java.util.ArrayList;
import java.util.EnumSet;

import jcm2606.mods.jccore.keybind.KeyBind;
import jcm2606.mods.sorcerycraft.api.IKeyBound;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketKeyPress;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientKeyBindingHandler extends KeyBindingRegistry.KeyHandler {
	
	public static ArrayList<KeyBind> keyBindList = new ArrayList<KeyBind>();
	public static ArrayList<Boolean> repeatingList = new ArrayList<Boolean>();
	
    public ClientKeyBindingHandler() {
        super(gatherKeyBindings(), gatherIsRepeating());
    }
    
    public static KeyBinding[] gatherKeyBindings() {
        return keyBindList.toArray(new KeyBinding[keyBindList.size()]);
    }

    public static boolean[] gatherIsRepeating() {

        boolean[] isRepeating = new boolean[repeatingList.size()];

        for (int x = 0; x < isRepeating.length; x++) {
            isRepeating[x] = repeatingList.get(x).booleanValue();
        }

        return isRepeating;
    }
    
    public static void addKeyBinding(String name, int key, boolean repeat)
    {
        keyBindList.add(new KeyBind(name, key));
        repeatingList.add(repeat);
    }
    
    public static void addKeyBinding(KeyBind keyBind, boolean repeat)
    {
        keyBindList.add(keyBind);
        repeatingList.add(repeat);
    }
    
    public static boolean isKeyBindClientSided(String name)
    {
        for(KeyBind keyBind : keyBindList)
        {
            if(keyBind.keyDescription.equals(name))
            {
                return keyBind.isClientSided;
            }
        }
        
        return false;
    }

    @Override
    public String getLabel() {
        return SorceryCraft.class.getAnnotation(Mod.class).modid() + ": " + this.getClass().getSimpleName();
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
        if (tickEnd) {
            if (FMLClientHandler.instance().getClient().inGameHasFocus) {
                EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
                if (player != null) {
                    
                    if(kb.keyDescription.equals(Reference.KEY_BIND_INHAND_ITEM_DESC))
                    {
                        ItemStack currentItem = FMLClientHandler.instance().getClient().thePlayer.getCurrentEquippedItem();

                        if(currentItem != null)
                        {
                            if(this.isKeyBindClientSided(kb.keyDescription))
                            {
                                PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketKeyPress(kb.keyDescription), PacketHandler.CHANNEL_SC));
                            } else {
                                ((IKeyBound) currentItem.getItem()).doKeyBindingAction(player, currentItem, kb.keyDescription);
                            }
                        }
                    }
                    
                    if(kb.keyDescription.equals(Reference.KEY_BIND_HOTBAR_ITEM_DESC))
                    {
                        for(int i = 0; i < player.inventory.getHotbarSize(); i++)
                        {
                            ItemStack stack = player.inventory.getStackInSlot(i);
                            ItemStack currentItem = FMLClientHandler.instance().getClient().thePlayer.getCurrentEquippedItem();
                            
                            if(currentItem != null)
                            {
                                if(currentItem == stack)
                                {
                                    return;
                                }
                            }
                            
                            if(stack != null)
                            {
                                if(this.isKeyBindClientSided(kb.keyDescription))
                                {
                                    PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketKeyPress(kb.keyDescription), PacketHandler.CHANNEL_SC));
                                } else {
                                    ((IKeyBound) stack.getItem()).doKeyBindingAction(player, stack, kb.keyDescription);
                                }
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
