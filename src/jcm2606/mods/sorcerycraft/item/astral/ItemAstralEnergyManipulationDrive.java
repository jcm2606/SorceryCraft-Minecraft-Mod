package jcm2606.mods.sorcerycraft.item.astral;

import jcm2606.mods.jccore.core.helper.NBTHelper;
import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.IItemUseTickHandler;
import jcm2606.mods.sorcerycraft.api.IKeyBound;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.astral.AstralManager;
import jcm2606.mods.sorcerycraft.client.fx.FXBeam;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketItemUseTick;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
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
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
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
        if (this.getMode(stack) == "INJECT")
        {
            this.injectEnergy(stack, player, player.worldObj);
        } else
        {
            this.extractEnergy(stack, player, player.worldObj);
        }
        
        PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketItemUseTick(count), PacketHandler.CHANNEL_SC));
    }
    
    public void injectEnergy(ItemStack stack, EntityPlayer player, World world)
    {
        MovingObjectPosition mop = GeneralUtil.getTargetBlock(world, player, false, 64.0f);
        
        Vec3 v = player.getLookVec();
        
        double x2 = player.posX + v.xCoord * 10.0D;
        double y2 = player.posY + v.yCoord * 10.0D;
        double z2 = player.posZ + v.zCoord * 10.0D;
        
        double adjAngle = 25.0D;
        double dist = 0.4D;
        
        EntityPlayer center = player;
        
        double posX = player.posX;
        double posY = player.posY;
        double posZ = player.posZ;
        
        posX -= MathHelper.cos(player.rotationYaw / 180.0F * 3.141593F) * 0.09F;
        posY += 0.02000000000001161D;
        posZ -= MathHelper.sin(player.rotationYaw / 180.0F * 3.141593F) * 0.09F;
        
        if (world.isRemote)
        {
            FXBeam beam = new FXBeam(Minecraft.getMinecraft().theWorld, posX, posY, posZ, x2, y2, z2, 1, Reference.SPRITE_BEAM_1);
            beam.colour = Reference.ASTRAL_ENERGY_BEAM_COLOUR;
            
            Minecraft.getMinecraft().effectRenderer.addEffect(beam);
        }
        
        if (mop == null)
        {
            return;
        }
        
        if (world.getBlockId(mop.blockX, mop.blockY, mop.blockZ) > 0)
        {
            if (world.getBlockTileEntity(mop.blockX, mop.blockY, mop.blockZ) != null)
            {
                TileEntity te = world.getBlockTileEntity(mop.blockX, mop.blockY, mop.blockZ);
                
                if (te instanceof IEnergyCapacitor)
                {
                    if (AstralManager.getTotalEnergy(player) > 0)
                    {
                        IEnergyCapacitor link = (IEnergyCapacitor) te;
                        
                        AstralManager.takeEnergyFrom(player, link.addEnergy(1, true));
                    }
                }
            }
        }
    }
    
    public void extractEnergy(ItemStack stack, EntityPlayer player, World world)
    {
        MovingObjectPosition mop = GeneralUtil.getTargetBlock(world, player, false, 64.0f);
        
        Vec3 v = player.getLookVec();
        
        double x2 = player.posX + v.xCoord * 10.0D;
        double y2 = player.posY + v.yCoord * 10.0D;
        double z2 = player.posZ + v.zCoord * 10.0D;
        
        double adjAngle = 25.0D;
        double dist = 0.4D;
        
        EntityPlayer center = player;
        
        double posX = player.posX;
        double posY = player.posY;
        double posZ = player.posZ;
        
        posX -= MathHelper.cos(player.rotationYaw / 180.0F * 3.141593F) * 0.09F;
        posY += 0.02000000000001161D;
        posZ -= MathHelper.sin(player.rotationYaw / 180.0F * 3.141593F) * 0.09F;
        
        if (world.isRemote)
        {
            FXBeam beam = new FXBeam(Minecraft.getMinecraft().theWorld, posX, posY, posZ, x2, y2, z2, 1, Reference.SPRITE_BEAM_1);
            beam.colour = Reference.ASTRAL_ENERGY_BEAM_COLOUR;
            
            Minecraft.getMinecraft().effectRenderer.addEffect(beam);
        }
        
        if (mop == null)
        {
            return;
        }
        
        if (world.getBlockId(mop.blockX, mop.blockY, mop.blockZ) > 0)
        {
            if (world.getBlockTileEntity(mop.blockX, mop.blockY, mop.blockZ) != null)
            {
                TileEntity te = world.getBlockTileEntity(mop.blockX, mop.blockY, mop.blockZ);
                
                if (te instanceof IEnergyCapacitor)
                {
                    IEnergyCapacitor link = (IEnergyCapacitor) te;
                    
                    if (link.hasEnergy() && AstralManager.getTotalEnergy(player) < AstralManager.getMaxEnergy(player))
                    {
                        AstralManager.addEnergyTo(player, link.takeEnergy(1));
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
    
    @Override
    public void doKeyBindingAction(EntityPlayer player, ItemStack stack, String keyBinding)
    {
        if (keyBinding.equals(Reference.KEY_BIND_INHAND_ITEM_DESC))
        {
            if (this.getMode(stack) == "INJECT")
            {
                this.setMode(stack, "EXTRACT");
            } else
            {
                this.setMode(stack, "INJECT");
            }
        }
    }
    
    @Override
    public void serverItemUseTick(ItemStack stack, EntityPlayer player, int count)
    {
        if (this.getMode(stack) == "INJECT")
        {
            this.injectEnergy(stack, player, player.worldObj);
        } else
        {
            this.extractEnergy(stack, player, player.worldObj);
        }
    }
}
