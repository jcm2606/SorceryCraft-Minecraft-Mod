package jcm2606.mods.sorcerycraft.astral;

import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.compat.CompatContainerSC;
import jcm2606.mods.sorcerycraft.compat.HandlerMethodID;
import jcm2606.mods.sorcerycraft.fx.FXAstralEnergyBeam;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyCell;
import net.minecraft.client.Minecraft;
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
                    
                    if(world.getBlockId(xCoord, yCoord, zCoord) == SCObjects.oreastral.blockID || world.getBlockId(xCoord, yCoord, zCoord) == SCObjects.astralenergygate.blockID)
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
                                            CompatContainerSC.postUpdateToSubContainers(HandlerMethodID.ASTRAL_ENERGY_GATHER_BLOCK, null);
                                            
                                            if(world.isRemote)
                                            {
                                                float var7 = (float) (player.posX - xCoord);
                                                float var9 = (float) (player.posY - yCoord);
                                                float var11 = (float) (player.posZ - zCoord);
                                                int distance = (int)MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
                                                
                                                double adjAngle = 5.0D;
                                                double dist = 0.2D;
                                            
                                                EntityPlayer center = player;
                                            
                                                double posX = center.posX - Math.cos((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
                                                double posY = center.posY - Math.sin(center.rotationPitch / 540.0F * Math.PI) * dist;
                                                double posZ = center.posZ + Math.sin((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
                                                
                                                FXAstralEnergyBeam fx = new FXAstralEnergyBeam(world, xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, posX, posY, posZ, 2);
                                                
                                                Minecraft.getMinecraft().effectRenderer.addEffect(fx);
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
    
    public static void setChargeForCellsInInv(EntityPlayer player, int charge)
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
                            cell.setEnergy(stack2, charge);
                            
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public static int getTotalEnergyForPlayer(EntityPlayer player)
    {
        int currentEnergy = 0;
        
        for(int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if(stack != null)
            {
                if(stack.getItem() == SCObjects.astralenergycell)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack.getItem();
                    
                    currentEnergy = currentEnergy + (1000 - cell.getEnergy(stack));
                }
            }
        }
        
        return currentEnergy;
    }
    
    public static int getAmountOfChargedCellsOnPlayer(EntityPlayer player)
    {
        int fullCellsInInv = 0;
        
        for(int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if(stack != null)
            {
                if(stack.getItem() == SCObjects.astralenergycell)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack.getItem();
                    
                    if(cell.getEnergy(stack) < 1000)
                    {
                        fullCellsInInv++;
                    }
                }
            }
        }
        
        return fullCellsInInv;
    }
}
