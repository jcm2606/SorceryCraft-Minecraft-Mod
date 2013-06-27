package jcm2606.mods.sorcerycraft.item.charm;


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
}
