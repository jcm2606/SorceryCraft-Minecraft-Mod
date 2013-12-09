package jcm2606.mods.sorcerycraft.block.tile.psyaic;

import jcm2606.mods.jccore.block.tile.TileEntityJC;
import jcm2606.mods.sorcerycraft.api.IConduitConnector;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;

public class TileCreativeGenerator extends TileEntityJC implements IEnergyProvider, IConduitConnector
{
    public int generationAmount = 1;
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        this.generationAmount = tag.getInteger("generateAmount");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        tag.setInteger("generateAmount", this.generationAmount);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
    {
        if(player.isSneaking())
        {
            if((this.generationAmount > 0) )
            {
                this.generationAmount--;
            }
        } else {
            this.generationAmount++;
        }
        
        if(!world.isRemote)
        {
            player.sendChatToPlayer(ChatMessageComponent.createFromText("Generation amount: " + this.generationAmount + " Psy/tick"));
        }
        
        return true;
    }
    
    @Override
    public int provide()
    {
        return this.generationAmount;
    }
    
    @Override
    public int provide(int amount)
    {
        return this.provide();
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
}
