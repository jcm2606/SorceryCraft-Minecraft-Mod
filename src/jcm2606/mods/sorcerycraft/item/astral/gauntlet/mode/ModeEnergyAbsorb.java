package jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode;

import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.SCParticle;
import jcm2606.mods.sorcerycraft.item.astral.ItemAstralEnergyCell;
import jcm2606.mods.sorcerycraft.item.astral.gauntlet.AstralGauntletManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModeEnergyAbsorb extends GauntletMode {
    public ModeEnergyAbsorb() {
        super(AstralGauntletManager.getNextAvailableId(), "Energy Absorption", "");
        this.useAction = EnumAction.bow;
        this.useActionDuration = 72000;
        this.hasItemUse = true;
    }
    
    @Override
    public boolean onUse(String type, ItemStack stack, World world, EntityPlayer player, EntityLiving living, int x, int y, int z, int side)
    {
        if(type.equals(this.USE_ITEM_USE))
        {
            for(int xC = 0; xC < 9; xC++)
            {
                for(int yC = 0; yC < 5; yC++)
                {
                    for(int zC = 0; zC < 9; zC++)
                    {
                        int xCoord = (int) (player.posX - 5 + xC);
                        int yCoord = (int) (player.posY - 3 + yC);
                        int zCoord = (int) (player.posZ - 5 + zC);
                        
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
                                                    float var9 = (float) (player.posY - 1 - yCoord);
                                                    float var11 = (float) (player.posZ - zCoord);
                                                    int distance = (int)MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
                                                    
                                                    SCParticle.spawnAstralEnergyFX(xCoord + 0.5, yCoord - 0.5, zCoord + 0.5, player.posX, player.posY, player.posZ, false, distance, false);
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
        
        return false;
    }

    @Override
    public void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem)
    {}

    @Override
    public int energyRequired(String type, EntityPlayer player)
    {
        return 0;
    }
}
