package jcm2606.mods.sorcerycraft.item.charm;

public class CurseBurn implements ICharmCurse
{
    @Override
    public String getCurseName()
    {
        return "burn";
    }
    
    @Override
    public String getCurseNameLocal()
    {
        return "Flash Burn";
    }
    
    @Override
    public boolean isCursePositive()
    {
        return false;
    }
}
