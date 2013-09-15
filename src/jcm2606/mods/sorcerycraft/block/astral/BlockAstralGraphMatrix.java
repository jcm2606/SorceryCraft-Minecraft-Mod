package jcm2606.mods.sorcerycraft.block.astral;

import jcm2606.mods.jccore.core.util.ConvertUtil;
import jcm2606.mods.jccore.core.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.energy.IEnergyCapacitor;
import jcm2606.mods.sorcerycraft.block.SCBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAstralGraphMatrix extends SCBlock
{
    public BlockAstralGraphMatrix(int par1)
    {
        super(par1, Material.iron, "astralGraphMatrix");
        this.setHardness(3.5f);
        this.setResistance(1.5f);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconBuffer = new Icon[2];
        this.iconBuffer[0] = par1IconRegister.registerIcon("SorceryCraft:" + name);
        this.iconBuffer[1] = par1IconRegister.registerIcon("SorceryCraft:" + name + "Side");
    }
    
    @Override
    public Icon getIcon(int blockSide, int par2)
    {
        return blockSide == 0 || blockSide == 1 ? iconBuffer[0] : iconBuffer[1];
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS)
        {
            if (GeneralUtil.doesNeighbourBlockExist(x, y, z, direction, world))
            {
                if (GeneralUtil.getBlockTileFromNeighbour(x, y, z, direction, player.worldObj) != null)
                {
                    if (GeneralUtil.getBlockTileFromNeighbour(x, y, z, direction, player.worldObj) instanceof IEnergyCapacitor)
                    {
                        IEnergyCapacitor capacitor = (IEnergyCapacitor) GeneralUtil.getBlockTileFromNeighbour(x, y, z, direction, player.worldObj);
                        
                        if (world.isRemote)
                        {
                            player.sendChatToPlayer(ConvertUtil.getChatMessageComponent(capacitor.getEnergyStored() + " [" + direction.name() + "]"));
                        }
                    }
                }
            }
        }
        
        return true;
    }
}
