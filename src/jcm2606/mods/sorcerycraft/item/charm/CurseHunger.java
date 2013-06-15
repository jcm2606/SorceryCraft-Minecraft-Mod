package jcm2606.mods.sorcerycraft.item.charm;

import jcm2606.mods.sorcerycraft.SCObjects;

public class CurseHunger implements ICharmCurse {
	@Override
	public String getCurseName() {
		return "hunger";
	}
	
	@Override
	public String getCurseNameLocal() {
		return "Hunger";
	}

	@Override
	public boolean isCursePositive() {
		return false;
	}

	@Override
	public Integer[] getValidIdList() {
		return new Integer[] {SCObjects.charmhealth.itemID};
	}
}
