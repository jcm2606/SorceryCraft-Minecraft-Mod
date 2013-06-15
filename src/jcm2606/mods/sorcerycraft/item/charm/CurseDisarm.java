package jcm2606.mods.sorcerycraft.item.charm;

import jcm2606.mods.sorcerycraft.SCObjects;

public class CurseDisarm implements ICharmCurse {
	@Override
	public String getCurseName() {
		return "disarm";
	}

	@Override
	public String getCurseNameLocal() {
		return "Disarmament";
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
