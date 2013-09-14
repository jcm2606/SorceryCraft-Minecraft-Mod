package jcm2606.mods.sorcerycraft.astral.gauntlet;

import java.util.HashMap;
import java.util.List;

import jcm2606.mods.sorcerycraft.api.AstralManager;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.EnumUseType;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.GauntletMode;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModeHailkenisis extends GauntletMode
{
    public static HashMap<Block, Block> coolingList = new HashMap<Block, Block>();
    
    public ModeHailkenisis()
    {
        super(AstralManager.getNextAvailableId(), "Hailkenisis", "");
    }
    
    public static void loadCoolingEntries()
    {
        coolingList.put(Block.stone, Block.cobblestone);
    }
    
    @Override
    public boolean onUse(EnumUseType type, ItemStack stack, World world, EntityPlayer player, EntityLivingBase living, int x, int y, int z, int side)
    {
        if (type.equals(EnumUseType.BLOCK_RIGHT_CLICK))
        {
            if (coolingList.get(Block.blocksList[world.getBlockId(x, y, z)]) != null)
            {
                world.setBlock(x, y, z, coolingList.get(Block.blocksList[world.getBlockId(x, y, z)]).blockID);
                
                this.useEnergy(player, type);
                
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem)
    {
    }
    
    @Override
    public int energyRequired(EnumUseType type, EntityPlayer player)
    {
        if (type.equals(EnumUseType.BLOCK_RIGHT_CLICK))
        {
            return 100;
        }
        
        return 0;
    }
    
    @Override
    public void addInfoToItemMouseover(EntityPlayer player, ItemStack stack, boolean isSneaking, List list)
    {
    }
}
