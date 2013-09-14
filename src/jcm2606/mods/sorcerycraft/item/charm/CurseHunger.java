package jcm2606.mods.sorcerycraft.item.charm;

public class CurseHunger implements ICharmCurse
{
    @Override
    public String getCurseName()
    {
        return "hunger";
    }
    
    @Override
    public String getCurseNameLocal()
    {
        return "Hunger";
    }
    
    @Override
    public boolean isCursePositive()
    {
        return false;
    }
}
