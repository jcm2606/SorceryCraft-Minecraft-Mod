package jcm2606.mods.sorcerycraft.item.special;

import jcm2606.mods.sorcerycraft.item.SCItemShine;

public class ItemElemental extends SCItemShine {
    public ItemElemental(int par1, String par2) {
        super(par1, par2);
        this.setMaxStackSize(1);
        this.setMaxDamage(240);
        this.setNoRepair();
    }
}
