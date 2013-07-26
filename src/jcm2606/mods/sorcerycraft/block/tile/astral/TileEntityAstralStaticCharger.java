package jcm2606.mods.sorcerycraft.block.tile.astral;

import jcm2606.mods.sorcerycraft.api.AstralManager;
import jcm2606.mods.sorcerycraft.block.tile.energy.TileEntityConsumer;
import net.minecraft.entity.player.EntityPlayer;

public class TileEntityAstralStaticCharger extends TileEntityConsumer {
    public void transferEnergyToPlayer(EntityPlayer player)
    {
        if(this.isPowered)
        {
            AstralManager.chargeCellsInInvFromBlocks(player.getCurrentEquippedItem(), player, player.worldObj);
        }
    }
}
