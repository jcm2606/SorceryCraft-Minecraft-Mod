package jcm2606.mods.sorcerycraft.astral.ability;

import java.util.ArrayList;

import jcm2606.mods.sorcerycraft.api.IAbility;
import jcm2606.mods.sorcerycraft.astral.ability.abilities.FocusedHeat;
import jcm2606.mods.sorcerycraft.astral.ability.abilities.IndirectTransmutation;
import jcm2606.mods.sorcerycraft.astral.ability.abilities.ReverseGravity;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import jcm2606.mods.sorcerycraft.core.network.PacketHandler;
import jcm2606.mods.sorcerycraft.core.network.PacketType;
import jcm2606.mods.sorcerycraft.core.network.packet.PacketPlayerForgetAbilitySync;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class AstralAbilityBase implements IExtendedEntityProperties
{
    public static final String NAME = "SCPlayerAstralAbility";
    
    protected EntityPlayer player;
    
    public ArrayList<IAbility> list = new ArrayList<IAbility>();
    public static ArrayList<IAbility> abilitiesList = new ArrayList<IAbility>();
    
    private boolean learnedBaseAbilities = false;
    
    public AstralAbilityBase(EntityPlayer player)
    {
        this.player = player;
    }
    
    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound tag = new NBTTagCompound();
        
        NBTTagList list = new NBTTagList();
        
        for (IAbility ability : abilitiesList)
        {
            NBTTagCompound tag2 = new NBTTagCompound();
            
            if(ability == null)
            {
                continue;
            }
            
            tag2.setString("Ability", ability.getName());
            
            list.appendTag(tag2);
        }
        
        tag.setTag("AbilityList", list);
        
        tag.setBoolean("learnedBaseAbilities", this.learnedBaseAbilities);
        
        compound.setCompoundTag(NAME, tag);
    }
    
    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound tag = compound.getCompoundTag(NAME);
        
        NBTTagList list = tag.getTagList("AbilityList");
        
        for (int i = 0; i < list.tagCount(); i++)
        {
            NBTTagCompound tag2 = (NBTTagCompound) list.tagAt(i);
            
            this.list.add(this.getAbility(tag2.getString("Ability")));
        }
        
        this.learnedBaseAbilities = tag.getBoolean("learnedBaseAbilities");
    }
    
    @Override
    public void init(Entity entity, World world)
    {
        unlockBaseAbilities(this.player);
    }
    
    public void syncLearn(IAbility ability)
    {
        PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketPlayerForgetAbilitySync(ability.getName()), PacketHandler.CHANNEL_SC),
                (Player) this.player);
    }
    
    public void syncForget(IAbility ability)
    {
        PacketDispatcher.sendPacketToPlayer(
                PacketType.populatePacket(new PacketPlayerForgetAbilitySync(ability.getName()), PacketHandler.CHANNEL_SC), (Player) this.player);
    }
    
    public static void registerAbilities()
    {
        registerAbility(new IndirectTransmutation());
        registerAbility(new ReverseGravity());
        registerAbility(new FocusedHeat());
    }
    
    public static void registerAbility(IAbility ability)
    {
        if (abilitiesList.contains(ability))
        {
            SorceryCraft.logger.warning("Ability '" + ability.getClass().getSimpleName() + "' is already registered, skipping.");
            
            return;
        }
        
        abilitiesList.add(ability);
    }
    
    public static void learnAbility(EntityPlayer player, IAbility ability)
    {
        if (((AstralAbilityBase) player.getExtendedProperties(NAME)) != null)
        {
            if (!abilitiesList.contains(ability))
            {
                SorceryCraft.logger.warning("Ability '" + ability.getClass().getSimpleName() + "' is not valid; Not registered.");
                
                return;
            }
            
            if (((AstralAbilityBase) player.getExtendedProperties(NAME)).list.contains(ability))
            {
                SorceryCraft.logger.warning("Player '" + player.username + "' already knows ability '" + ability.getClass().getSimpleName() + "'.");
            } else
            {
                ((AstralAbilityBase) player.getExtendedProperties(NAME)).list.add(ability);
                
                SorceryCraft.logger.warning("Taught ability '" + ability.getClass().getSimpleName() + "' to player '" + player.username + "'.");
                
                ((AstralAbilityBase) player.getExtendedProperties(NAME)).syncLearn(ability);
            }
        }
    }
    
    public static void unlockBaseAbilities(EntityPlayer player)
    {
        AstralAbilityBase base = ((AstralAbilityBase) player.getExtendedProperties(NAME));
        
        if(player.worldObj.isRemote)
        {
            return;
        }
        
        base.learnAbility(player, new IndirectTransmutation());
        base.learnAbility(player, new FocusedHeat());
    }
    
    public static void unlockAllAbilities(EntityPlayer player)
    {
        for (IAbility ability : abilitiesList)
        {
            if(ability == null)
            {
                continue;
            }
            
            learnAbility(player, ability);
        }
    }
    
    public static void forgetAbility(EntityPlayer player, IAbility ability)
    {
        if (((AstralAbilityBase) player.getExtendedProperties(NAME)) != null)
        {
            if (!abilitiesList.contains(ability))
            {
                SorceryCraft.logger.warning("Ability '" + ability.getClass().getSimpleName() + "' is not valid; Not registered.");
                
                return;
            }
            
            if (!((AstralAbilityBase) player.getExtendedProperties(NAME)).list.contains(ability))
            {
                SorceryCraft.logger.warning("Player '" + player.username + "' does not know ability '" + ability.getClass().getSimpleName() + "'.");
            } else
            {
                ((AstralAbilityBase) player.getExtendedProperties(NAME)).list.remove(ability);
                
                SorceryCraft.logger.warning("Forgot ability '" + ability.getClass().getSimpleName() + "' on player '" + player.username + "'.");
                
                ((AstralAbilityBase) player.getExtendedProperties(NAME)).syncForget(ability);
            }
        }
    }
    
    public static void forgetAllAbilities(EntityPlayer player)
    {
        ((AstralAbilityBase) player.getExtendedProperties(NAME)).list.clear();
    }
    
    public static boolean isValid(IAbility ability)
    {
        return abilitiesList.contains(ability);
    }
    
    public static IAbility getAbility(String name)
    {
        IAbility ability = null;
        
        for (IAbility a : abilitiesList)
        {
            if (a.getName().equals(name))
            {
                ability = a;
                break;
            }
        }
        
        return ability;
    }
}
