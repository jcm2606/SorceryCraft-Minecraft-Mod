package jcm2606.mods.sorcerycraft.astral.ability.abilities;

import jcm2606.mods.sorcerycraft.api.IAbility;
import jcm2606.mods.sorcerycraft.astral.AstralManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ReverseGravity implements IAbility
{
    @Override
    public String getName()
    {
        return "Reverse Gravity";
    }
    
    @Override
    public boolean rightClickBlock(World world, EntityPlayer player, ItemStack stack, int x, int y, int z, int side)
    {
        return false;
    }
    
    @Override
    public boolean rightClick(EntityPlayer player, ItemStack stack)
    {
        return false;
    }
    
    @Override
    public boolean itemUseTick(ItemStack stack, EntityPlayer player, int count)
    {
        if(player.motionY < 1.0f)
        {
            player.motionY += 0.1f;
        }
        
        AstralManager.takeEnergyFrom(player, 1);
        
        return false;
    }
    
    @Override
    public boolean handleItemUseTick(ItemStack stack, EntityPlayer player)
    {
        return true;
    }
    
    @Override
    public EnumAction getUseAction(ItemStack stack)
    {
        return EnumAction.block;
    }
}
