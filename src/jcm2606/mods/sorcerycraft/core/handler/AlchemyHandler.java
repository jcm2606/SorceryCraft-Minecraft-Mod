package jcm2606.mods.sorcerycraft.core.handler;

import java.util.Random;

import jcm2606.mods.jccore.compat.container.CompatibilityContainer;
import jcm2606.mods.sorcerycraft.api.ITransmutable;
import jcm2606.mods.sorcerycraft.api.compat.HandlerMethodID;
import jcm2606.mods.sorcerycraft.client.fx.FXFissure;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Class that handles the various alchemical mechanics within SorceryCraft
 * 
 * @author jcm2606
 */
public class AlchemyHandler
{
    /**
     * Tries to perform an in-world transmutation at the given coordinates.
     * 
     * @param stack
     *            The item performing the transmutation
     * @param player
     *            The player performing the transmutation
     * @param world
     *            The world the transmutation is being performed in
     * @param block
     *            The block the transmutaton will be creating
     * @param x
     *            The X coord the transmutation is being performed at
     * @param y
     *            The Y coord the transmutation is being performed at
     * @param z
     *            The Z coord the transmutation is being performed at
     */
    public static boolean performInWorldTransmutation(ItemStack stack, EntityPlayer player, World world, int x, int y, int z)
    {
        ITransmutable i = null;
        Block block = null;
        
        for (Block var1 : Block.blocksList)
        {
            ITransmutable var2 = null;
            
            if (var1 instanceof ITransmutable)
            {
                var2 = (ITransmutable) var1;
            } else
            {
                continue;
            }
            
            block = var1;
            break;
        }
        
        if (block != null)
        {
            if (block instanceof ITransmutable)
            {
                i = (ITransmutable) block;
                
                if (i.getRequiredBlock(stack).blockID == world.getBlockId(x, y, z))
                {
                    world.setBlock(x, y, z, block.blockID, i.getMetadataToChangeTo(), 0x02);
                    i.onTransmute(stack, block, player, world, x, y, z);
                    CompatibilityContainer.postUpdate(HandlerMethodID.ALCH_STONE_TRANSMUTE, null);
                    
                    world.playSoundAtEntity(player, "sorcerycraft:transmutation", 0.3f + (world.rand.nextFloat() / 4), 1.0f);
                    
                    if (world.isRemote)
                    {
                        FXFissure fx = new FXFissure(world, x + 0.5, y + 0.5, z + 0.5, 50);
                        
                        Minecraft.getMinecraft().effectRenderer.addEffect(fx);
                    }
                    
                    Random rand = new Random();
                    
                    return true;
                }
            }
        }
        
        world.playSoundAtEntity(player, "sorcerycraft:magic_fail", 0.2f, 1.0f);
        CompatibilityContainer.postUpdate(HandlerMethodID.ALCH_STONE_TRANSMUTE_FAIL, null);
        
        return false;
    }
}
