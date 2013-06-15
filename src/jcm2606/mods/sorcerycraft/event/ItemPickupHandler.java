package jcm2606.mods.sorcerycraft.event;

import jcm2606.mods.sorcerycraft.SCAchievements;
import jcm2606.mods.sorcerycraft.SCObjects;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class ItemPickupHandler {
	@ForgeSubscribe
	public void onItemPickup(EntityItemPickupEvent event) {
		EntityItem item = event.item;
		int id = item.getEntityItem().itemID;

		if (id == SCObjects.dustvordic.itemID) {
			event.entityPlayer.addStat(SCAchievements.vordicDustGet, 1);
		}
	}
}
