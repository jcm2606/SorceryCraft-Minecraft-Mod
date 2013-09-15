package jcm2606.mods.sorcerycraft.client.keybind;

import jcm2606.mods.jccore.client.keybind.KeyBind;

public class KeyBindingSC extends KeyBind
{
    public KeyBindingSC(String name, int key)
    {
        super(name, key);
        this.isClientSided = true;
    }
}
