package jcm2606.mods.sorcerycraft.keybind;

import jcm2606.mods.jccore.keybind.KeyBind;

public class KeyBindingSC extends KeyBind {
    public KeyBindingSC(String name, int key) {
        super(name, key);
        this.isClientSided = true;
    }
}
