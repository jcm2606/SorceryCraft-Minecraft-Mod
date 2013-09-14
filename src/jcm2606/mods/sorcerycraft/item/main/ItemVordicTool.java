package jcm2606.mods.sorcerycraft.item.main;

import jcm2606.mods.sorcerycraft.core.handler.ToolHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemVordicTool extends ItemVordicDevice
{
    public ItemVordicTool(int par1)
    {
        super(par1, "toolVordic");
        this.setMaxDamage(128);
    }
    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
        ToolHandler.performBlockWorking(stack, player, world, x, y, z);
        return true;
    }
}
