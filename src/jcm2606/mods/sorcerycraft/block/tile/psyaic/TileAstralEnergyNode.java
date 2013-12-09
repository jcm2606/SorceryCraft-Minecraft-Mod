package jcm2606.mods.sorcerycraft.block.tile.psyaic;

import jcm2606.mods.jccore.block.tile.TileEntityJC;
import jcm2606.mods.jccore.core.util.ConvertUtil;
import jcm2606.mods.jccore.core.util.Coord;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.jccore.core.util.RenderUtil;
import jcm2606.mods.sorcerycraft.api.IExpandedSightHandler;
import jcm2606.mods.sorcerycraft.api.ILinkable;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyProvider;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyReadable;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyReciever;
import jcm2606.mods.sorcerycraft.client.fx.FXBeam;
import jcm2606.mods.sorcerycraft.client.fx.FXLightningBolt;
import jcm2606.mods.sorcerycraft.core.config.Settings;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
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

import codechicken.lib.vec.Vector3;

public class TileAstralEnergyNode extends TileEntityJC implements IEnergyCapacitor, IEnergyProvider, ILinkable, IEnergyReadable, IEnergyReciever,
IExpandedSightHandler
{
    public int energyStored;
    public boolean isConnectedToSource;
    
    public ItemStack[] stackList = new ItemStack[8];
    
    public EnumRedstoneState redstoneState = EnumRedstoneState.NOTHING;
    
    public boolean isReceivingRedstoneSignal = false;
    
    private static enum EnumRedstoneState
    {
        NOTHING, CUT_PROVIDING, CUT_REQUESTING, BOTH
    }
    
    private String[] stateStrings =
        { "Nothing", "Stop providing", "Stop requesting", "Both" };
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
    {
        if (player.getCurrentEquippedItem() != null)
        {
            return false;
        }
        
        int num = 0;
        
        if (this.redstoneState.ordinal() >= 3)
        {
            num = 0;
        } else
        {
            num = this.redstoneState.ordinal() + 1;
        }
        
        this.redstoneState = EnumRedstoneState.values()[num];
        
        if (!this.worldObj.isRemote)
        {
            player.sendChatToPlayer(ConvertUtil.getChatMessageComponent("Redstone interaction behaviour: " + this.stateStrings[num]));
        }
        
        return true;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        this.energyStored = tag.getInteger("energyStored");
        this.isConnectedToSource = tag.getBoolean("hasSource");
        
        for (EnumRedstoneState state : EnumRedstoneState.values())
        {
            if (state.ordinal() == tag.getInteger("redstoneState"))
            {
                this.redstoneState = EnumRedstoneState.values()[state.ordinal()];
            }
        }
        
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
        tag.setInteger("redstoneState", this.redstoneState.ordinal());
        
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
        super.updateEntity();
        
        if (this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord))
        {
            this.isReceivingRedstoneSignal = true;
        } else
        {
            this.isReceivingRedstoneSignal = false;
        }
        
        if (this.worldObj.isRemote)
        {
            int divide = 5;
            
            if (this.energyStored > this.getEnergyLimit() * 2)
            {
                this.worldObj.spawnParticle("reddust", this.xCoord + 0.5 - (this.worldObj.rand.nextGaussian() / divide),
                        this.yCoord + 0.5 + (this.worldObj.rand.nextGaussian() / divide),
                        this.zCoord + 0.5 - (this.worldObj.rand.nextGaussian() / divide), 0, 0, 0);
                this.worldObj.spawnParticle("reddust", this.xCoord + 0.5 + (this.worldObj.rand.nextGaussian() / divide),
                        this.yCoord + 0.5 + (this.worldObj.rand.nextGaussian() / divide),
                        this.zCoord + 0.5 + (this.worldObj.rand.nextGaussian() / divide), 0, 0, 0);
            }
            
            if (this.energyStored > this.getEnergyLimit() * 4)
            {
                this.worldObj.spawnParticle("smoke", this.xCoord + 0.5 - (this.worldObj.rand.nextGaussian() / divide),
                        this.yCoord + 0.5 + (this.worldObj.rand.nextGaussian() / divide),
                        this.zCoord + 0.5 - (this.worldObj.rand.nextGaussian() / divide), 0, 0, 0);
                this.worldObj.spawnParticle("smoke", this.xCoord + 0.5 + (this.worldObj.rand.nextGaussian() / divide),
                        this.yCoord + 0.5 + (this.worldObj.rand.nextGaussian() / divide),
                        this.zCoord + 0.5 + (this.worldObj.rand.nextGaussian() / divide), 0, 0, 0);
            }
            
            if (this.energyStored > this.getEnergyLimit() * 8)
            {
                this.worldObj.spawnParticle("flame", this.xCoord + 0.5 - (this.worldObj.rand.nextGaussian() / divide),
                        this.yCoord + 0.5 + (this.worldObj.rand.nextGaussian() / divide),
                        this.zCoord + 0.5 - (this.worldObj.rand.nextGaussian() / divide), 0, 0, 0);
                this.worldObj.spawnParticle("flame", this.xCoord + 0.5 + (this.worldObj.rand.nextGaussian() / divide),
                        this.yCoord + 0.5 + (this.worldObj.rand.nextGaussian() / divide),
                        this.zCoord + 0.5 + (this.worldObj.rand.nextGaussian() / divide), 0, 0, 0);
            }
        }
        
        if (this.energyStored > this.getEnergyLimit() * 16)
        {
            this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, 0);
            
            this.worldObj.createExplosion(null, this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5, 1, true);
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
                            IEnergyCapacitor capacitor = (IEnergyCapacitor) this.worldObj.getBlockTileEntity(x, y, z);
                            
                            if (this.getDistanceFrom(x, y, z) <= 272.75)
                            {
                                if (this.hasEnergy() && capacitor.hasSpace())
                                {
                                    if(this.isReceivingRedstoneSignal && (this.redstoneState == EnumRedstoneState.CUT_PROVIDING || this.redstoneState == EnumRedstoneState.BOTH))
                                    {
                                        continue;
                                    }
                                    
                                    if(this.worldObj.getBlockTileEntity(x, y, z) instanceof TileAstralEnergyNode)
                                    {
                                        TileAstralEnergyNode node = (TileAstralEnergyNode) this.worldObj.getBlockTileEntity(x, y, z);
                                        
                                        if(node.isReceivingRedstoneSignal && (node.redstoneState == EnumRedstoneState.CUT_REQUESTING || node.redstoneState == EnumRedstoneState.BOTH))
                                        {
                                            continue;
                                        }
                                    }
                                    
                                    if(capacitor.getEnergyStored() < this.getEnergyStored())
                                    {
                                        capacitor.addEnergy(1, false, new Vector3(this.xCoord, this.yCoord, this.zCoord));
                                        this.takeEnergy(1, new Vector3(x, y, z));
                                    }
                                }
                                
                                if (this.worldObj.isRemote)
                                {
                                    FXBeam fx = new FXBeam(this.worldObj, this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5, x + 0.5, y + 0.5,
                                            z + 0.5, 2, Reference.SPRITE_BEAM_2);
                                    
                                    fx.slide = false;
                                    fx.colour = Reference.ASTRAL_ENERGY_BEAM_COLOUR;
                                    fx.opacity = 0.009f;
                                    
                                    Minecraft.getMinecraft().effectRenderer.addEffect(fx);
                                }
                            }
                        }
                    }
                } else
                {
                    this.setStackInIndex(i, null);
                }
            }
        }
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            if (GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj) != null)
            {
                if (GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord, this.zCoord, direction, this.worldObj) instanceof IEnergyProvider)
                {
                    if (this.hasSpace())
                    {
                        this.addEnergy(((IEnergyProvider) GeneralUtil.getBlockTileFromNeighbour(this.xCoord, this.yCoord, this.zCoord,
                                direction, this.worldObj)).provide(), false);
                    }
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
        this.readFromNBT(pkt.data);
    }
    
    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side)
    {
        return 0;
    }
    
    @Override
    public int provide()
    {
        return this.takeEnergy(1);
    }
    
    @Override
    public int provide(int amount)
    {
        return amount <= this.provide() ? amount : 0;
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
    public int addEnergy(int amount, boolean ignoreLimit)
    {
        if (this.isReceivingRedstoneSignal)
        {
            if (this.redstoneState == EnumRedstoneState.CUT_REQUESTING || this.redstoneState == EnumRedstoneState.BOTH)
            {
                return 0;
            }
        }
        
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
    public int takeEnergy(int amount)
    {
        if (this.isReceivingRedstoneSignal)
        {
            if (this.redstoneState == EnumRedstoneState.CUT_PROVIDING || this.redstoneState == EnumRedstoneState.BOTH)
            {
                return 0;
            }
        }
        
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
        TileAstralEnergyNode node2 = (TileAstralEnergyNode) world.getBlockTileEntity((int) card.getCoordFromStack(stack).x, (int) card.getCoordFromStack(stack).y, (int) card.getCoordFromStack(stack).z);
        
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
        ItemStack stack3 = stack.copy();
        
        if (this.getDistanceFrom(card.getCoordFromStack(stack2).x, card.getCoordFromStack(stack2).y, card.getCoordFromStack(stack2).z) <= 272.75)
        {
            node.setStackInIndex(node.getNextFreeIndex(), stack2);
            
            card.setCoords(stack3, new Coord(x, y, z));
            
            node2.setStackInIndex(node2.getNextFreeIndex(), stack3);
            
            if (!world.isRemote)
            {
                player.sendChatToPlayer(ConvertUtil.getChatMessageComponent("Written data from linking card to node."));
            }
        } else
        {
            if (!world.isRemote)
            {
                player.sendChatToPlayer(ConvertUtil.getChatMessageComponent("The connection distance is too great."));
            }
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
        this.addEnergy(energy, false);
    }
    
    @Override
    public int getEnergyRequirement()
    {
        return 0;
    }
    
    @Override
    public void renderOverlay(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        MovingObjectPosition mop = GeneralUtil.getTargetBlock(player.worldObj, player, false, 5.0f);
        
        if (mop == null)
        {
            return;
        }
        
        int x = mop.blockX;
        int y = mop.blockY;
        int z = mop.blockZ;
        
        if (player.worldObj.getBlockTileEntity(x, y, z) != null)
        {
            if (player.worldObj.getBlockTileEntity(x, y, z) instanceof IEnergyCapacitor)
            {
                IEnergyCapacitor capacitor = (IEnergyCapacitor) player.worldObj.getBlockTileEntity(x, y, z);
                
                RenderHandlerSC.bindTexture(Reference.PATH_TEXTURES_GUI_HUD + "overlay.png");
                
                RenderUtil.instance().drawTextureRect(RenderUtil.width / 4 + 3, RenderUtil.height / 2 + 3, 0, 28, 25, 34, 2f, 1.0f, 1.0f);
                
                GL11.glPushMatrix();
                GL11.glScaled(0.5, 0.5, 0.5);
                Minecraft.getMinecraft().fontRenderer.drawString("\2477" + capacitor.getEnergyStored() + " / " + capacitor.getEnergyLimit() + " Psy",
                        RenderUtil.width + 23, RenderUtil.height + 10, 0xffffff);
                GL11.glScaled(1, 1, 1);
                GL11.glPopMatrix();
            }
        }
    }
    
    @Override
    public boolean canRender(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        return hasMedallion || player.capabilities.isCreativeMode;
    }
    
    @Override
    public int addEnergy(int amount, boolean ignoreLimit, Vector3 vec3)
    {
        return this.addEnergy(amount, ignoreLimit);
    }
    
    @Override
    public int takeEnergy(int amount, Vector3 vec3)
    {
        if(this.worldObj.rand.nextInt(100) <= 100)
        {
            FXLightningBolt bolt = new FXLightningBolt(this.getWorldObj(), xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, vec3.x + 0.5, vec3.y + 0.5, vec3.z + 0.5, worldObj.rand.nextLong(), 5, 0.1F, 5);
            
            bolt.setBlendMode(771, 771);
            bolt.setMultiplier(0.2f / (bolt.getLength()));
            bolt.setWidth(0.0125f);
            bolt.setColours(0x7D0552, 0x141414);
            bolt.defaultFractal();
            
            if(!Settings.LOW_FX)
            {
                bolt.finalizeBolt();
            } else {
                if(worldObj.isRemote)
                {
                    if(Minecraft.getMinecraft().isFancyGraphicsEnabled())
                    {
                        bolt.finalizeBolt();
                    }
                }
            }
        }
        
        return this.takeEnergy(amount);
    }
}
