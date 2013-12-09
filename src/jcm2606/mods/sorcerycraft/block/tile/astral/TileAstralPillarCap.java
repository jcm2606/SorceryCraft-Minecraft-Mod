package jcm2606.mods.sorcerycraft.block.tile.astral;

import jcm2606.mods.jccore.block.tile.TileEntityJC;
import jcm2606.mods.sorcerycraft.client.fx.FXLightningBolt;
import jcm2606.mods.sorcerycraft.core.SCObjects;

public class TileAstralPillarCap extends TileEntityJC
{
    public boolean valid = false;
    
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        this.valid = this.checkValidity();
        
        if(this.worldObj.isRemote)
        {
            if(this.valid)
            {
                if(this.worldObj.rand.nextInt(1000) <= 30)
                {
                    double multiplier = 4;
                    
                    FXLightningBolt bolt = new FXLightningBolt(this.getWorldObj(), xCoord + 0.5, yCoord + 0.75, zCoord + 0.5, xCoord + 0.5 + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * multiplier, yCoord + 0.5 + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * multiplier, zCoord + 0.5 + (this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble()) * multiplier, worldObj.rand.nextLong(), (10 + this.worldObj.rand.nextInt(3) - 1), 10F, 2);
                    
                    bolt.setBlendMode(771, 771);
                    bolt.setMultiplier(4);
                    bolt.setWidth(0.025f);
                    bolt.setColours(0x7D0552, 0x777777);
                    bolt.defaultFractal();
                    bolt.finalizeBolt();
                }
                
                for(int i = 0; i < 15; i++)
                {
                    for(int j = 1; j < 4; j++)
                    {
                        for(int k = 0; k < 15; k++)
                        {
                            int x = this.xCoord - 7 + i;
                            int y = this.yCoord - j;
                            int z = this.zCoord - 7 + k;
                            
                            if(this.worldObj.getBlockId(x, y, z) != 0 &&
                                    this.worldObj.getBlockId(x, y, z) != SCObjects.astralPillarStructure.blockID &&
                                    this.worldObj.getBlockId(x, y, z) != SCObjects.astralPillarBase.blockID &&
                                    this.worldObj.getBlockId(x, y, z) != SCObjects.astralPillarCap.blockID
                                    )
                            {
                                if(this.worldObj.rand.nextInt(100) <= 30)
                                {
                                    FXLightningBolt bolt = new FXLightningBolt(this.getWorldObj(), xCoord + 0.5, yCoord + 0.75, zCoord + 0.5, x + 0.5, y + 0.5, z + 0.5, worldObj.rand.nextLong(), (2), 10F, 2);
                                    
                                    bolt.setBlendMode(771, 771);
                                    bolt.setMultiplier(5f / bolt.getLength());
                                    bolt.setWidth(0.025f);
                                    bolt.setColours(0x7D0552, 0x777777);
                                    bolt.defaultFractal();
                                    bolt.finalizeBolt();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private boolean checkValidity()
    {
        return this.worldObj.getBlockId(xCoord, yCoord - 3, zCoord) == SCObjects.astralPillarBase.blockID &&
                this.worldObj.getBlockTileEntity(xCoord, yCoord - 3, zCoord) instanceof TileAstralPillarBase &&
                worldObj.getBlockId(xCoord, yCoord - 2, zCoord) == SCObjects.astralPillarStructure.blockID &&
                worldObj.getBlockId(xCoord, yCoord - 1, zCoord) == SCObjects.astralPillarStructure.blockID;
    }
}
