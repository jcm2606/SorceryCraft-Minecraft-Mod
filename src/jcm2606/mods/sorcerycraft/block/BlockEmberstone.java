package jcm2606.mods.sorcerycraft.block;

import java.util.ArrayList;
import java.util.List;

import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.helper.SCHelper;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.input.Keyboard;

public class BlockEmberstone extends SCBlock {
    public BlockEmberstone(int par1) {
        super(par1, Material.rock, "emberStone", Rarities.BASIC);
        this.setHardness(1.5f);
        this.setResistance(3.0f);
    }
    
    @Override
    public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side)
    {
        return true;
    }
    
    /**
     * This returns a complete list of items dropped from this block.
     *
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @param metadata Current metadata
     * @param fortune Breakers fortune level
     * @return A ArrayList containing all items this block drops
     */
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        if(world.rand.nextInt(100) <= 30)
        {
            for(int i = 0; i < 1 + world.rand.nextInt(2); i++)
            {
                ret.add(new ItemStack(Item.gunpowder, 1, 0));
            }
            
            ret.add(new ItemStack(this.blockID, 1, damageDropped(metadata)));
        } else {
            ret.add(new ItemStack(this.blockID, 1, damageDropped(metadata)));
        }
        
        if(fortune > 0)
        {
            if(fortune == 1)
            {
                if(world.rand.nextInt(100) <= 10)
                {
                    for(int i = 0; i < 1 + world.rand.nextInt(1); i++)
                    {
                        ret.add(new ItemStack(SCObjects.firepowder, 1, 0));
                    }
                }
            }
            
            if(fortune == 2)
            {
                if(world.rand.nextInt(100) <= 25)
                {
                    for(int i = 0; i < 1 + world.rand.nextInt(1); i++)
                    {
                        ret.add(new ItemStack(SCObjects.firepowder, 1, 0));
                    }
                }
            }
            
            if(fortune == 3)
            {
                if(world.rand.nextInt(100) <= 50)
                {
                    for(int i = 0; i < 1 + world.rand.nextInt(1); i++)
                    {
                        ret.add(new ItemStack(SCObjects.firepowder, 1, 0));
                    }
                }
            }
        } else {
            if(world.rand.nextInt(100) <= 5)
            {
                for(int i = 0; i < 1 + world.rand.nextInt(1); i++)
                {
                    ret.add(new ItemStack(SCObjects.firepowder, 1, 0));
                }
            }
        }
        
        return ret;
    }
    
    @Override
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4) {
        if(SCHelper.playerHasPerceptionMedallion(player))
        {
            if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode))
            {
                list.add("A unique and special type of");
                list.add("stone which can only be naturally");
                list.add("found in the Nether. This stone");
                list.add("still smolders in the ashes of it's");
                list.add("neighbouring blocks. The Nether has");
                list.add("compressed the ashes enoughto form");
                list.add("veins of Gunpowder and occasionally");
                list.add("Sear Powder all throughout this stone.");
            } else {
                list.add("<Hold SHIFT>");
            }
        }
    }
}
