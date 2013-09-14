package jcm2606.mods.sorcerycraft.api;

import jcm2606.mods.sorcerycraft.api.astral.gauntlet.EnumUseType;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.GauntletMode;
import jcm2606.mods.sorcerycraft.api.compat.CompatContainerSC;
import jcm2606.mods.sorcerycraft.api.compat.HandlerMethodID;
import jcm2606.mods.sorcerycraft.astral.gauntlet.ModeBlockTeleport;
import jcm2606.mods.sorcerycraft.astral.gauntlet.ModeEnergyGather;
import jcm2606.mods.sorcerycraft.astral.gauntlet.ModeManualEmpowerment;
import jcm2606.mods.sorcerycraft.astral.gauntlet.ModePyrokenisis;
import jcm2606.mods.sorcerycraft.astral.gauntlet.ModeTeleport;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketDrawAstralEnergyBeam;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyCell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;

public class AstralManager
{
    public static final int ENERGY_MAX = 4000;
    public static GauntletMode[] modeList = new GauntletMode[256];
    
    public static void chargeCellsInInvFromBlocks(ItemStack stack, EntityPlayer player, World world)
    {
        chargeCellsInInvFromBlocks(stack, player, world, 9, 5, 9);
    }
    
    public static void chargeCellsInInvFromBlocks(ItemStack stack, EntityPlayer player, World world, int xScanDistance, int yScanDistance,
            int zScanDistance)
    {
        for (int xC = 0; xC < xScanDistance; xC++)
        {
            for (int yC = 0; yC < yScanDistance; yC++)
            {
                for (int zC = 0; zC < zScanDistance; zC++)
                {
                    int xCoord = (int) (player.posX - (xScanDistance / 2) + xC);
                    int yCoord = (int) (player.posY - (yScanDistance / 2) + yC);
                    int zCoord = (int) (player.posZ - (zScanDistance / 2) + zC);
                    
                    if (world.getBlockId(xCoord, yCoord, zCoord) == SCObjects.oreastral.blockID || world.getBlockId(xCoord, yCoord, zCoord) == SCObjects.astralenergygate.blockID)
                    {
                        if (player.inventory.hasItem(SCObjects.astralenergycell.itemID))
                        {
                            for (int i = 0; i < player.inventory.mainInventory.length; i++)
                            {
                                ItemStack stack2 = player.inventory.mainInventory[i];
                                
                                if (stack2 != null)
                                {
                                    if (stack2.getItem() == SCObjects.astralenergycell)
                                    {
                                        ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack2.getItem();
                                        
                                        if (cell.getEnergy(stack2) > 0)
                                        {
                                            if (getTotalEnergyForPlayer(player) < ENERGY_MAX)
                                            {
                                                cell.setEnergy(stack2, cell.getEnergy(stack2) - 1);
                                                CompatContainerSC.postUpdate(HandlerMethodID.ASTRAL_ENERGY_GATHER_BLOCK, null);
                                                
                                                float var7 = (float) (player.posX - xCoord);
                                                float var9 = (float) (player.posY + 2.0 - yCoord);
                                                float var11 = (float) (player.posZ - zCoord);
                                                int distance = (int) MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
                                                
                                                double adjAngle = 5.0D;
                                                double dist = 0.2D;
                                                
                                                EntityPlayer center = player;
                                                
                                                double posX = center.posX - Math.cos((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
                                                double posY = center.posY - Math.sin(center.rotationPitch / 540.0F * Math.PI) * dist;
                                                double posZ = center.posZ + Math.sin((-center.rotationYaw + adjAngle) * 0.01745329D) * dist;
                                                
                                                if (world.isRemote)
                                                {
                                                    PacketDispatcher.sendPacketToAllPlayers(PacketType.populatePacket(new PacketDrawAstralEnergyBeam(
                                                            xCoord + 0.5, yCoord + 0.5, zCoord + 0.5, posX, posY, posZ, 2, true),
                                                            PacketHandler.CHANNEL_SC));
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
    
    public static void setChargeForCellsInInv(EntityPlayer player, int charge)
    {
        if (player.inventory.hasItem(SCObjects.astralenergycell.itemID))
        {
            for (int i = 0; i < player.inventory.mainInventory.length; i++)
            {
                ItemStack stack2 = player.inventory.mainInventory[i];
                
                if (stack2 != null)
                {
                    if (stack2.getItem() == SCObjects.astralenergycell)
                    {
                        ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack2.getItem();
                        
                        if (cell.getEnergy(stack2) > 0)
                        {
                            if (getTotalEnergyForPlayer(player) < ENERGY_MAX)
                            {
                                cell.setEnergy(stack2, charge);
                                
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public static int getTotalEnergyForPlayer(EntityPlayer player)
    {
        int currentEnergy = 0;
        
        for (int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if (stack != null)
            {
                if (stack.getItem() == SCObjects.astralenergycell)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack.getItem();
                    
                    currentEnergy = currentEnergy + (1000 - cell.getEnergy(stack));
                }
            }
        }
        
        return currentEnergy;
    }
    
    public static int getEnergyForPlayer(EntityPlayer player)
    {
        int currentEnergy = 0;
        
        for (int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if (stack != null)
            {
                if (stack.getItem() == SCObjects.astralenergycell)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack.getItem();
                    
                    currentEnergy = currentEnergy + cell.getEnergy(stack);
                }
            }
        }
        
        return currentEnergy;
    }
    
    public static int getAmountOfChargedCellsOnPlayer(EntityPlayer player)
    {
        int fullCellsInInv = 0;
        
        for (int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if (stack != null)
            {
                if (stack.getItem() == SCObjects.astralenergycell)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack.getItem();
                    
                    if (cell.getEnergy(stack) < 1000)
                    {
                        fullCellsInInv++;
                    }
                }
            }
        }
        
        return fullCellsInInv;
    }
    
    public static int getAmountOfCellsOnPlayer(EntityPlayer player)
    {
        int cellsInInv = 0;
        
        for (int i = 0; i < player.inventory.mainInventory.length; i++)
        {
            ItemStack stack = player.inventory.mainInventory[i];
            
            if (stack != null)
            {
                if (stack.getItem() == SCObjects.astralenergycell)
                {
                    ItemAstralEnergyCell cell = (ItemAstralEnergyCell) stack.getItem();
                    
                    cellsInInv++;
                }
            }
        }
        
        return cellsInInv;
    }
    
    public void loadCoreModes()
    {
        registerMode(new ModeBlockTeleport());
        registerMode(new ModeEnergyGather());
        registerMode(new ModePyrokenisis());
        // registerMode(new ModeBlockBreak());
        // registerMode(new ModeHailkenisis());
        registerMode(new ModeTeleport());
        // registerMode(new ModeHealing());
        registerMode(new ModeManualEmpowerment());
    }
    
    public static void registerMode(GauntletMode mode)
    {
        int id = mode.id;
        
        if (modeList[id] == null)
        {
            modeList[id] = mode;
        } else
        {
            throw new RuntimeException("Slot " + id + " is already occupied by mode " + modeList[id].name + " when adding mode " + mode.name + ".");
        }
    }
    
    public static GauntletMode getMode(int id)
    {
        return modeList[id];
    }
    
    public static boolean useGauntlet(EnumUseType useType, int id, ItemStack stack, EntityPlayer player, EntityLivingBase living, World world, int x,
            int y, int z, int side)
    {
        GauntletMode mode = getMode(id);
        
        if (mode.energyRequired(useType, player) == 0)
        {
            CompatContainerSC.postUpdate(HandlerMethodID.ASTRAL_GAUNTLET_MODE_USE, null);
            return mode.onUse(useType, stack, world, player, living, x, y, z, side);
        }
        
        if (mode != null)
        {
            if (getTotalEnergyForPlayer(player) >= mode.energyRequired(useType, player))
            {
                if (mode.onUse(useType, stack, world, player, living, x, y, z, side))
                {
                    CompatContainerSC.postUpdate(HandlerMethodID.ASTRAL_GAUNTLET_MODE_USE, null);
                    
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static void onGauntletUpdateTick(int id, ItemStack stack, Entity entity, World world, int slot, boolean isCurrentItem)
    {
        GauntletMode mode = getMode(id);
        
        if (mode != null)
        {
            mode.onGauntletItemUpdateTick(stack, (EntityPlayer) (EntityLivingBase) entity, world, slot, isCurrentItem);
        }
    }
    
    public static int getNextAvailableId()
    {
        if (modeList[255] != null)
        {
            throw new RuntimeException("No more available mode ids.");
        }
        
        for (int i = 0; i < 256; i++)
        {
            if (modeList[i] == null)
            {
                return i;
            }
        }
        
        return 0;
    }
}
