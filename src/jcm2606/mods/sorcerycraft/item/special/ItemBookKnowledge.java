package jcm2606.mods.sorcerycraft.item.special;

import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import jcm2606.mods.sorcerycraft.core.lib.GuiIDs;
import jcm2606.mods.sorcerycraft.item.SCItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBookKnowledge extends SCItem
{
    public ItemBookKnowledge(int par1)
    {
        super(par1, "bookKnowledge");
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
    {
        player.openGui(SorceryCraft.instance, GuiIDs.BOOK_KNOWLEDGE, par2World, (int) player.posX, (int) player.posY, (int) player.posZ);
        
        return par1ItemStack;
    }
}
