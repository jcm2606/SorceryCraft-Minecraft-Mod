package jcm2606.mods.sorcerycraft.item.staff;

import java.util.List;

import jcm2606.mods.jccore.core.helper.NBTHelper;
import jcm2606.mods.jccore.core.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.api.IAbility;
import jcm2606.mods.sorcerycraft.api.IItemUseTickHandler;
import jcm2606.mods.sorcerycraft.api.IKeyBound;
import jcm2606.mods.sorcerycraft.astral.ability.AstralAbilityBase;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import jcm2606.mods.sorcerycraft.core.lib.Reference;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketItemUseTick;
import jcm2606.mods.sorcerycraft.item.SCItem;
import jcm2606.mods.sorcerycraft.skill.SkillData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;

public class ItemChannelingStaff extends SCItem implements IKeyBound, IItemUseTickHandler
{
    public ItemChannelingStaff(int par1)
    {
        super(par1, "channelingStaff");
    }
    
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.BASIC);
    }
    @Override
    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return this.getSelectedAbility(itemstack).getUseAction(itemstack) == null ? EnumAction.none : this.getSelectedAbility(itemstack).getUseAction(itemstack);
    }
    
    public void setSelectedAbility(ItemStack stack, String name)
    {
        NBTHelper.setString(NBTHelper.getNBTCompoundForItemStack(stack), "SelectedAbility", name);
    }
    
    public IAbility getSelectedAbility(ItemStack stack)
    {
        return NBTHelper.getString(NBTHelper.getNBTCompoundForItemStack(stack), "SelectedAbility") == "" ? AstralAbilityBase.abilitiesList.get(0)
                : AstralAbilityBase.getAbility(NBTHelper.getString(NBTHelper.getNBTCompoundForItemStack(stack), "SelectedAbility"));
    }
    
    @Override
    public void doKeyBindingAction(EntityPlayer player, ItemStack stack, String keyBinding)
    {
        if (keyBinding.equals(Reference.KEY_BIND_INHAND_ITEM_DESC))
        {
            AstralAbilityBase data = ((AstralAbilityBase) player.getExtendedProperties(AstralAbilityBase.NAME));
            
            if (data.list.isEmpty())
            {
                return;
            }
            
            int currentIndex = data.list.indexOf(this.getSelectedAbility(stack));
            int nextIndex = currentIndex + 1;
            
            if (currentIndex + 1 > data.list.size() - 1)
            {
                nextIndex = 0;
            }
            
            this.setSelectedAbility(stack, data.list.get(nextIndex).getName());
        }
    }
    
    @Override
    public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count)
    {
        this.getSelectedAbility(stack).itemUseTick(stack, player, count);
        
        PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketItemUseTick(count), PacketHandler.CHANNEL_SC));
    }
    
    @Override
    public void serverItemUseTick(ItemStack stack, EntityPlayer player, int count)
    {
        if(this.getSelectedAbility(stack).itemUseTick(stack, player, count))
        {
            SkillData sdata = ((SkillData) player.getExtendedProperties(SkillData.NAME));
            
            sdata.addPointsToWandSkill(1);
        }
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
    }
    
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        boolean successful = this.getSelectedAbility(stack).rightClickBlock(world, player, stack, x, y, z, side);
        
        SkillData sdata = ((SkillData) player.getExtendedProperties(SkillData.NAME));
        
        if(this.getSelectedAbility(stack).rightClickBlock(world, player, stack, x, y, z, side))
        {
            sdata.addPointsToWandSkill(1);
        }
        
        return successful;
    }
}
