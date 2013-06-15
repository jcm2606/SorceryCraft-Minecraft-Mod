package jcm2606.mods.sorcerycraft.item.tool;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.item.SCSword;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSwordEndowment extends SCSword {
    public ItemSwordEndowment(int par1) {
        super(par1, SCObjects.SWORD_ENDOWMENT, "toolSwordPower");
        this.setNoRepair();
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.LEGENDARY);
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4)
    {
    }
}
