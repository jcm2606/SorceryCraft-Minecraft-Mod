package jcm2606.mods.sorcerycraft.core.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import jcm2606.mods.sorcerycraft.client.fx.FXAstralEnergyBeam;
import jcm2606.mods.sorcerycraft.core.network.PacketBase;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;

public class PacketDrawAstralEnergyBeam extends PacketBase
{
    double startX;
    double startY;
    double startZ;
    
    double endX;
    double endY;
    double endZ;
    
    int age;
    
    boolean slide;
    
    public PacketDrawAstralEnergyBeam()
    {
        super(PacketType.DRAW_ASTRAL_ENERGY_BEAM, false);
    }
    
    public PacketDrawAstralEnergyBeam(double startX, double startY, double startZ, double endX, double endY, double endZ, int age, boolean slide)
    {
        super(PacketType.DRAW_ASTRAL_ENERGY_BEAM, false);
        this.startX = startX;
        this.startY = startY;
        this.startZ = startZ;
        
        this.endX = endX;
        this.endY = endY;
        this.endZ = endZ;
        
        this.age = age;
        
        this.slide = slide;
    }
    
    @Override
    public void readData(DataInputStream data) throws IOException
    {
        this.startX = data.readDouble();
        this.startY = data.readDouble();
        this.startZ = data.readDouble();
        
        this.endX = data.readDouble();
        this.endY = data.readDouble();
        this.endZ = data.readDouble();
        
        this.age = data.readInt();
        
        this.slide = data.readBoolean();
    }
    
    @Override
    public void writeData(DataOutputStream dos) throws IOException
    {
        dos.writeDouble(startX);
        dos.writeDouble(startY);
        dos.writeDouble(startZ);
        
        dos.writeDouble(endX);
        dos.writeDouble(endY);
        dos.writeDouble(endZ);
        
        dos.writeInt(age);
        
        dos.writeBoolean(slide);
    }
    
    @Override
    public void execute(INetworkManager network, Player player)
    {
        FXAstralEnergyBeam fx = new FXAstralEnergyBeam(Minecraft.getMinecraft().theWorld, startX, startY, startZ, endX, endY, endZ, age);
        fx.slide = this.slide;
        
        Minecraft.getMinecraft().effectRenderer.addEffect(fx);
    }
}
