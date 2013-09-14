package jcm2606.mods.sorcerycraft.item.wand;

import java.util.List;

import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketWandUse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;

public class ItemWandAir extends ItemWandBase
{
    public ItemWandAir(int par1)
    {
        super(par1, "wandAir");
    }
    
    @Override
    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            player.inventoryContainer.detectAndSendChanges();
        }
        
        player.setItemInUse(stack, 72000);
        
        return stack;
    }
    
    private void getTargets(ItemStack itemstack, World world, Vec3 tvec, EntityPlayer player, double range)
    {
        Entity pointedEntity = null;
        Vec3 vec3d = Vec3.fakePool.getVecFromPool(player.posX, player.posY, player.posZ);
        Vec3 vec3d2 = vec3d.addVector(tvec.xCoord * range, tvec.yCoord * range, tvec.zCoord * range);
        float f1 = 1.0F;
        List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(tvec.xCoord * range, tvec.yCoord * range,
                tvec.zCoord * range).expand(f1, f1, f1));
        
        double d2 = 0.0D;
        for (int i = 0; i < list.size(); i++)
        {
            Entity entity = (Entity) list.get(i);
            if (entity.canBeCollidedWith())
            {
                float f2 = Math.max(1.0F, entity.getCollisionBorderSize());
                AxisAlignedBB axisalignedbb = entity.boundingBox.expand(f2, f2 * 1.25F, f2);
                MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3d, vec3d2);
                
                if (movingobjectposition != null)
                {
                    pointedEntity = entity;
                    
                    if ((player.canEntityBeSeen(pointedEntity)))
                    {
                        double d0 = player.posX - pointedEntity.posX;
                        double d1;
                        
                        for (d1 = player.posZ - pointedEntity.posZ; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D)
                        {
                            d0 = (Math.random() - Math.random()) * 0.01D;
                        }
                        
                        this.knockBack(pointedEntity, d0, d1);
                    }
                }
            }
        }
    }
    
    public void knockBack(Entity attacked, double par3, double par5)
    {
        attacked.isAirBorne = true;
        float f1 = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
        float f2 = 0.4F;
        attacked.motionX /= 2.0D;
        attacked.motionY /= 2.0D;
        attacked.motionZ /= 2.0D;
        attacked.motionX -= par3 / f1 * f2;
        attacked.motionZ -= par5 / f1 * f2;
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.bow;
    }
    
    @Override
    public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count)
    {
        int range = 5;
        Vec3 vec3d = player.getLook(range);
        Vec3 lookVec = player.getLookVec().normalize();
        
        getTargets(stack, player.worldObj, vec3d, player, range);
        
        for (int i = 0; i < 2; i++)
        {
            double adjAngle = 50.0D;
            double dist = 0.3D;
            
            EntityPlayer center = player;
            
            double px = center.posX - Math.cos((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
            double py = center.posY - Math.sin(center.rotationPitch / 540.0F * Math.PI) * dist;
            double pz = center.posZ + Math.sin((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
            
            float speed = 0.5F;
            float look = -2.0F;
            
            int divideAmount = 4;
            
            EntityFX fx = new EntitySmokeFX(player.worldObj, px, py, pz, 0, 0, 0, 0.5f);
            fx.motionX = (lookVec.xCoord + (player.worldObj.rand.nextGaussian() / divideAmount)) * speed;
            fx.motionY = (lookVec.yCoord + (player.worldObj.rand.nextGaussian() / divideAmount)) * speed;
            fx.motionZ = (lookVec.zCoord + (player.worldObj.rand.nextGaussian() / divideAmount)) * speed;
            
            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
        }
        
        PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketWandUse(), PacketHandler.CHANNEL_SC));
    }
    
    @Override
    public void onWandUse(ItemStack stack, EntityPlayer player)
    {
        this.chargeCount++;
        
        int charges = getMaxItemUseDuration(stack) - chargeCount;
        if (charges > stack.getMaxDamage() - stack.getItemDamage() - 1)
        {
            player.stopUsingItem();
        }
        
        int range = 5;
        Vec3 vec3d = player.getLook(range);
        
        getTargets(stack, player.worldObj, vec3d, player, range);
    }
    
    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int count)
    {
        this.chargeCount = 0;
        int charges = getMaxItemUseDuration(stack) - count;
        if (charges > stack.getMaxDamage() - stack.getItemDamage())
        {
            charges = stack.getMaxDamage() - stack.getItemDamage() + 1;
        }
        
        stack.damageItem(charges, player);
        player.inventoryContainer.detectAndSendChanges();
    }
}
