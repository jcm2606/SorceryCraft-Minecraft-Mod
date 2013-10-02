package jcm2606.mods.sorcerycraft.item.astral;

import jcm2606.mods.jccore.core.helper.NBTHelper;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.IItemUseTickHandler;
import jcm2606.mods.sorcerycraft.api.IKeyBound;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.astral.AstralManager;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketDrawAstralEnergyBeam;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketItemUseTick;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;

public class ItemAstralEnergyManipulationDrive extends SCItem implements IKeyBound, IItemUseTickHandler
{
    public ItemAstralEnergyManipulationDrive(int par1)
    {
        super(par1, "astralEnergyManipulationDrive");
        this.setMaxStackSize(1);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        player.setItemInUse(stack, 72000);
        
        return stack;
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.bow;
    }
    
    @Override
    public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count)
    {
        if(this.getMode(stack) == "INJECT")
        {
            this.injectEnergy(stack, player, player.worldObj);
        } else {
            this.extractEnergy(stack, player, player.worldObj);
        }
        
        PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketItemUseTick(count), PacketHandler.CHANNEL_SC));
    }
    
    public void injectEnergy(ItemStack stack, EntityPlayer player, World world)
    {
        MovingObjectPosition mop = GeneralUtil.getTargetBlock(world, player, false, 64.0f);
        
        if (mop == null)
        {
            return;
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
                    if(AstralManager.getTotalEnergy(player) > 0)
                    {
                        IEnergyCapacitor link = (IEnergyCapacitor) te;
                        
                        this.useEnergy(player, link.capacitorRequestEnergy(1, true));
                    }
                }
            }
        }
    }
    
    public void extractEnergy(ItemStack stack, EntityPlayer player, World world)
    {
        MovingObjectPosition mop = GeneralUtil.getTargetBlock(world, player, false, 64.0f);
        
        if (mop == null)
        {
            return;
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
                    
                    if(link.hasEnergy() && AstralManager.getTotalEnergy(player) < AstralManager.getMaxEnergy(player))
                    {
                        this.useEnergy(player, -link.capacitorProvideEnergy(1));
                    }
                }
            }
        }
    }
    
    public String getMode(ItemStack stack)
    {
        return NBTHelper.getString(NBTHelper.getNBTCompoundForItemStack(stack), "mode");
    }
    
    public void setMode(ItemStack stack, String mode)
    {
        NBTHelper.setString(NBTHelper.getNBTCompoundForItemStack(stack), "mode", mode);
    }
    
    public void useEnergy(EntityPlayer player, int energy)
    {
        for (int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if (stack != null)
            {
                if (stack.getItem() == SCObjects.astralCellEnergy)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack.getItem();
                    
                    if ((cell.getEnergy(stack)) >= energy)
                    {
                        cell.setEnergy(stack, cell.getEnergy(stack) - energy);
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public void doKeyBindingAction(EntityPlayer player, ItemStack stack, String keyBinding)
    {
        if(keyBinding.equals(Reference.KEY_BIND_INHAND_ITEM_DESC))
        {
            if(this.getMode(stack) == "INJECT")
            {
                this.setMode(stack, "EXTRACT");
            } else {
                this.setMode(stack, "INJECT");
            }
        }
    }
    
    @Override
    public void serverItemUseTick(ItemStack stack, EntityPlayer player, int count)
    {
        if(this.getMode(stack) == "INJECT")
        {
            this.injectEnergy(stack, player, player.worldObj);
        } else {
            this.extractEnergy(stack, player, player.worldObj);
        }
    }
}
