package jcm2606.mods.sorcerycraft.item.special;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.jccore.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

public class ItemElementSceptorLightning extends ItemElemental {
    public ItemElementSceptorLightning(int par1) {
        super(par1, "elementSceptorLightning");
        this.setMaxDamage(120);
    }
    
    @Override
    public boolean shouldRotateAroundWhenRendering() {
        return true;
    }
    
    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean currentItem) {
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if(!Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
        {
            MovingObjectPosition mop = GeneralUtil.getTargetBlock(world, player, false, 64.0f);
            
            if(mop == null)
            {
                return stack;
            }
            
            Vec3 vec3 = mop.hitVec;
            
            double x = vec3.xCoord - 1 + world.rand.nextInt(3);
            double y = vec3.yCoord;
            double z = vec3.zCoord - 1 + world.rand.nextInt(3);
            
            EntityLightningBolt bolt = new EntityLightningBolt(world, x, y, z);
            
            world.spawnEntityInWorld(bolt);
            stack.damageItem(1, player);
        } else {
            MovingObjectPosition mop = GeneralUtil.getTargetBlock(world, player, false, 256.0f);
            
            if(mop == null)
            {
                return stack;
            }
            
            Vec3 vec3 = mop.hitVec;
            
            int x = (int) vec3.xCoord;
            int y = (int) vec3.yCoord;
            int z = (int) vec3.zCoord;
            
            int damageAmount = 0;
            
            for(int i = 0; i < 5; i++)
            {
                for(int j = 0; j < 5; j++)
                {
                    if(world.canBlockSeeTheSky(x - 3 + i, y, z - 3 + j))
                    {
                        EntityLightningBolt bolt = new EntityLightningBolt(world, x - 3 + i, y, z - 3 + j);
                        
                        if(damageAmount < 20)
                        {
                            damageAmount++;
                        }
                        
                        world.spawnEntityInWorld(bolt);
                    }
                }
            }
            
            stack.damageItem(damageAmount, player);
        }
        
        return stack;
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.MAGICAL);
    }
}
