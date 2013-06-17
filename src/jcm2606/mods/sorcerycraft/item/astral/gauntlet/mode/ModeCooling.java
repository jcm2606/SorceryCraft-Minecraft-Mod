package jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode;

import java.util.HashMap;
import java.util.List;

import jcm2606.mods.sorcerycraft.item.astral.gauntlet.AstralGauntletManager;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModeCooling extends GauntletMode {
    public static HashMap<Block, Block> coolingList = new HashMap<Block, Block>();
    
    public ModeCooling() {
        super(AstralGauntletManager.getNextAvailableId(), "Subzero Cooling", "");
    }
    
    public static void loadCoolingEntries()
    {
        coolingList.put(Block.stone, Block.cobblestone);
    }

    @Override
    public boolean onUse(String type, ItemStack stack, World world, EntityPlayer player, EntityLiving living, int x, int y, int z, int side)
    {
        if(type.equals(this.USE_BLOCK_ACTIVATE))
        {
            if(coolingList.get(Block.blocksList[world.getBlockId(x, y, z)]) != null)
            {
                world.setBlock(x, y, z, coolingList.get(Block.blocksList[world.getBlockId(x, y, z)]).blockID);
                
                return true;
            }
        }
        
        return false;
    }

    @Override
    public void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem)
    {}

    @Override
    public int energyRequired(String type, EntityPlayer player)
    {
        if(type.equals(this.USE_BLOCK_ACTIVATE))
        {
            return 100;
        }
        
        return 0;
    }

    @Override
    public void addInfoToItemMouseover(EntityPlayer player, ItemStack stack, boolean isSneaking, List list)
    {}
}
