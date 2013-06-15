package jcm2606.mods.sorcerycraft.item.charm;

public class ItemCharmSight extends ItemCharm {
	public ItemCharmSight(int par1) {
		super(par1, "charmSight");
		this.setMaxDamage(120);
		this.displayInfo = false;
		this.applyCursesOnTick = true;
	}
}
