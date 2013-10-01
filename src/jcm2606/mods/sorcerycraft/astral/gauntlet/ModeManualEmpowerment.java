package jcm2606.mods.sorcerycraft.astral.gauntlet;

import java.util.List;

import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.AstralManager;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.EnumUseType;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.GauntletMode;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketDrawAstralEnergyBeam;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;

public class ModeManualEmpowerment extends GauntletMode
{
    public ModeManualEmpowerment()
    {
        super(AstralManager.getNextAvailableId(), "Empowerment Beam", "");
        this.useAction = EnumAction.bow;
        this.useActionDuration = 72000;
        this.hasItemUse = true;
    }
    
    @Override
    public boolean onUse(EnumUseType type, ItemStack stack, World world, EntityPlayer player, EntityLivingBase living, int x, int y, int z, int side)
    {
        if (type == EnumUseType.ITEM_USE)
        {
            MovingObjectPosition mop = GeneralUtil.getTargetBlock(world, player, false, 64.0f);
            
            if (mop == null)
            {
                return false;
            }
            
            Vec3 vec3 = mop.hitVec;
            
            double x2 = vec3.xCoord;
            double y2 = vec3.yCoord;
            double z2 = vec3.zCoord;
            
            double adjAngle = 25.0D;
            double dist = 0.4D;
            
            EntityPlayer center = player;
            
            double posX = center.posX - Math.cos((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
            double posY = center.posY - Math.sin(center.rotationPitch / 540.0F * Math.PI) * dist;
            double posZ = center.posZ + Math.sin((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
            
            if (world.isRemote)
            {
                PacketDispatcher.sendPacketToAllAround(player.posX, player.posY, player.posZ, 64, player.dimension, PacketType.populatePacket(
                        new PacketDrawAstralEnergyBeam(posX, posY, posZ, x2, y2, z2, 1, true), PacketHandler.CHANNEL_SC));
            }
            
            if (world.getBlockId(mop.blockX, mop.blockY, mop.blockZ) > 0)
            {
                if (world.getBlockTileEntity(mop.blockX, mop.blockY, mop.blockZ) != null)
                {
                    TileEntity te = world.getBlockTileEntity(mop.blockX, mop.blockY, mop.blockZ);
                    
                    if (te instanceof IEnergyCapacitor)
                    {
                        IEnergyCapacitor link = (IEnergyCapacitor) te;
                        
                        link.capacitorRequestEnergy(1, true);
                        this.useEnergy(player, type);
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    @Override
    public void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem)
    {
        
    }
    
    @Override
    public int energyRequired(EnumUseType type, EntityPlayer player)
    {
        return 1;
    }
    
    @Override
    public void addInfoToItemMouseover(EntityPlayer player, ItemStack stack, boolean isSneaking, List list)
    {
    }
}
