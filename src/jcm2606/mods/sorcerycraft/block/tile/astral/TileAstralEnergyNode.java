package jcm2606.mods.sorcerycraft.block.tile.astral;

import jcm2606.mods.jccore.tile.TileEntityJC;
import jcm2606.mods.jccore.util.ConvertUtil;
import jcm2606.mods.jccore.util.Coord;
import jcm2606.mods.sorcerycraft.api.ILinkable;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyProvider;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketDrawAstralEnergyBeam;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralLinkingCard;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TileAstralEnergyNode extends TileEntityJC implements IEnergyCapacitor, IEnergyProvider, ILinkable
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
                                capacitor.capacitorRequestEnergy(this.provide(), false);
                                this.capacitorProvideEnergy(this.provide());
                            }
                            
                            if (this.worldObj.isRemote)
                            {
                                PacketDispatcher.sendPacketToAllPlayers(PacketType.populatePacket(new PacketDrawAstralEnergyBeam(xCoord + 0.5,
                                        yCoord + 0.5, zCoord + 0.5, x + 0.5, y + 0.5, z + 0.5, 2, false), PacketHandler.CHANNEL_SC));
                            }
                        }
                    }
                } else
                {
                    this.setStackInIndex(i, null);
                }
            }
        }
        
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlockId(xCoord, yCoord, zCoord));
        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord - 1, this.yCoord - 1, this.zCoord - 1, this.xCoord + 1, this.yCoord + 1,
                this.zCoord + 1);
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
        if (this.energyStored > this.getEnergyLimit() * 8)
        {
            return 15;
        } else
            if (this.energyStored > this.getEnergyLimit() * 4)
            {
                return 10;
            } else
                if (this.energyStored > this.getEnergyLimit() * 2)
                {
                    return 5;
                } else
                {
                    return 0;
                }
    }
    
    @Override
    public int provide()
    {
        return 1;
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
}
