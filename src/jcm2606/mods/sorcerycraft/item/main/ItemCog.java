package jcm2606.mods.sorcerycraft.item.main;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.jccore.item.ItemMeta;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import jcm2606.mods.sorcerycraft.core.helper.SCHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

public class ItemCog extends ItemMeta {
    public static String[] names = {
        "sc_cogStone",
        "sc_cogMetal",
        "sc_cogAstral",
        "sc_cogArcane"
    };
    
    public static String[] localisedNames = {
        "Stone",
        "Metal",
        "Astral",
        "Arcane"
    };
    
    public static String[] textures = {
        "SorceryCraft:cogStone",
        "SorceryCraft:cogMetal",
        "SorceryCraft:cogAstral",
        "SorceryCraft:cogArcane"
    };
    
    public ItemCog(int id) {
        super(id, names, textures);
        this.setCreativeTab(SorceryCraft.tabItems);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        if(stack.getItemDamage() == 2 || stack.getItemDamage() == 3)
        {
            return RarityHelper.getCustomRarityType(Rarities.BASIC);
        }
        
        return EnumRarity.common;
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        if(stack.getItemDamage() == 2 || stack.getItemDamage() == 3)
        {
            return true;
        }
        
        return false;
    }
    
    @Override
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4) {
        if(SCHelper.playerHasPerceptionMedallion(player))
        {
            if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode))
            {
                if(stack.getItemDamage() == 0)
                {
                    list.add("A basic mechanical gear made of Stone.");
                }
                
                if(stack.getItemDamage() == 1)
                {
                    list.add("A basic mechanical gear made of Metal.");
                }
                
                if(stack.getItemDamage() == 2)
                {
                    list.add("An advanced mechanical gear made of Astral Crystal.");
                }
                
                if(stack.getItemDamage() == 3)
                {
                    list.add("An advanced mechanical gear made of Arcane Steel.");
                }
            } else {
                list.add("<Hold SHIFT>");
            }
        }
    }
}
