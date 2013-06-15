package jcm2606.mods.sorcerycraft.item.charm;

import jcm2606.mods.sorcerycraft.SCObjects;

public class CurseBlindness implements ICharmCurse {

	@Override
	public String getCurseName() {
		return "blindness";
	}

	@Override
	public String getCurseNameLocal() {
		return "Blind Eye";
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