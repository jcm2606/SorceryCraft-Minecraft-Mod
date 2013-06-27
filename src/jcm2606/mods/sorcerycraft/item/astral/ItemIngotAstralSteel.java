package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.helper.SCHelper;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

public class ItemIngotAstralSteel extends SCItemShine {
    public ItemIngotAstralSteel(int par1) {
        super(par1, "ingotAstralSteel");
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
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
                list.add("An exceptionally magical and strong");
                list.add("metallic alloy that is capable of");
                list.add("withstanding the immense energies that");
                list.add("the Astral dimension seems to be home to.");
                list.add("Is favoured for use with most, if not all,");
                list.add("Astral constructs.");
            } else {
                list.add("<Hold SHIFT>");
            }
        }
    }
}
