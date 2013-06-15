package jcm2606.mods.sorcerycraft.item.charm;

import jcm2606.mods.sorcerycraft.SCObjects;

public class CurseBurn implements ICharmCurse {
	@Override
	public String getCurseName() {
		return "burn";
	}
	
	@Override
	public String getCurseNameLocal() {
		return "Flash Burn";
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
