package jcm2606.mods.sorcerycraft.block.tile;

import net.minecraft.tileentity.TileEntity;

public class TileEntityTeleporter extends TileEntity {
	private int x;
	private int y;
	private int z;
	
	private int linkX;
	private int linkY;
	private int linkZ;
	
	public TileEntityTeleporter()
	{
		super();
	}
	
	public void setCoords(int xCoord, int yCoord, int zCoord)
	{
		this.x = xCoord;
		this.y = yCoord;
		this.z = zCoord;
	}
	
	public void setLinkCoords(int xCoord, int yCoord, int zCoord)
	{
		this.linkX = xCoord;
		this.linkY = yCoord;
		this.linkZ = zCoord;
	}
	
	public int getXCoord()
	{
		return this.x;
	}
	
	public int getYCoord()
	{
		return this.y;
	}
	
	public int getZCoord()
	{
		return this.z;
	}
	
	public int getLinkXCoord()
	{
		return this.linkX;
	}
	
	public int getLinkYCoord()
	{
		return this.linkY;
	}
	
	public int getLinkZCoord()
	{
		return this.linkZ;
	}
}
