package jcm2606.mods.sorcerycraft;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

public class SCPlayerTracker implements IPlayerTracker {
    @Override
    public void onPlayerLogin(EntityPlayer player)
    {
//        player.addStat(SCAchievements.modUseFirst, 1);
    }

    @Override
    public void onPlayerLogout(EntityPlayer player)
    {}

    @Override
    public void onPlayerChangedDimension(EntityPlayer player)
    {}

    @Override
    public void onPlayerRespawn(EntityPlayer player)
    {}
}
