package jcm2606.mods.sorcerycraft.manager;

import java.util.ArrayList;

import jcm2606.mods.sorcerycraft.recipe.RecipeInfusion;
import net.minecraft.item.ItemStack;

public class CraftingManagerAstralInfuser
{
    /** The static instance of this class */
    private static CraftingManagerAstralInfuser instance = new CraftingManagerAstralInfuser();
    
    public ArrayList<RecipeInfusion> recipes;
    
    /**
     * Returns the static instance of this class
     */
    public static CraftingManagerAstralInfuser getInstance()
    {
        return instance;
    }
    
    private CraftingManagerAstralInfuser()
    {
        recipes = new ArrayList<RecipeInfusion>();
    }
    
    public void addInfusion(ItemStack result, ItemStack[] materials)
    {
        recipes.add(new RecipeInfusion(result, materials));
    }
    
    public ItemStack getResult(Object[] materials)
    {
        ItemStack result = null;
        
        int index = -1;
        
        for (int i = 0; i < recipes.size(); i++)
        {
            RecipeInfusion recipe = recipes.get(i);
            
            if (recipe.getResult(materials) != null)
            {
                index = i;
            }
        }
        
        if (index >= 0)
        {
            RecipeInfusion recipe = recipes.get(index);
            
            result = recipe.getResult();
        }
        
        return result;
    }
    
    public ItemStack[] getComponentsFor(ItemStack stack)
    {
        ItemStack[] components = null;
        
        for (int i = 0; i < recipes.size(); i++)
        {
            RecipeInfusion recipe = recipes.get(i);
            
            components = recipe.getComponents();
        }
        
        return components;
    }
}
