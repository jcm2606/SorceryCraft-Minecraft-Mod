package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.jccore.core.helper.NBTHelper;
import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.jccore.core.util.ConvertUtil;
import jcm2606.mods.jccore.core.util.Coord;
import jcm2606.mods.sorcerycraft.api.ILinkCardCoord;
import jcm2606.mods.sorcerycraft.api.ILinkable;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAstralLinkingCard extends SCItem
{
    public final String NBT_COORD_X = "x";
    public final String NBT_COORD_Y = "y";
    public final String NBT_COORD_Z = "z";
    public final String NBT_HAS_COORDS = "hasCoords";
    
    public ItemAstralLinkingCard(int par1)
    {
        super(par1, "astralLinkingCard");
        this.setMaxStackSize(1);
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        return this.hasCoords(stack);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (player.isSneaking())
        {
            this.setHasCoords(stack, false);
            
            if (!world.isRemote)
            {
                player.sendChatToPlayer(ConvertUtil.getChatMessageComponent("Stored coordinates cleared."));
            }
        }
        
        return stack;
    }
    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        if (!player.isSneaking())
        {
            if (world.getBlockTileEntity(x, y, z) != null)
            {
                if (world.getBlockTileEntity(x, y, z) instanceof ILinkable)
                {
                    if (!this.hasCoords(stack))
                    {
                        if (!world.isRemote)
                        {
                            player.sendChatToPlayer(ConvertUtil.getChatMessageComponent("Linking card does not have any stored coordinates."));
                        }
                        
                        return false;
                    }
                    
                    ((ILinkable) world.getBlockTileEntity(x, y, z)).applyCard(this, stack, player, world, x, y, z, side);
                    return true;
                } else
                {
                    if (!world.isRemote)
                    {
                        player.sendChatToPlayer(ConvertUtil
                                .getChatMessageComponent("This block does not support the linking of other blocks via astral coordinate data."));
                    }
                }
            } else
            {
                if (!world.isRemote)
                {
                    player.sendChatToPlayer(ConvertUtil
                            .getChatMessageComponent("This block does not support the linking of other blocks via astral coordinate data."));
                }
            }
        }
        
        return false;
    }
    
    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
    {
        this.setCoords(stack, new Coord(x, y, z));
        
        if (player.worldObj.getBlockTileEntity(x, y, z) != null)
        {
            if (player.worldObj.getBlockTileEntity(x, y, z) instanceof ILinkCardCoord)
            {
                if (((ILinkCardCoord) player.worldObj.getBlockTileEntity(x, y, z)).getCoords(this, stack, player, player.worldObj, x, y, z) != null)
                {
                    this.setCoords(stack, ((ILinkCardCoord) player.worldObj.getBlockTileEntity(x, y, z)).getCoords(this, stack, player,
                            player.worldObj, x, y, z));
                }
            }
        }
        
        if (!player.worldObj.isRemote)
        {
            player.sendChatToPlayer(ConvertUtil
                    .getChatMessageComponent("Stored coordinates set to " + (int) this.getCoordFromStack(stack).x + ", " + (int) this
                            .getCoordFromStack(stack).y + ", " + (int) this.getCoordFromStack(stack).z + "."));
        }
        
        return true;
    }
    
    public Coord getCoordFromStack(ItemStack stack)
    {
        return Coord.constructFromNBT(NBTHelper.getNBTCompoundForItemStack(stack));
    }
    
    public boolean hasCoords(ItemStack stack)
    {
        return NBTHelper.getBoolean(NBTHelper.getNBTCompoundForItemStack(stack), this.NBT_HAS_COORDS);
    }
    
    public void setCoords(ItemStack stack, Coord coord)
    {
        stack.stackTagCompound = Coord.constructNBTFromCoord(coord);
        this.setHasCoords(stack, true);
    }
    
    public void setHasCoords(ItemStack stack, boolean b)
    {
        NBTHelper.setBoolean(NBTHelper.getNBTCompoundForItemStack(stack), this.NBT_HAS_COORDS, b);
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        if (this.hasCoords(stack))
        {
            int id = player.worldObj.getBlockId((int) this.getCoordFromStack(stack).x, (int) this.getCoordFromStack(stack).y, (int) this
                    .getCoordFromStack(stack).z);
            
            if (id > 0)
            {
                Block block = Block.blocksList[id];
                
                if (block != null)
                {
                    list.add(block.getLocalizedName());
                }
            }
            list.add("X: " + (int) this.getCoordFromStack(stack).x);
            list.add("Y: " + (int) this.getCoordFromStack(stack).y);
            list.add("Z: " + (int) this.getCoordFromStack(stack).z);
        } else
        {
            list.add("Not holding readable data");
        }
    }
}
