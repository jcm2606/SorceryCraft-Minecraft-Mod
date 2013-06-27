package jcm2606.mods.sorcerycraft.item.special;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.SCParticle;
import jcm2606.mods.sorcerycraft.compat.CompatContainerSC;
import jcm2606.mods.sorcerycraft.compat.HandlerMethodID;
import jcm2606.mods.sorcerycraft.config.Settings;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;

public class ItemRingMagma extends SCItem {
    public ItemRingMagma(int par1) {
        super(par1, "ringMagma");
        this.setMaxStackSize(1);
        this.setMaxDamage(820);
    }

    @Override
    public boolean shouldRotateAroundWhenRendering()
    {
        return false;
    }

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps
     * to check if is on a player hand and update it's contents.
     */
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean currentItem)
    {
        if (entity instanceof EntityLiving) {
            if ((EntityLiving) entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) (EntityLiving) entity;

                if (player.isInsideOfMaterial(Material.lava)) {
                    if (world.rand.nextInt(100) <= 5) {
                        stack.damageItem(-1, player);
                    }
                }
            }
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.ADVANCED);
    }

    /**
     * Callback for item usage. If the item does something special on right
     * clicking, he will have one of those. Return True if something happen and
     * false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        /*
         * for(int ax = 0; ax < 3; ax++) {mm for(int ay = 0; ay < 3; ay++) {
         * for(int az = 0; az < 3; az++) { int xCoord = x - 1 + ax; int yCoord =
         * y - 1 + ay; int zCoord = z - 1 + az;
         * 
         * 
         * } } }
         */

        int id = world.getBlockId(x, y, z);

        if (player.isSneaking()) {
            attemptSmeltingAt(world, player, stack, x, y, z, id);
            world.playSound(x, y, z, "mob.ghast.fireball", 0.5f, 1.0f, false);
            CompatContainerSC.postUpdateToSubContainers(HandlerMethodID.RING_MAGMA_USE, null);
            return true;
        }

        int r = 8;

        if ((side == 1) || (side == 0)) {
            for (int i = -r; i <= r; i++) {
                for (int n = -r; n <= r; n++) {
                    int xCur = x + i;
                    int zCur = z + n;
                    int yCur = y;

                    if (stack.getItemDamage() >= stack.getMaxDamage()) {
                        stack.damageItem(1, player);
                        break;
                    }

                    attemptSmeltingAt(world, player, stack, xCur, yCur, zCur, id);
                }
            }

            CompatContainerSC.postUpdateToSubContainers(HandlerMethodID.RING_MAGMA_USE, null);
        } else
            if ((side == 2) || (side == 3)) {
                for (int i = -r; i <= r; i++) {
                    for (int n = -r; n <= r; n++) {
                        int xCur = x + i;
                        int zCur = z;
                        int yCur = y + n;

                        if (stack.getItemDamage() >= stack.getMaxDamage()) {
                            stack.damageItem(1, player);
                            break;
                        }

                        attemptSmeltingAt(world, player, stack, xCur, yCur, zCur, id);
                    }
                }

                CompatContainerSC.postUpdateToSubContainers(HandlerMethodID.RING_MAGMA_USE, null);
            } else
                if ((side == 4) || (side == 5)) {
                    for (int i = -r; i <= r; i++) {
                        for (int n = -r; n <= r; n++) {
                            int xCur = x;
                            int zCur = z + i;
                            int yCur = y + n;

                            if (stack.getItemDamage() >= stack.getMaxDamage()) {
                                stack.damageItem(1, player);
                                break;
                            }

                            attemptSmeltingAt(world, player, stack, xCur, yCur, zCur, id);
                        }
                    }

                    CompatContainerSC.postUpdateToSubContainers(HandlerMethodID.RING_MAGMA_USE, null);
                }

        world.playSound(x, y, z, "mob.ghast.fireball", 1f, 1.0f, false);
        return true;
    }

    public void attemptSmeltingAt(World world, EntityPlayer player, ItemStack stack, int x, int y, int z, int idR)
    {
        int id = world.getBlockId(x, y, z);

        if (id == 0) {
            return;
        }

        if (id != idR) {
            return;
        }

        ItemStack smelted = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(Block.blocksList[id]));

        if (smelted == null) {
            return;
        }

        if (!world.isRemote) {
            if (smelted.getItem() instanceof ItemBlock) {
                world.setBlock(x, y, z, smelted.getItem().itemID);
            } else {
                player.entityDropItem(new ItemStack(smelted.getItem().itemID, 1, smelted.getItemDamage()), 0.0F);
                world.setBlock(x, y, z, 0);
            }
        }

        for (int i = 0; i < 32; i++) {
            world.spawnParticle("smoke", x + 0.5 - (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3), z + 0.5 - (world.rand
                    .nextGaussian() / 3), 0, 0, 0);
            world.spawnParticle("smoke", x + 0.5 + (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3), z + 0.5 + (world.rand
                    .nextGaussian() / 3), 0, 0, 0);
        }

        for (int i = 0; i < 8; i++) {
            if (Minecraft.isFancyGraphicsEnabled() || Settings.RING_MAGMA_FANCY_PARTICLES) {
                SCParticle.spawnSearFlameFX(x + 0.5 - (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3),
                        z + 0.5 - (world.rand.nextGaussian() / 3), 0, 0.001, 0, false);
                SCParticle.spawnSearFlameFX(x + 0.5 + (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3),
                        z + 0.5 + (world.rand.nextGaussian() / 3), 0, 0.001, 0, false);
            } else {
                world.spawnParticle("flame", x + 0.5 - (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3),
                        z + 0.5 - (world.rand.nextGaussian() / 3), 0, 0, 0);
                world.spawnParticle("flame", x + 0.5 + (world.rand.nextGaussian() / 3), y + 0.5 + (world.rand.nextGaussian() / 3),
                        z + 0.5 + (world.rand.nextGaussian() / 3), 0, 0, 0);
            }
        }

        stack.damageItem(1, player);
    }
}
