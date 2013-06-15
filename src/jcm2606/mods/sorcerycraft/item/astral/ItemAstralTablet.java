package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.jccore.helper.NBTHelper;
import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.AstralGauntletManager;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode.GauntletMode;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAstralTablet extends SCItemShine {
    public final String NBT_TAG_MODE = "mode";
    
    public ItemAstralTablet(int par1) {
        super(par1, "astralTablet");
        this.setMaxStackSize(1);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
    }
    
    public void setMode(ItemStack stack, int mode)
    {
        NBTHelper.setInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_TAG_MODE, mode);
    }
    
    public int getMode(ItemStack stack)
    {
        return NBTHelper.getInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_TAG_MODE);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4) {
        GauntletMode mode = AstralGauntletManager.getMode(getMode(stack));
        
        if(mode != null)
        {
            list.add(mode.name);
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(int itemID, CreativeTabs tab, List list) {
        for(int i = 0; i < AstralGauntletManager.modeList.length; i++)
        {
            if(AstralGauntletManager.modeList[i] != null)
            {
                ItemStack stack = new ItemStack(SCObjects.astraltablet, 1);
                setMode(stack, i);
                
                list.add(stack);
            }
        }
    }
}
