package jcm2606.mods.sorcerycraft.helper;

import jcm2606.mods.sorcerycraft.SCObjects;
import net.minecraft.entity.player.EntityPlayer;

public class SCHelper {
    public static boolean playerHasPerceptionMedallion(EntityPlayer player)
    {
        return player.inventory.hasItem(SCObjects.medallionperception.itemID);
    }
}
