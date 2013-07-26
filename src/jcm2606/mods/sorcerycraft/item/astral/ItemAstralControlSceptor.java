package jcm2606.mods.sorcerycraft.item.astral;

import java.util.List;

import jcm2606.mods.jccore.helper.NBTHelper;
import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.jccore.util.Coord;
import jcm2606.mods.sorcerycraft.core.SCParticle;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCItemShine;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAstralControlSceptor extends SCItemShine {
    public final String NBT_TAG_STORED_ID = "storedID";
    public final String NBT_TAG_STORED_X_COORD = "storedXCoord";
    public final String NBT_TAG_STORED_Y_COORD = "storedYCoord";
    public final String NBT_TAG_STORED_Z_COORD = "storedZCoord";
    public final String NBT_TAG_HAS_STORED_COORDS = "hasStoredCoords";
    
    public ItemAstralControlSceptor(int par1) {
        super(par1, "astralControlSceptor");
        this.setMaxStackSize(1);
        this.setMaxDamage(512);
        this.setNoRepair();
    }
    
    /**
     * Callback for item usage. If the item does something special on right
     * clicking, he will have one of those. Return True if something happen and
     * false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world,
            int x, int y, int z, int par7, float par8, float par9, float par10) {
        if(player.isSneaking())
        {
            if(getStoredID(stack) == -1 || getStoredID(stack) == 0)
            {
                if(Block.blocksList[1].isBlockNormalCube(world, x, y, z) && !world.blockHasTileEntity(x, y, z))
                {
                    setStoredID(stack, world.getBlockId(x, y, z)); 
                    world.playSoundEffect(x, y, z, "random.orb", 0.5f, 1.0f);
                    world.setBlock(x, y, z, 0);
                    stack.damageItem(1, player);
                }
            } else {
                switch(par7)
                {
                    case 5 : {
                        world.setBlock(x + 1, y, z, getStoredID(stack), 0, 0x02);
                        setStoredID(stack, -1);
                        world.playSoundEffect(x + 1, y, z, "mob.endermen.portal", 0.5f, 1.0f);
                        stack.damageItem(1, player);
                    } break;
                    
                    case 2 : {
                        world.setBlock(x, y, z - 1, getStoredID(stack), 0, 0x02);
                        setStoredID(stack, -1);
                        world.playSoundEffect(x, y, z - 1, "mob.endermen.portal", 0.5f, 1.0f);
                        stack.damageItem(1, player);
                    } break;
                    
                    case 4 : {
                        world.setBlock(x - 1, y, z, getStoredID(stack), 0, 0x02);
                        setStoredID(stack, -1);
                        world.playSoundEffect(x - 1, y, z, "mob.endermen.portal", 0.5f, 1.0f);
                        stack.damageItem(1, player);
                    } break;
                    
                    case 3 : {
                        world.setBlock(x, y, z + 1, getStoredID(stack), 0, 0x02);
                        setStoredID(stack, -1);
                        world.playSoundEffect(x, y, z + 1, "mob.endermen.portal", 0.5f, 1.0f);
                        stack.damageItem(1, player);
                    } break;
                    
                    case 1 : {
                        world.setBlock(x, y + 1, z, getStoredID(stack), 0, 0x02);
                        setStoredID(stack, -1);
                        world.playSoundEffect(x, y, z, "mob.endermen.portal", 0.5f, 1.0f);
                        stack.damageItem(1, player);
                    } break;
                    
                    case 0 : {
                        world.setBlock(x, y - 1, z, getStoredID(stack), 0, 0x02);
                        setStoredID(stack, -1);
                        world.playSoundEffect(x, y - 1, z, "mob.endermen.portal", 0.5f, 1.0f);
                        stack.damageItem(1, player);
                    } break;
                }
            }
        } else {
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
                
                switch(par7)
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
            }
        }
            
        return false;
    }
    
    public void setStoredID(ItemStack stack, int id)
    {
        NBTHelper.setInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_TAG_STORED_ID, id);
    }
    
    public int getStoredID(ItemStack stack)
    {
        return NBTHelper.getInt(NBTHelper.getNBTCompoundForItemStack(stack), NBT_TAG_STORED_ID);
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
    @SideOnly(Side.CLIENT)
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4) {
        if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode))
        {
            if(getStoredID(stack) == -1 || getStoredID(stack) == 0)
            {
                list.add("Not holding anything.");
            } else {
                list.add("Holding " + Block.blocksList[getStoredID(stack)].getLocalizedName() + "\2477 (" + getStoredID(stack) + ").");
            }
            
            list.add("");
            
            if(!getHasStoredCoords(stack))
            {
                list.add("Not storing any placement data.");
            } else {
                list.add("Storing placement data of X: " + getStoredXCoord(stack) + " Y: " + getStoredYCoord(stack) + " Z: " + getStoredZCoord(stack));
            }
        } else {
            list.add("\247oHold <SHIFT>");
        }
        
        if(stack.getItemDamage() > 0)
        {
            list.add("Damaged (" + stack.getItemDamage() + ")");
        }
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
    }
}
