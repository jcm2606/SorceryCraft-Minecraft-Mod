package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.helper.SCHelper;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

public class ItemAstralMechanismDrive extends SCItem {
    public ItemAstralMechanismDrive(int par1) {
        super(par1, "astralMechanismDrive");
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return false;
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
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
                list.add("A mechanical drive which seems");
                list.add("to run entirely off of the energy");
                list.add("of the crystals used to construct");
                list.add("the drive. The energy seems to keep");
                list.add("the cogs and gears spinning constantly,");
                list.add("which makes the drive an excellent");
                list.add("construct for any mechanical devices");
                list.add("needing non-stop clockwork. Keep in mind,");
                list.add("the crystals do NOT serve as infinite energy");
                list.add("reserves. By itself, the drive will continue");
                list.add("to spin, when used in a device, the drive will");
                list.add("require energy to actually spin up and start,");
                list.add("and even more to keep it running.");
            } else {
                list.add("<Hold SHIFT>");
            }
        }
    }
}
