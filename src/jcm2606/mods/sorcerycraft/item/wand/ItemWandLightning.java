package jcm2606.mods.sorcerycraft.item.wand;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.jccore.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.compat.CompatContainerSC;
import jcm2606.mods.sorcerycraft.api.compat.HandlerMethodID;
import jcm2606.mods.sorcerycraft.core.helper.SCHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketWandUse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;

public class ItemWandLightning extends ItemWandBase
{
    public ItemWandLightning(int par1)
    {
        super(par1, "wandLightning");
        this.setMaxStackSize(1);
        this.setMaxDamage(512);
        this.setNoRepair();
    }
    
    @Override
    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }
    
    /**
     * Called each tick as long the item is on a player inventory. Uses by maps
     * to check if is on a player hand and update it's contents.
     */
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean currentItem)
    {
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        player.setItemInUse(stack, 20);
        
        return stack;
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        if (SCHelper.playerHasPerceptionMedallion(player))
        {
            list.add("\2478\247o" + (stack.getMaxDamage() - stack.getItemDamage()) + " / " + stack.getMaxDamage());
        }
    }
    
    @Override
    public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count)
    {
        if (count < 0)
        {
            return;
        }
        
        PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketWandUse(), PacketHandler.CHANNEL_SC));
        
        MovingObjectPosition mop = GeneralUtil.getTargetBlock(player.worldObj, player, false, 64.0f);
        
        if (mop == null)
        {
            return;
        }
        
        Vec3 vec3 = mop.hitVec;
        
        double x = vec3.xCoord;
        double y = vec3.yCoord;
        double z = vec3.zCoord;
        
        EntityLightningBolt bolt = new EntityLightningBolt(player.worldObj, x, y, z);
        
        player.worldObj.spawnEntityInWorld(bolt);
        stack.damageItem(1, player);
        CompatContainerSC.postUpdate(HandlerMethodID.SCEPTOR_LIGHTNING_USE, null);
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.bow;
    }
    
    @Override
    public void onWandUse(ItemStack stack, EntityPlayer player)
    {
        MovingObjectPosition mop = GeneralUtil.getTargetBlock(player.worldObj, player, false, 64.0f);
        
        if (mop == null)
        {
            return;
        }
        
        Vec3 vec3 = mop.hitVec;
        
        double x = vec3.xCoord;
        double y = vec3.yCoord;
        double z = vec3.zCoord;
        
        EntityLightningBolt bolt = new EntityLightningBolt(player.worldObj, x, y, z);
        
        player.worldObj.spawnEntityInWorld(bolt);
        stack.damageItem(1, player);
        CompatContainerSC.postUpdate(HandlerMethodID.SCEPTOR_LIGHTNING_USE, null);
    }
}
