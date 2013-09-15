package jcm2606.mods.sorcerycraft.core.codechicken.nei;

import java.util.List;

import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.client.gui.GuiArcaneWorkbench;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.manager.CraftingManagerArcaneWorkbench;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import codechicken.nei.recipe.ShapedRecipeHandler;
import codechicken.nei.recipe.ShapelessRecipeHandler;

public class ArcaneWorkbenchHandler
{
    public static class ShapedRecipe extends ShapedRecipeHandler
    {
        @Override
        public Class<? extends GuiContainer> getGuiClass()
        {
            return GuiArcaneWorkbench.class;
        }
        
        @Override
        public String getRecipeName()
        {
            return "Arcane Workbench";
        }
        
        @Override
        public void loadCraftingRecipes(String outputId, Object... results)
        {
            if (outputId.equals("crafting") && (getClass() == ShapedRecipe.class))
            {
                List<IRecipe> allrecipes = CraftingManagerArcaneWorkbench.recipes;
                for (IRecipe irecipe : allrecipes)
                {
                    CachedShapedRecipe recipe = null;
                    if (irecipe instanceof ShapedRecipes)
                    {
                        recipe = new CachedShapedRecipe((ShapedRecipes) irecipe);
                    } else
                        if (irecipe instanceof ShapedOreRecipe)
                        {
                            recipe = forgeShapedRecipe((ShapedOreRecipe) irecipe);
                        }
                    
                    if (recipe == null)
                    {
                        continue;
                    }
                    
                    recipe.computeVisuals();
                    arecipes.add(recipe);
                }
            } else
            {
                super.loadCraftingRecipes(outputId, results);
            }
        }
        
        @Override
        public void loadCraftingRecipes(ItemStack result)
        {
            List<IRecipe> allrecipes = CraftingManagerArcaneWorkbench.recipes;
            for (IRecipe irecipe : allrecipes)
            {
                CachedShapedRecipe recipe = null;
                if (irecipe instanceof ShapedRecipes)
                {
                    recipe = new CachedShapedRecipe((ShapedRecipes) irecipe);
                } else
                    if (irecipe instanceof ShapedOreRecipe)
                    {
                        recipe = forgeShapedRecipe((ShapedOreRecipe) irecipe);
                    }
                
                if (recipe == null)
                {
                    continue;
                }
                
                recipe.computeVisuals();
                
                if (GeneralUtil.areStacksEqual(recipe.getResult().item, result, true))
                {
                    arecipes.add(recipe);
                }
            }
        }
        
        @Override
        public void loadUsageRecipes(ItemStack ingredient)
        {
            List<IRecipe> allrecipes = CraftingManagerArcaneWorkbench.recipes;
            for (IRecipe irecipe : allrecipes)
            {
                CachedShapedRecipe recipe = null;
                if (irecipe instanceof ShapedRecipes)
                {
                    recipe = new CachedShapedRecipe((ShapedRecipes) irecipe);
                } else
                    if (irecipe instanceof ShapedOreRecipe)
                    {
                        recipe = forgeShapedRecipe((ShapedOreRecipe) irecipe);
                    }
                
                if (recipe == null)
                {
                    continue;
                }
                
                recipe.computeVisuals();
                arecipes.add(recipe);
            }
        }
        
        @Override
        public String getGuiTexture()
        {
            return "SorceryCraft:" + Reference.PATH_TEXTURES_GUI + "arcane_workbench.png";
        }
    }
    
    public static class ShapelessRecipe extends ShapelessRecipeHandler
    {
        @Override
        public Class<? extends GuiContainer> getGuiClass()
        {
            return GuiArcaneWorkbench.class;
        }
        
        @Override
        public String getRecipeName()
        {
            return "Arcane Workbench Shapeless";
        }
        
        @Override
        public void loadCraftingRecipes(String outputId, Object... results)
        {
            if (outputId.equals("crafting") && (getClass() == ShapelessRecipe.class))
            {
                List<IRecipe> allrecipes = CraftingManagerArcaneWorkbench.recipes;
                for (IRecipe irecipe : allrecipes)
                {
                    CachedShapelessRecipe recipe = null;
                    if (irecipe instanceof ShapelessRecipes)
                    {
                        recipe = new CachedShapelessRecipe((ShapelessRecipes) irecipe);
                    } else
                        if (irecipe instanceof ShapelessOreRecipe)
                        {
                            recipe = forgeShapelessRecipe((ShapelessOreRecipe) irecipe);
                        }
                    
                    if (recipe == null)
                    {
                        continue;
                    }
                    
                    arecipes.add(recipe);
                }
            } else
            {
                super.loadCraftingRecipes(outputId, results);
            }
        }
        
        @Override
        public void loadCraftingRecipes(ItemStack result)
        {
            List<IRecipe> allrecipes = CraftingManagerArcaneWorkbench.recipes;
            for (IRecipe irecipe : allrecipes)
            {
                CachedShapelessRecipe recipe = null;
                if (irecipe instanceof ShapelessRecipes)
                {
                    recipe = new CachedShapelessRecipe((ShapelessRecipes) irecipe);
                } else
                    if (irecipe instanceof ShapelessOreRecipe)
                    {
                        recipe = forgeShapelessRecipe((ShapelessOreRecipe) irecipe);
                    }
                
                if (recipe == null)
                {
                    continue;
                }
                
                if (GeneralUtil.areStacksEqual(recipe.getResult().item, result, true))
                {
                    arecipes.add(recipe);
                }
            }
        }
        
        @Override
        public void loadUsageRecipes(ItemStack ingredient)
        {
            List<IRecipe> allrecipes = CraftingManagerArcaneWorkbench.recipes;
            for (IRecipe irecipe : allrecipes)
            {
                CachedShapelessRecipe recipe = null;
                if (irecipe instanceof ShapelessRecipes)
                {
                    recipe = new CachedShapelessRecipe((ShapelessRecipes) irecipe);
                } else
                    if (irecipe instanceof ShapelessOreRecipe)
                    {
                        recipe = forgeShapelessRecipe((ShapelessOreRecipe) irecipe);
                    }
                
                if (recipe == null)
                {
                    continue;
                }
                
                arecipes.add(recipe);
            }
        }
        
        @Override
        public String getGuiTexture()
        {
            return "SorceryCraft:" + Reference.PATH_TEXTURES_GUI + "arcane_workbench.png";
        }
    }
}
