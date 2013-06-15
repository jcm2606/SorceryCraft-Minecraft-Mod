package jcm2606.mods.sorcerycraft.item.astral.gauntlet.mode;

import jcm2606.mods.sorcerycraft.item.astral.gauntlet.AstralGauntletManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ModeHeal extends GauntletMode {
    public ModeHeal() {
        super(AstralGauntletManager.getNextAvailableId(), "Healing Induction", "");
        this.useAction = EnumAction.block;
        this.useActionDuration = 72000;
        this.hasItemUse = true;
    }

    @Override
    public boolean onUse(String type, ItemStack stack, World world, EntityPlayer player, EntityLiving living, int x, int y, int z, int side)
    {
        if(type.equals(this.USE_ITEM_USE))
        {
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 1));
            
            return true;
        }
        
        return false;
    }

    @Override
    public void onGauntletItemUpdateTick(ItemStack stack, EntityPlayer player, World world, int slot, boolean isCurrentItem)
    {}

    @Override
    public int energyRequired(String type)
    {
        return 1000;
    }
}
