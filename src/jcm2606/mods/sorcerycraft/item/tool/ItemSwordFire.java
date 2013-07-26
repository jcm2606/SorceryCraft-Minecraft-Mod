package jcm2606.mods.sorcerycraft.item.tool;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.item.SCSword;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ItemSwordFire extends SCSword {

    public ItemSwordFire(int par1) {
        super(par1, SCObjects.SWORD_GRIFFIN, "toolSwordFire");
        this.setNoRepair();
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.LEGENDARY);
    }

    /**
     * Current implementations of this method in child classes do not use the
     * entry argument beside ev. They just raise the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase living,
            EntityLivingBase living2)
    {
        super.hitEntity(stack, living, living2);
        if (living2 instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) living2;
        }
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world,
            EntityPlayer player)
    {
        if (stack == null) {
            return stack;
        }

        if (player.isSneaking()) {
            if (stack.getItemDamage() != 0) {
                player.setFire(5);
                stack.damageItem(-1, player);

                if (player.worldObj.isRemote) {
                    for (int i = 0; i < 8; i++) {
                        world.spawnParticle("flame",
                                player.posX + Math.random(),
                                player.posY + Math.random(),
                                player.posZ + Math.random(), 0, 0.1, 0);
                        world.spawnParticle("flame",
                                player.posX - Math.random(),
                                player.posY - Math.random(),
                                player.posZ - Math.random(), 0, 0.1, 0);
                    }
                }
            }
            
            player.setItemInUse(stack, 10000);

            return stack;
        }

        if (world.getBiomeGenForCoords((int) player.posX, (int) player.posZ) == BiomeGenBase.desert
                || world.getBiomeGenForCoords((int) player.posX,
                        (int) player.posZ) == BiomeGenBase.desertHills) {
            stack.damageItem(5, player);
        } else
            if (world.provider.dimensionId == -1) {} else {
                stack.damageItem(10, player);
            }

        if (!world.isRemote) {
            Vec3 look1 = player.getLookVec();
            EntitySmallFireball fireball21 = new EntitySmallFireball(world,
                    player, 1, 1, 1);
            fireball21.setPosition(player.posX + look1.xCoord, player.posY
                    + 1.8f + look1.yCoord, player.posZ + look1.zCoord);
            fireball21.accelerationX = look1.xCoord * 0.1;
            fireball21.accelerationY = look1.yCoord * 0.1;
            fireball21.accelerationZ = look1.zCoord * 0.1;
            for (int i = 0; i < 2; i++) {
                world.spawnParticle("flame", player.posX + Math.random(),
                        player.posY + Math.random(),
                        player.posZ + Math.random(), 0, 0.1, 0);
                world.spawnParticle("flame", player.posX - Math.random(),
                        player.posY - Math.random(),
                        player.posZ - Math.random(), 0, 0.1, 0);
            }
            world.spawnEntityInWorld(fireball21);
            world.playSoundAtEntity(player, "mob.ghast.fireball", 0.5f, 1.0f);
        }
        return stack;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player,
            Entity entity)
    {
        if (entity instanceof EntityLiving) {
            entity.setFire(10);
            if (entity.worldObj.isRemote) {
                for (int i = 0; i < 4; i++) {
                    entity.worldObj.spawnParticle("flame",
                            entity.posX + Math.random(), entity.posY + 1.0
                                    + Math.random(),
                            entity.posZ + Math.random(), 0, 0.1, 0);
                    entity.worldObj.spawnParticle("flame",
                            entity.posX - Math.random(), entity.posY + 1.0
                                    - Math.random(),
                            entity.posZ - Math.random(), 0, 0.1, 0);
                }
            }
        }

        return true;
    }
}
