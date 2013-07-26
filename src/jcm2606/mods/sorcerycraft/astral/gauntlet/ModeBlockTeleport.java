package jcm2606.mods.sorcerycraft.astral.gauntlet;

import java.util.List;

import jcm2606.mods.jccore.helper.NBTHelper;
import jcm2606.mods.jccore.util.Coord;
import jcm2606.mods.sorcerycraft.api.AstralManager;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.EnumUseType;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.GauntletMode;
import jcm2606.mods.sorcerycraft.core.SCParticle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModeBlockTeleport extends GauntletMode {
    public final String NBT_TAG_STORED_X_COORD = "storedXCoord";
    public final String NBT_TAG_STORED_Y_COORD = "storedYCoord";
    public final String NBT_TAG_STORED_Z_COORD = "storedZCoord";
    public final String NBT_TAG_HAS_STORED_COORDS = "hasStoredCoords";
    
    public ModeBlockTeleport() {
        super(AstralManager.getNextAvailableId(), "Block Teleportation", "");
    }

    @Override
    public boolean onUse(EnumUseType type, ItemStack stack, World world, EntityPlayer player, EntityLivingBase living, int x, int y, int z, int side)
    {
        if(type.equals(EnumUseType.BLOCK_RIGHT_CLICK))
        {
            if(!getHasStoredCoords(stack))
            {
                if(world.blockHasTileEntity(x, y, z))
                {
                    if(world.isRemote)
                    {
                        player.addChatMessage("\247oThis block has extra energy bound to it. You cannot move this block.");
                    }
                    return false;
                }
                
                setStoredXCoord(stack, x);
                setStoredYCoord(stack, y);
                setStoredZCoord(stack, z);
                setHasStoredCoords(stack, true);
                world.playSoundEffect(x, y, z, "random.orb", 0.5f, 1.0f);
                
                return true;
            } else {
                int storedX = getStoredXCoord(stack);
                int storedY = getStoredYCoord(stack);
                int storedZ = getStoredZCoord(stack);
                int portalParticleMultiplier = 1;
                String particle = "astralEnergy";
                
                int age = 50;
                
                switch(side)
                {
                    case 5 : {
                        if(world.blockExists(storedX, storedY, storedZ))
                        {
                            int id = world.getBlockId(storedX, storedY, storedZ);
                            world.setBlock(x + 1, y, z, id, world.getBlockMetadata(storedX, storedY, storedZ), 0x02);
                            world.setBlock(storedX, storedY, storedZ, 0);
                            
                            if(!world.isRemote)
                            {
                                for(int i = 0; i < portalParticleMultiplier; i++)
                                {
                                    float var7 = x + 1 - storedX;
                                    float var9 = y - storedY;
                                    float var11 = z - storedZ;
                                    int distance = (int)MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
                                    
                                    SCParticle.spawnAstralEnergyBeamFX(new Coord(storedX + 0.5, storedY + 0.5, storedZ + 0.5), new Coord(x + 1 + 0.5, y + 0.5, z + 0.5), age, true, false, false);
                                }
                                
                                SCParticle.spawnAstralEnergyFX(storedX + 0.5, storedY + 0.5, storedZ + 0.5, storedX + 0.5, storedY + 0.5, storedZ + 0.5, age, true, false, false, false);
                            }
                            
                            world.playSoundEffect(x + 1, y, z, "mob.endermen.portal", 0.5f, 1.0f);
                        } else {
                            player.addChatMessage("\247oThere is no block at the origin.");
                        }
                    } break;
                    
                    case 2 : {
                        if(world.blockExists(storedX, storedY, storedZ))
                        {
                            int id = world.getBlockId(storedX, storedY, storedZ);
                            world.setBlock(x, y, z - 1, id, world.getBlockMetadata(storedX, storedY, storedZ), 0x02);
                            world.setBlock(storedX, storedY, storedZ, 0);
                            
                            if(!world.isRemote)
                            {
                                for(int i = 0; i < portalParticleMultiplier; i++)
                                {
                                    float var7 = x - storedX;
                                    float var9 = y - storedY;
                                    float var11 = z - 1 - storedZ;
                                    int distance = (int)MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
                                    
                                    SCParticle.spawnAstralEnergyBeamFX(new Coord(storedX + 0.5, storedY + 0.5, storedZ + 0.5), new Coord(x + 0.5, y + 0.5, z - 1 + 0.5), age, true, false, false);
                                }
                                
                                SCParticle.spawnAstralEnergyFX(storedX + 0.5, storedY + 0.5, storedZ + 0.5, storedX + 0.5, storedY + 0.5, storedZ + 0.5, age, true, false, false, false);
                            }
                            
                            world.playSoundEffect(x, y, z - 1, "mob.endermen.portal", 0.5f, 1.0f);
                        } else {
                            player.addChatMessage("\247oThere is no block at the origin.");
                        }
                    } break;
                    
                    case 4 : {
                        if(world.blockExists(storedX, storedY, storedZ))
                        {
                            int id = world.getBlockId(storedX, storedY, storedZ);
                            world.setBlock(x - 1, y, z, id, world.getBlockMetadata(storedX, storedY, storedZ), 0x02);
                            world.setBlock(storedX, storedY, storedZ, 0);
                            
                            if(!world.isRemote)
                            {
                                for(int i = 0; i < portalParticleMultiplier; i++)
                                {
                                    float var7 = x - 1 - storedX;
                                    float var9 = y - storedY;
                                    float var11 = z - storedZ;
                                    int distance = (int)MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
                                    
                                    SCParticle.spawnAstralEnergyBeamFX(new Coord(storedX + 0.5, storedY + 0.5, storedZ + 0.5), new Coord(x - 1 + 0.5, y + 0.5, z + 0.5), age, true, false, false);
                                }
                                
                                SCParticle.spawnAstralEnergyFX(storedX + 0.5, storedY + 0.5, storedZ + 0.5, storedX + 0.5, storedY + 0.5, storedZ + 0.5, age, true, false, false, false);
                            }
                            
                            world.playSoundEffect(x - 1, y, z, "mob.endermen.portal", 0.5f, 1.0f);
                        } else {
                            player.addChatMessage("\247oThere is no block at the origin.");
                        }
                    } break;
                    
                    case 3 : {
                        if(world.blockExists(storedX, storedY, storedZ))
                        {
                            int id = world.getBlockId(storedX, storedY, storedZ);
                            world.setBlock(x, y, z + 1, id, world.getBlockMetadata(storedX, storedY, storedZ), 0x02);
                            world.setBlock(storedX, storedY, storedZ, 0);
                            
                            if(!world.isRemote)
                            {
                                for(int i = 0; i < portalParticleMultiplier; i++)
                                {
                                    float var7 = x - storedX;
                                    float var9 = y - storedY;
                                    float var11 = z + 1 - storedZ;
                                    int distance = (int)MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
                                    
                                    SCParticle.spawnAstralEnergyBeamFX(new Coord(storedX + 0.5, storedY + 0.5, storedZ + 0.5), new Coord(x + 0.5, y + 0.5, z + 1 + 0.5), age, true, false, false);
                                }
                                
                                SCParticle.spawnAstralEnergyFX(storedX + 0.5, storedY + 0.5, storedZ + 0.5, storedX + 0.5, storedY + 0.5, storedZ + 0.5, age, true, false, false, false);
                            }
                            
                            world.playSoundEffect(x, y, z + 1, "mob.endermen.portal", 0.5f, 1.0f);
                        } else {
                            player.addChatMessage("\247oThere is no block at the origin.");
                        }
                    } break;
                    
                    case 1 : {
                        if(world.blockExists(storedX, storedY, storedZ))
                        {
                            int id = world.getBlockId(storedX, storedY, storedZ);
                            world.setBlock(x, y + 1, z, id, world.getBlockMetadata(storedX, storedY, storedZ), 0x02);
                            world.setBlock(storedX, storedY, storedZ, 0);
                            
                            if(!world.isRemote)
                            {
                                for(int i = 0; i < portalParticleMultiplier; i++)
                                {
                                    float var7 = x - storedX;
                                    float var9 = y + 1 - storedY;
                                    float var11 = z - storedZ;
                                    int distance = (int)MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
                                    
                                    SCParticle.spawnAstralEnergyBeamFX(new Coord(storedX + 0.5, storedY + 0.5, storedZ + 0.5), new Coord(x + 0.5, y + 1 + 0.5, z + 0.5), age, true, false, false);
                                }
                                
                                SCParticle.spawnAstralEnergyFX(storedX + 0.5, storedY + 0.5, storedZ + 0.5, storedX + 0.5, storedY + 0.5, storedZ + 0.5, age, true, false, false, false);
                            }
                            
                            world.playSoundEffect(x, y + 1, z, "mob.endermen.portal", 0.5f, 1.0f);
                        } else {
                            player.addChatMessage("\247oThere is no block at the origin.");
                        }
                    } break;
                    
                    case 0 : {
                        if(world.blockExists(storedX, storedY, storedZ))
                        {
                            int id = world.getBlockId(storedX, storedY, storedZ);
                            world.setBlock(x, y - 1, z, id, world.getBlockMetadata(storedX, storedY, storedZ), 0x02);
                            world.setBlock(storedX, storedY, storedZ, 0);
                            
                            if(!world.isRemote)
                            {
                                for(int i = 0; i < portalParticleMultiplier; i++)
                                {
                                    float var7 = x - storedX;
                                    float var9 = y - 1 - storedY;
                                    float var11 = z - storedZ;
                                    int distance = (int)MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
                                    
                                    SCParticle.spawnAstralEnergyBeamFX(new Coord(storedX + 0.5, storedY + 0.5, storedZ + 0.5), new Coord(x + 0.5, y - 1 + 0.5, z + 0.5), age, true, false, false);
                                }
                                
                                SCParticle.spawnAstralEnergyFX(storedX + 0.5, storedY + 0.5, storedZ + 0.5, storedX + 0.5, storedY + 0.5, storedZ + 0.5, age, true, false, false, false);
                            }
                            
                            world.playSoundEffect(x, y - 1, z, "mob.endermen.portal", 0.5f, 1.0f);
                        } else {
                            player.addChatMessage("\247oThere is no block at the origin.");
                        }
                    } break;
                }
                
                setHasStoredCoords(stack, false);
                
                this.useEnergy(player, type);
                
                return true;
            }
        }
        
        return false;
    }
    
    public void setHasStoredCoords(ItemStack stack, boolean flag)
    {
        NBTHelper.setBoolean(NBTHelper.getNBTCompoundForItemStack(stack), NBT_TAG_HAS_STORED_COORDS, flag);
    }
    
    public boolean getHasStoredCoords(ItemStack stack)
    {
        return NBTHelper.getBoolean(NBTHelper.getNBTCompoundForItemStack(stack), NBT_TAG_HAS_STORED_COORDS);
    }
    
    public void setStoredXCoord(ItemStack stack, int x)
    {
        NBTHelper.setInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_TAG_STORED_X_COORD, x);
    }

    public void setStoredYCoord(ItemStack stack, int y)
    {
        NBTHelper.setInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_TAG_STORED_Y_COORD, y);
    }
    
    public void setStoredZCoord(ItemStack stack, int z)
    {
        NBTHelper.setInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_TAG_STORED_Z_COORD, z);
    }
    
    public int getStoredXCoord(ItemStack stack)
    {
        return NBTHelper.getInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_TAG_STORED_X_COORD);
    }

    public int getStoredYCoord(ItemStack stack)
    {
        return NBTHelper.getInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_TAG_STORED_Y_COORD);
    }
    
    public int getStoredZCoord(ItemStack stack)
    {
        return NBTHelper.getInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_TAG_STORED_Z_COORD);
    }

    @Override
    public void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem)
    {}

    @Override
    public int energyRequired(EnumUseType type, EntityPlayer player)
    {
        if(type.equals(EnumUseType.BLOCK_RIGHT_CLICK))
        {
            return 10;
        }
        
        return 0;
    }

    @Override
    public void addInfoToItemMouseover(EntityPlayer player, ItemStack stack, boolean isSneaking, List list)
    {
        list.add("Stored coordinates: ");
        
        list.add(" " + this.getStoredXCoord(stack) + ", " + this.getStoredYCoord(stack) + ", " + this.getStoredZCoord(stack));
        
        list.add("Coordinates valid: ");
        
        if(player.worldObj.blockExists(this.getStoredXCoord(stack), this.getStoredYCoord(stack), this.getStoredZCoord(stack)))
        {
            list.add(" True");
        } else {
            list.add(" False");
        }
    }
}