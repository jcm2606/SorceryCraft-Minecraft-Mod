package jcm2606.mods.sorcerycraft.item.astral;

import jcm2606.mods.jccore.helper.NBTHelper;
import jcm2606.mods.jccore.util.Coord;
import jcm2606.mods.sorcerycraft.block.tile.energy.TileEntityWirelessLink;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAstralEnergyNetworkLinkCreator extends SCItem {
    public final String NBT_COORD_X = "coordX";
    public final String NBT_COORD_Y = "coordY";
    public final String NBT_COORD_Z = "coordZ";
    public final String NBT_HAS_COORDS = "hasCoords";
    
    public ItemAstralEnergyNetworkLinkCreator(int par1) {
        super(par1, "astralEnergyNetworkLinkCreator");
        this.setMaxStackSize(1);
    }
    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
            int x, int y, int z, int par7, float par8, float par9, float par10) {
        if(!this.getHasCoords(stack))
        {
            this.setCoords(stack, new Coord(x, y, z));
            this.setHasCoords(stack, true);
            player.addChatMessage("\247oCoordinates saved.");
        } else {
            if(world.getBlockTileEntity(x, y, z) instanceof TileEntityWirelessLink)
            {
                TileEntityWirelessLink link = (TileEntityWirelessLink) world.getBlockTileEntity(x, y, z);
                
                if(this.getHasCoords(stack))
                {
                    link.connectedCoordSet = this.getCoords(stack);
                    player.addChatMessage("\247oBlock connection made.");
                    this.setHasCoords(stack, false);
                } else {
                    player.addChatMessage("\247oYou must set the coordinates for this block to use first.");
                }
            }
        }
        
        return false;
    }
    
    public void setCoords(ItemStack stack, Coord coord)
    {
        NBTHelper.setInt(NBTHelper.getNBTCompoundForItemStack(stack), this.NBT_COORD_X, (int) coord.x);
        NBTHelper.setInt(NBTHelper.getNBTCompoundForItemStack(stack), this.NBT_COORD_Y, (int) coord.y);
        NBTHelper.setInt(NBTHelper.getNBTCompoundForItemStack(stack), this.NBT_COORD_Z, (int) coord.z);
    }
    
    public Coord getCoords(ItemStack stack)
    {
        return new Coord(NBTHelper.getInt(NBTHelper.getNBTCompoundForItemStack(stack), this.NBT_COORD_X), NBTHelper.getInt(NBTHelper.getNBTCompoundForItemStack(stack), this.NBT_COORD_Y), NBTHelper.getInt(NBTHelper.getNBTCompoundForItemStack(stack), this.NBT_COORD_Z));
    }
    
    public void setHasCoords(ItemStack stack, boolean b)
    {
        NBTHelper.setBoolean(NBTHelper.getNBTCompoundForItemStack(stack), this.NBT_HAS_COORDS, b);
    }
    
    public boolean getHasCoords(ItemStack stack)
    {
        return NBTHelper.getBoolean(NBTHelper.getNBTCompoundForItemStack(stack), this.NBT_HAS_COORDS);
    }
}
