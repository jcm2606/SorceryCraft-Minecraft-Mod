package jcm2606.mods.sorcerycraft.block.main;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockDarkQuartzBrickItem extends ItemBlock
{
    public final String[] types = new String[]
    { "Design 1", "Design 2" };
    
    public BlockDarkQuartzBrickItem(int par1)
    {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
    
    @Override
    public int getMetadata(int par1)
    {
        return par1;
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
    {
        list.add(this.types[(stack.getItemDamage() % 16)]);
    }
}
