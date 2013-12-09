package jcm2606.mods.sorcerycraft.api.research;

import net.minecraft.item.ItemStack;

public class ResearchBase
{
    private final String name;
    private final String localisedName;
    private final int researchPointCost;
    private final ItemStack stack;
    
    public ResearchBase(String name, String localisedName, int researchPointCost, ItemStack stack)
    {
        this.name = name;
        this.localisedName = localisedName;
        this.researchPointCost = researchPointCost;
        this.stack = stack;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getLocalisedName()
    {
        return this.localisedName;
    }
    
    public int getCost()
    {
        return this.researchPointCost;
    }
    
    public ItemStack getStack()
    {
        return this.stack;
    }
}
