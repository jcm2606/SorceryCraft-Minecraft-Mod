package jcm2606.mods.sorcerycraft.block.tile.astral;

import jcm2606.mods.jccore.block.tile.TileEntityJC;
import jcm2606.mods.jccore.core.util.ConvertUtil;
import jcm2606.mods.jccore.core.util.Coord;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.jccore.core.util.RenderUtil;
import jcm2606.mods.sorcerycraft.api.ILinkable;
import jcm2606.mods.sorcerycraft.api.IMedallionPerceptionOverlayHandler;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyProvider;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyReadable;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyReciever;
import jcm2606.mods.sorcerycraft.client.fx.FXAstralEnergyBeam;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralLinkingCard;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class TileAstralEnergyNode extends TileEntityJC implements IEnergyCapacitor, IEnergyProvider, ILinkable, IEnergyReadable, IEnergyReciever, IMedallionPerceptionOverlayHandler
{
    public int energyStored;
    public boolean isConnectedToSource;
    
    public ItemStack[] stackList = new ItemStack[8];
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        energyStored = tag.getInteger("energyStored");
        isConnectedToSource = tag.getBoolean("hasSource");
        
        NBTTagList tagList = tag.getTagList("StackList");
        
        for (int i = 0; i < tagList.tagCount(); i++)
        {
            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            
            byte b = tagCompound.getByte("Stack");
            
            if (b >= 0 && (b < this.stackList.length))
            {
                this.stackList[b] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setBoolean("hasSource", this.isConnectedToSource);
        tag.setInteger("energyStored", this.energyStored);
        
        NBTTagList tagList = new NBTTagList();
        
        for (int i = 0; i < this.stackList.length; i++)
        {
            if (this.stackList[i] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                
                tagCompound.setByte("Stack", (byte) i);
                
                this.stackList[i].writeToNBT(tagCompound);
                
                tagList.appendTag(tagCompound);
            }
        }
        
        tag.setTag("StackList", tagList);
    }
    
    @Override
    public void updateEntity()
    {
        if (this.worldObj.isRemote)
        {
            int divide = 5;
            
            if (this.energyStored > this.getEnergyLimit() * 2)
            {
                worldObj.spawnParticle("reddust", xCoord + 0.5 - (worldObj.rand.nextGaussian() / divide), yCoord + 0.5 + (worldObj.rand
                        .nextGaussian() / divide), zCoord + 0.5 - (worldObj.rand.nextGaussian() / divide), 0, 0, 0);
                worldObj.spawnParticle("reddust", xCoord + 0.5 + (worldObj.rand.nextGaussian() / divide), yCoord + 0.5 + (worldObj.rand
                        .nextGaussian() / divide), zCoord + 0.5 + (worldObj.rand.nextGaussian() / divide), 0, 0, 0);
            }
            
            if (this.energyStored > this.getEnergyLimit() * 4)
            {
                worldObj.spawnParticle("smoke", xCoord + 0.5 - (worldObj.rand.nextGaussian() / divide),
                        yCoord + 0.5 + (worldObj.rand.nextGaussian() / divide), zCoord + 0.5 - (worldObj.rand.nextGaussian() / divide), 0, 0, 0);
                worldObj.spawnParticle("smoke", xCoord + 0.5 + (worldObj.rand.nextGaussian() / divide),
                        yCoord + 0.5 + (worldObj.rand.nextGaussian() / divide), zCoord + 0.5 + (worldObj.rand.nextGaussian() / divide), 0, 0, 0);
            }
            
            if (this.energyStored > this.getEnergyLimit() * 8)
            {
                worldObj.spawnParticle("flame", xCoord + 0.5 - (worldObj.rand.nextGaussian() / divide),
                        yCoord + 0.5 + (worldObj.rand.nextGaussian() / divide), zCoord + 0.5 - (worldObj.rand.nextGaussian() / divide), 0, 0, 0);
                worldObj.spawnParticle("flame", xCoord + 0.5 + (worldObj.rand.nextGaussian() / divide),
                        yCoord + 0.5 + (worldObj.rand.nextGaussian() / divide), zCoord + 0.5 + (worldObj.rand.nextGaussian() / divide), 0, 0, 0);
            }
        }
        
        if (this.energyStored > this.getEnergyLimit() * 16)
        {
            this.worldObj.setBlock(xCoord, yCoord, zCoord, 0);
            
            this.worldObj.createExplosion(null, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, 1, true);
        }
        
        for (int i = 0; i < this.stackList.length; i++)
        {
            ItemStack stack = this.stackList[i];
            
            if (stack != null)
            {
                Coord coord = this.getCoordFromIndex(i);
                
                if (coord == null)
                {
                    return;
                }
                
                int x = (int) coord.x;
                int y = (int) coord.y;
                int z = (int) coord.z;
                
                if (this.getBlockAtCoords(x, y, z) != null)
                {
                    if (this.getWorldObj().getBlockTileEntity(x, y, z) != null)
                    {
                        if (this.getWorldObj().getBlockTileEntity(x, y, z) instanceof IEnergyCapacitor)
                        {
                            IEnergyCapacitor capacitor = (IEnergyCapacitor) worldObj.getBlockTileEntity(x, y, z);
                            
                            if (this.hasEnergy() && capacitor.hasSpace())
                            {
                                capacitor.capacitorRequestEnergy(1, false);
                                this.capacitorProvideEnergy(1);
                            }
                            
                            if (this.worldObj.isRemote)
                            {
                                FXAstralEnergyBeam fx = new FXAstralEnergyBeam(this.worldObj, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, x + 0.5,
                                        y + 0.5, z + 0.5, 2);
                                
                                Minecraft.getMinecraft().effectRenderer.addEffect(fx);
                            }
                        }
                    }
                } else
                {
                    this.setStackInIndex(i, null);
                }
            }
        }
        
        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            if(GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, direction, worldObj) != null)
            {
                if(GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, direction, worldObj) instanceof IEnergyProvider)
                {
                    this.capacitorRequestEnergy(((IEnergyProvider) GeneralUtil.getBlockTileFromNeighbour(xCoord, yCoord, zCoord, direction, worldObj)).provide(), false);
                }
            }
        }
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        this.readFromNBT(pkt.customParam1);
    }
    
    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side)
    {
        return 0;
    }
    
    @Override
    public int provide()
    {
        return this.capacitorProvideEnergy(1);
    }
    
    @Override
    public int provide(int amount)
    {
        return amount <= provide() ? amount : 0;
    }
    
    @Override
    public int getEnergyLimit()
    {
        return 10;
    }
    
    @Override
    public int getEnergyStored()
    {
        return this.energyStored;
    }
    
    @Override
    public int capacitorRequestEnergy(int amount, boolean ignoreLimit)
    {
        if (ignoreLimit)
        {
            this.energyStored += amount;
            
            return amount;
        }
        
        if (this.getEnergyStored() < this.getEnergyLimit())
        {
            this.energyStored += amount;
            
            return amount;
        }
        
        return 0;
    }
    
    @Override
    public int capacitorProvideEnergy(int amount)
    {
        if (this.getEnergyStored() > 0)
        {
            this.energyStored -= amount;
            
            return amount;
        } else
        {
            return 0;
        }
    }
    
    @Override
    public boolean hasEnergy()
    {
        return this.getEnergyStored() > 0;
    }
    
    @Override
    public boolean hasSpace()
    {
        return this.getEnergyStored() < this.getEnergyLimit();
    }
    
    @Override
    public boolean hasSpace(int amount)
    {
        return this.energyStored < this.getEnergyLimit() && this.energyStored + amount <= this.getEnergyLimit();
    }
    
    public void setStackInIndex(int index, ItemStack stack)
    {
        this.stackList[index] = stack;
    }
    
    public ItemStack getStackFromIndex(int index)
    {
        return this.stackList[index];
    }
    
    public Coord getCoordFromIndex(int index)
    {
        ItemStack stack = this.stackList[index];
        
        if (stack == null)
        {
            return null;
        }
        
        Coord coord = ((ItemAstralLinkingCard) stack.getItem()).getCoordFromStack(stack);
        
        if (coord != null)
        {
            return coord;
        }
        
        return null;
    }
    
    public int getNextFreeIndex()
    {
        for (int i = 0; i < this.stackList.length; i++)
        {
            if (this.stackList[i] == null)
            {
                return i;
            }
        }
        
        return -1;
    }
    
    @Override
    public void applyCard(ItemAstralLinkingCard card, ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int sideClicked)
    {
        TileAstralEnergyNode node = (TileAstralEnergyNode) world.getBlockTileEntity(x, y, z);
        
        if (node.getNextFreeIndex() == -1)
        {
            if (!world.isRemote)
            {
                player.sendChatToPlayer(ConvertUtil
                        .getChatMessageComponent("This Astral Energy Node does not have any free slots for connection link data."));
            }
            
            return;
        }
        
        ItemStack stack2 = stack.copy();
        
        node.setStackInIndex(node.getNextFreeIndex(), stack2);
        
        if (!world.isRemote)
        {
            player.sendChatToPlayer(ConvertUtil.getChatMessageComponent("Written data from linking card to node."));
        }
    }
    
    @Override
    public String getMessage()
    {
        return this.energyStored + " / " + this.getEnergyLimit();
    }
    
    @Override
    public void recieveEnergy(int energy)
    {
        this.capacitorRequestEnergy(energy, false);
    }
    
    @Override
    public int getEnergyRequirement()
    {
        return 0;
    }

    @Override
    public void renderMedallionOverlay(Minecraft mc, EntityPlayer player)
    {
        MovingObjectPosition mop = GeneralUtil.getTargetBlock(player.worldObj, player, false, 5.0f);
        
        if (mop == null)
        {
            return;
        }
        
        int x = mop.blockX;
        int y = mop.blockY;
        int z = mop.blockZ;
        
        if(player.worldObj.getBlockTileEntity(x, y, z) != null)
        {
            if(player.worldObj.getBlockTileEntity(x, y, z) instanceof IEnergyCapacitor)
            {
                IEnergyCapacitor capacitor = (IEnergyCapacitor) player.worldObj.getBlockTileEntity(x, y, z);
            
                GL11.glPushMatrix();
                GL11.glScaled(0.5, 0.5, 0.5);
                Minecraft.getMinecraft().fontRenderer.drawString("\2477" + capacitor.getEnergyStored() + " AE / " + capacitor.getEnergyLimit() + " AE", RenderUtil.width + 8, RenderUtil.height + 8, 0xffffff);
                GL11.glScaled(1, 1, 1);
                GL11.glPopMatrix();
            }
        }
    }
}
