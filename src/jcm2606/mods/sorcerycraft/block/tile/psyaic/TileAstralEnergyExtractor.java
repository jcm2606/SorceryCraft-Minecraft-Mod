package jcm2606.mods.sorcerycraft.block.tile.psyaic;

import jcm2606.mods.jccore.block.tile.TileEntityMultiblock;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.jccore.core.util.RenderUtil;
import jcm2606.mods.sorcerycraft.api.IEnergyInfused;
import jcm2606.mods.sorcerycraft.api.IExpandedSightHandler;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyReadable;
import jcm2606.mods.sorcerycraft.block.tile.astral.TileAstralStructure;
import jcm2606.mods.sorcerycraft.client.fx.FXBeam;
import jcm2606.mods.sorcerycraft.client.fx.FXLightningBolt;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.helper.RenderHandlerSC;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.MovingObjectPosition;

import org.lwjgl.opengl.GL11;

import codechicken.lib.vec.Vector3;

public class TileAstralEnergyExtractor extends TileEntityMultiblock implements IEnergyCapacitor, IEnergyReadable, IExpandedSightHandler
{
    public int energyStored;
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        this.energyStored = tag.getInteger("energyStored");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setInteger("energyStored", this.energyStored);
    }
    
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        
        for (int x = -2; x < 3; x++)
        {
            for (int z = -2; z < 3; z++)
            {
                int x2 = this.xCoord + x;
                int y2 = this.yCoord - 1;
                int z2 = this.zCoord + z;
                
                if ((x > -2 && x < 2) && (z > -2 && z < 2))
                {
                    continue;
                }
                
                if ((x != 2 && z != 2) || (x != -2 && z != -2) || (x != -2 && z == 2) || (x != 2 && -z != 2))
                {
                    if (this.worldObj.getBlockTileEntity(x2, y2, z2) instanceof TileAstralStructure)
                    {
                        TileAstralStructure structure = (TileAstralStructure) this.worldObj.getBlockTileEntity(x2, y2, z2);
                        
                        structure.setCoreTileEntity(this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord));
                        structure.isFormed = this.isValid;
                    }
                }
            }
        }
        
        if (this.isValid)
        {
            int generating = 0;
            
            for (int x = -2; x < 3; x++)
            {
                for (int z = -2; z < 3; z++)
                {
                    int x2 = this.xCoord + x;
                    int y2 = this.yCoord - 1;
                    int z2 = this.zCoord + z;
                    
                    if ((x > -2 && x < 2) && (z > -2 && z < 2))
                    {
                        continue;
                    }
                    
                    if ((x == 2 && z == 2) || (x == -2 && z == -2) || (x == -2 && z == 2) || (x == 2 && -z == 2))
                    {
                        if (this.worldObj.isRemote)
                        {
                            FXBeam fx = new FXBeam(this.worldObj, this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5, x2 + 0.5, y2 + 1,
                                    z2 + 0.5, 1, Reference.SPRITE_BEAM_3);
                            
                            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
                        }
                    }
                }
            }
            
            for (int x = -2; x < 3; x++)
            {
                for (int z = -2; z < 3; z++)
                {
                    int x2 = this.xCoord + x;
                    int y2 = this.yCoord - 1;
                    int z2 = this.zCoord + z;
                    
                    if (this.getBlockAtCoords(x2, y2, z2) instanceof IEnergyInfused)
                    {
                        int amount = ((IEnergyInfused) this.getBlockAtCoords(x2, y2, z2)).getCharge();
                        
                        generating += amount;
                        
                        if (this.hasSpace(amount))
                        {
                            this.addEnergy(amount, false);
                            
                            if(this.worldObj.isRemote)
                            {
                                FXLightningBolt bolt = new FXLightningBolt(this.worldObj, x2 + 0.5, y2 + 1, z2 + 0.5, this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5, this.worldObj.rand.nextLong(), 6, 0.1f, 5);
                                bolt.setBlendMode(771, 771);
                                bolt.setMultiplier(5f);
                                bolt.setWidth(0.03f);
                                bolt.setColours(0x7D0552, 0x000000);
                                bolt.defaultFractal();
                                
                                bolt.finalizeBolt();
                            }
                            
                            if (((IEnergyInfused) this.getBlockAtCoords(x2, y2, z2)).destroyBlockWhenExtracted() && this.worldObj.rand.nextInt(10000) <= ((IEnergyInfused) this
                                    .getBlockAtCoords(x2, y2, z2)).getDestroyChance())
                            {
                                this.worldObj.setBlock(x2, y2, z2, 0);
                            }
                        }
                    }
                }
            }
            
            for (int x = -3; x < 4; x++)
            {
                for (int y = -3; y < 4; y++)
                {
                    for (int z = -3; z < 4; z++)
                    {
                        int x2 = this.xCoord + x;
                        int y2 = this.yCoord + y;
                        int z2 = this.zCoord + z;
                        
                        if (this.worldObj.getBlockId(x2, y2, z2) != 0)
                        {
                            if (this.worldObj.getBlockTileEntity(x2, y2, z2) != null)
                            {
                                if (this.worldObj.getBlockTileEntity(x2, y2, z2) instanceof TileAstralEnergyNode)
                                {
                                    TileAstralEnergyNode node = (TileAstralEnergyNode) this.worldObj.getBlockTileEntity(x2, y2, z2);
                                    
                                    if (node.hasSpace())
                                    {
                                        node.addEnergy(this.takeEnergy(1), false, new Vector3(this.xCoord + 0.5, this.zCoord + 0.5, this.yCoord + 0.5));
                                    }
                                    
                                    if (this.worldObj.isRemote)
                                    {
                                        FXBeam fx = new FXBeam(this.worldObj, this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5, x2 + 0.5,
                                                y2 + 0.5, z2 + 0.5, 2, Reference.SPRITE_BEAM_1);
                                        fx.colour = Reference.ASTRAL_ENERGY_BEAM_COLOUR;
                                        
                                        Minecraft.getMinecraft().effectRenderer.addEffect(fx);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public boolean isFormedCorrectly()
    {
        for (int x = -2; x < 3; x++)
        {
            for (int z = -2; z < 3; z++)
            {
                int x2 = this.xCoord + x;
                int y2 = this.yCoord - 1;
                int z2 = this.zCoord + z;
                
                if ((x > -2 && x < 2) && (z > -2 && z < 2))
                {
                    continue;
                }
                
                if ((x == 2 && z == 2) || (x == -2 && z == -2) || (x == -2 && z == 2) || (x == 2 && -z == 2))
                {
                    if (this.worldObj.getBlockId(x2, y2, z2) != Block.obsidian.blockID)
                    {
                        return false;
                    }
                } else
                {
                    if (this.worldObj.getBlockId(x2, y2, z2) != SCObjects.astralStructure.blockID)
                    {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    @Override
    public int addEnergy(int amount, boolean ignoreLimit)
    {
        if (!this.isValid)
        {
            return 0;
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
        if (!this.isValid)
        {
            return 0;
        }
        
        if (this.getEnergyStored() >= amount)
        {
            this.energyStored -= amount;
            
            return amount;
        } else
        {
            return 0;
        }
    }
    
    @Override
    public int getEnergyLimit()
    {
        return 100;
    }
    
    @Override
    public int getEnergyStored()
    {
        return this.energyStored;
    }
    
    @Override
    public boolean hasEnergy()
    {
        return this.energyStored > 0;
    }
    
    @Override
    public boolean hasSpace()
    {
        return this.energyStored < this.getEnergyLimit();
    }
    
    @Override
    public boolean hasSpace(int amount)
    {
        return this.energyStored < this.getEnergyLimit() && this.energyStored + amount <= this.getEnergyLimit();
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
    public String getMessage()
    {
        if (this.isValid)
        {
            return this.getEnergyStored() + " / " + this.getEnergyLimit();
        }
        
        return "";
    }
    
    @Override
    public void renderOverlay(Minecraft mc, EntityPlayer player, boolean hasMedallion)
    {
        if (!this.isValid)
        {
            return;
        }
        
        int generating = 0;
        
        for (int x = -2; x < 3; x++)
        {
            for (int z = -2; z < 3; z++)
            {
                int x2 = this.xCoord + x;
                int y2 = this.yCoord - 1;
                int z2 = this.zCoord + z;
                
                if (this.getBlockAtCoords(x2, y2, z2) instanceof IEnergyInfused)
                {
                    generating += ((IEnergyInfused) this.getBlockAtCoords(x2, y2, z2)).getCharge();
                }
            }
        }
        
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
                
                RenderUtil.instance().drawTextureRect(RenderUtil.width / 4 - 7, RenderUtil.height / 4 - 4, 0, 28, 25, 34, 2.2f, 2.2f, 1.0f);
                
                GL11.glPushMatrix();
                GL11.glScaled(0.5, 0.5, 0.5);
                Minecraft.getMinecraft().fontRenderer.drawString("\2477" + capacitor.getEnergyStored() + " / " + capacitor.getEnergyLimit() + " Psy",
                        RenderUtil.width + 23, RenderUtil.height + 13, 0xffffff);
                Minecraft.getMinecraft().fontRenderer.drawString("\2477+" + generating + " Psy/t", RenderUtil.width + 23, RenderUtil.height + 23,
                        0xffffff);
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
        return this.takeEnergy(amount);
    }
}
