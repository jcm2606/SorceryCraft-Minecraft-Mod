package jcm2606.mods.sorcerycraft.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntitySkull extends TileEntity {
	String owner = "";
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
	    super.readFromNBT(nbt);
	    owner = nbt.getString("Owner");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
	    super.writeToNBT(nbt);
	    nbt.setString("Owner", owner);
	}
	
	public void setOwner(String s)
	{
		this.owner = s;
	}
	
	public String getOwner()
	{
		return this.owner;
	}
}
