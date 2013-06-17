package jcm2606.mods.sorcerycraft.astral;

import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.SCParticle;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyCell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class AstralManager {
    
    public static void chargeCellsInInv(ItemStack stack, EntityPlayer player, World world)
    {
        chargeCellsInInv(stack, player, world, 9, 5, 9);
    }
    
    public static void chargeCellsInInv(ItemStack stack, EntityPlayer player, World world, int xScanDistance, int yScanDistance, int zScanDistance)
    {
        for(int xC = 0; xC < xScanDistance; xC++)
        {
            for(int yC = 0; yC < yScanDistance; yC++)
            {
                for(int zC = 0; zC < zScanDistance; zC++)
                {
                    int xCoord = (int) (player.posX - (xScanDistance / 2) + xC);
                    int yCoord = (int) (player.posY - (yScanDistance / 2) + yC);
                    int zCoord = (int) (player.posZ - (zScanDistance / 2) + zC);
                    
                    if(world.getBlockId(xCoord, yCoord, zCoord) == SCObjects.oreastral.blockID)
                    {
                        if(player.inventory.hasItem(SCObjects.astralenergycell.itemID))
                        {
                            for(int i = 0; i < player.inventory.mainInventory.length; i++)
                            {
                                ItemStack stack2 = player.inventory.mainInventory[i];
                                
                                if(stack2 != null)
                                {
                                    if(stack2.getItem() == SCObjects.astralenergycell)
                                    {
                                        ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack2.getItem();
                                        
                                        if(cell.getEnergy(stack2) > 0)
                                        {
                                            cell.setEnergy(stack2, cell.getEnergy(stack2) - 1);
                                            
                                            if(world.isRemote)
                                            {
                                                float var7 = (float) (player.posX - xCoord);
                                                float var9 = (float) (player.posY - yCoord);
                                                float var11 = (float) (player.posZ - zCoord);
                                                int distance = (int)MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
                                                
                                                SCParticle.spawnAstralEnergyFX(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, player.posX, player.posY - 0.2, player.posZ, distance, false);
                                            }
                                            
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
