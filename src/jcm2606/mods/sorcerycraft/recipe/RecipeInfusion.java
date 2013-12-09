package jcm2606.mods.sorcerycraft.recipe;

import net.minecraft.item.ItemStack;

public class RecipeInfusion
{
    private final String name;
    private final ItemStack result;
    private final ItemStack[] components;
    
    public RecipeInfusion(ItemStack result, ItemStack[] components)
    {
        this.result = result;
        this.name = result.getDisplayName();
        this.components = components;
    }
    
    public ItemStack getResult()
    {
        return this.result;
    }
    
    public ItemStack[] getComponents()
    {
        return this.components;
    }
    
    public boolean componentsMatch(Object[] materials)
    {
        int matching = this.components.length;
        
        for (ItemStack stack1 : this.components)
        {
            for (Object obj : materials)
            {
                ItemStack stack2 = (ItemStack) obj;
                
                if (stack2.itemID == stack1.itemID && (stack2.getItemDamage() == stack1.getItemDamage() || stack1.getItemDamage() == -1))
                {
                    matching--;
                }
            }
        }
        
        return matching == 0;
    }
    
    public ItemStack getResult(Object materials[])
    {
        return this.componentsMatch(materials) ? this.result : null;
    }
    
    public boolean isComponent(ItemStack stack)
    {
        for (ItemStack stack2 : this.components)
        {
            if (stack.itemID == stack2.itemID && (stack.getItemDamage() == stack2.getItemDamage() || stack.getItemDamage() == -1))
            {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public String toString()
    {
        return "RecipeInfusion:" + this.name;
    }
}
