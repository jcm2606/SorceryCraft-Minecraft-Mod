package jcm2606.mods.sorcerycraft.damagesource;

import jcm2606.mods.jccore.DamageSourceJC;

public class DamageSourceEntityDetector extends DamageSourceJC {
	public DamageSourceEntityDetector(String name) {
		super(name);
		this.setDeathMessage(this.PLAYER_NAME_CHARACTER + " was killed by a strong force of energy.");
		this.setDamageBypassesArmor();
		this.setMagicDamage();
	}
}
