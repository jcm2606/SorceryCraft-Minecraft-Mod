package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.astral.AstralManager;
import jcm2606.mods.sorcerycraft.helper.SCHelper;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

public class ItemAstralEnergyPearl extends SCItemShine {
    public ItemAstralEnergyPearl(int par1) {
        super(par1, "astralEnergyPearl");
        this.setMaxStackSize(1);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isCurrentItem) {
        if(slot <= 8)
        {
            if(entity instanceof EntityLiving)
            {
                EntityLiving living = (EntityLiving) entity;
                
                if(living instanceof EntityPlayer)
                {
                    EntityPlayer player = (EntityPlayer) living;
                    
                    AstralManager.chargeCellsInInv(stack, player, world, 11, 8, 11);
                }
            }
        }
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
                list.add("Somehow draws in and gathers");
                list.add("Astral Energy from surrounding");
                list.add("sources in an 11x8x11 radius");
                list.add("around the player.");
            } else {
                list.add("<Hold SHIFT>");
            }
        }
    }
}