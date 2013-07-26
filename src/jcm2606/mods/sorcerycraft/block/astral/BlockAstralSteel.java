package jcm2606.mods.sorcerycraft.block.astral;

import java.util.List;

import jcm2606.mods.sorcerycraft.block.SCBlock;
import jcm2606.mods.sorcerycraft.core.helper.SCHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

public class BlockAstralSteel extends SCBlock {
    public BlockAstralSteel(int par1) {
        super(par1, Material.iron, "astralSteelBlock", Rarities.BASIC);
        this.setHardness(4.0f);
        this.setResistance(32.0f);
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
                list.add("A solid block made up of Astral");
                list.add("Steel ingots. The incredible");
                list.add("sturdiness of the ingots allows this");
                list.add("block to withstand a decent amount of");
                list.add("damage.");
            } else {
                list.add("<Hold SHIFT>");
            }
        }
    }
}
