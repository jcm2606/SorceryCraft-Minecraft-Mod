package jcm2606.mods.sorcerycraft.astral.gauntlet;

import java.util.List;

import jcm2606.mods.jccore.util.GeneralUtil;
import jcm2606.mods.sorcerycraft.api.AstralManager;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.EnumUseType;
import jcm2606.mods.sorcerycraft.api.astral.gauntlet.GauntletMode;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ModeTeleport extends GauntletMode {
    public ModeTeleport() {
        super(AstralManager.getNextAvailableId(), "Teleportation", "");
    }

    @Override
    public boolean onUse(EnumUseType type, ItemStack stack, World world, EntityPlayer player, EntityLivingBase living, int x, int y, int z, int side)
    {
        if(type == EnumUseType.ITEM_RIGHT_CLICK)
        {
            MovingObjectPosition mop = GeneralUtil.getTargetBlock(world, player, false, 64.0f);
            
            if(mop == null)
            {
                return false;
            }
            
            Vec3 vec3 = mop.hitVec;
            
            player.setPositionAndUpdate(vec3.xCoord, vec3.yCoord, vec3.zCoord);
            world.playSoundEffect(vec3.xCoord, vec3.yCoord, vec3.zCoord, "mob.endermen.portal", 0.5f, 1.0f);
            
            for (int i = 0; i < 32; ++i)
            {
                world.spawnParticle("portal", vec3.xCoord, vec3.yCoord + world.rand.nextDouble() * 2.0D, vec3.zCoord, world.rand.nextGaussian(), 0.0D, world.rand.nextGaussian());
            }
            
            this.useEnergy(player, type);
            
            return true;
        }
        
        return false;
    }

    @Override
    public void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem)
    {}

    @Override
    public int energyRequired(EnumUseType type, EntityPlayer player)
    {
        if(type.equals(EnumUseType.ITEM_RIGHT_CLICK))
        {
            return 50;
        }
        
        return 0;
    }

    @Override
    public void addInfoToItemMouseover(EntityPlayer player, ItemStack stack, boolean isSneaking, List list)
    {}
}
