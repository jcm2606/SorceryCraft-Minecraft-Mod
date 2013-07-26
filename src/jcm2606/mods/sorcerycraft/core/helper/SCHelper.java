package jcm2606.mods.sorcerycraft.core.helper;

import jcm2606.mods.sorcerycraft.core.SCObjects;
import net.minecraft.entity.player.EntityPlayer;

public class SCHelper {
    public static boolean playerHasPerceptionMedallion(EntityPlayer player)
    {
        return player.inventory.hasItem(SCObjects.medallionperception.itemID);
    }
}
