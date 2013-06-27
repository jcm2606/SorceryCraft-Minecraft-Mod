package jcm2606.mods.sorcerycraft.item.special;

import java.util.ArrayList;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.compat.CompatContainerSC;
import jcm2606.mods.sorcerycraft.compat.HandlerMethodID;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemSceptorAscension extends SCItem {
    public ItemSceptorAscension(int par1) {
        super(par1, "sceptorAscension");
        this.setMaxDamage(420);
        this.setMaxStackSize(1);
        this.setNoRepair();
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
    }
    
    /**
     * Callback for item usage. If the item does something special on right
     * clicking, he will have one of those. Return True if something happen and
     * false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        double radius = 13;
        ArrayList nearbyEntities = (ArrayList) world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius));

        double velUp = 1D;

        for (Object obj : nearbyEntities) {
            if(obj instanceof Entity)
            {
                Entity ent = (Entity) obj;
                
                if (ent != player) {
                    if (!(ent instanceof EntityPlayer)) {
                        CompatContainerSC.postUpdateToSubContainers(HandlerMethodID.SCEPTOR_ASCENSION_USE, null);
                        ent.addVelocity(0.0D, velUp, 0.0D);
                    }
                  }
            }
        }
        
        return true;
    }
}
