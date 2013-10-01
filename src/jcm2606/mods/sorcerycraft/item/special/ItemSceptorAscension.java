package jcm2606.mods.sorcerycraft.item.special;

import java.util.ArrayList;
import java.util.List;

import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.api.compat.HandlerMethodID;
import jcm2606.mods.sorcerycraft.core.helper.SCHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemSceptorAscension extends SCItem
{
    public ItemSceptorAscension(int par1)
    {
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
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        double radius = 13;
        ArrayList nearbyEntities = (ArrayList) world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(x - radius, y - radius,
                z - radius, x + radius, y + radius, z + radius));
        
        double velUp = 1D;
        
        for (Object obj : nearbyEntities)
        {
            if (obj instanceof Entity)
            {
                Entity ent = (Entity) obj;
                
                if (ent != player)
                {
                    if (!(ent instanceof EntityPlayer))
                    {
                        CompatibilityContainer.postUpdate(HandlerMethodID.SCEPTOR_ASCENSION_USE, null);
                        ent.addVelocity(0.0D, velUp, 0.0D);
                    }
                }
            }
        }
        
        return true;
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        if (SCHelper.playerHasPerceptionMedallion(player))
        {
            list.add("\2478\247o" + (stack.getMaxDamage() - stack.getItemDamage()) + " / " + stack.getMaxDamage());
        }
    }
}
