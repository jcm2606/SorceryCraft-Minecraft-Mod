package jcm2606.mods.sorcerycraft.astral.ability.abilities;

import jcm2606.mods.sorcerycraft.api.IAbility;
import jcm2606.mods.sorcerycraft.core.handler.AlchemyHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class IndirectTransmutation implements IAbility
{
    @Override
    public String getName()
    {
        return "Indirect Transmutation";
    }
    
    @Override
    public boolean rightClickBlock(World world, EntityPlayer player, ItemStack stack, int x, int y, int z, int side)
    {
        return AlchemyHandler.performInWorldTransmutation(stack, player, world, x, y, z);
    }
    
    @Override
    public boolean rightClick(EntityPlayer player, ItemStack stack)
    {
        return false;
    }
    
    @Override
    public boolean itemUseTick(ItemStack stack, EntityPlayer player, int count)
    {
        return false;
    }
    
    @Override
    public boolean handleItemUseTick(ItemStack stack, EntityPlayer player)
    {
        return false;
    }
    
    @Override
    public EnumAction getUseAction(ItemStack stack)
    {
        return null;
    }
}
