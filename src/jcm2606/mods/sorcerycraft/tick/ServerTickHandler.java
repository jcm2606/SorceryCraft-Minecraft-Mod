package jcm2606.mods.sorcerycraft.tick;

import jcm2606.mods.jccore.tick.TickHandlerServerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ServerTickHandler extends TickHandlerServerBase
{
    @Override
    public void onServerWorldLoad(World world)
    {
    }
    
    @Override
    public void onServerWorldTick(World world)
    {
    }
    
    @Override
    public void onServerPlayerTick(EntityPlayer player, World world)
    {
        //        AstralAbilityBase.unlockBaseAbilities(player);
    }
    
    @Override
    public void onServerTick()
    {
    }
}
