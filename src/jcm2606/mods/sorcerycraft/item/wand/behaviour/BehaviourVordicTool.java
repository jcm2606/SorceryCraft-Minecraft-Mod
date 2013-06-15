package jcm2606.mods.sorcerycraft.item.wand.behaviour;

import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.block.IWorkable;
import jcm2606.mods.sorcerycraft.handler.ToolHandler;
import jcm2606.mods.sorcerycraft.item.wand.EnumWandBehaviour;
import jcm2606.mods.sorcerycraft.item.wand.WandManager;
import jcm2606.mods.sorcerycraft.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BehaviourVordicTool extends Behaviour implements IBlockInteractionHandler {
    public BehaviourVordicTool() {
        super(WandManager.getNextAvailableBehaviourId(), "Integrated Vordic Working", EnumWandBehaviour.blockInteract);
        this.setBehaviourClass(this);
        this.icon = Reference.PATH_TEXTURES_GUI_HUD + "wand/wand_vordic_tool.png";
    }

    @Override
    public boolean useCustomWandSound()
    {
        return true;
    }

    @Override
    public Block getBlock(ItemStack stack, EntityPlayer player)
    {
        return null;
    }

    @Override
    public boolean onBlockInteract(ItemStack wand, ItemStack stack,
            EntityPlayer player, World world, int x, int y, int z)
    {
        int blockIdRequired = -1;
        
        for(int i = 0; i < ToolHandler.blocksList.size(); i++)
        {
            Block entry = (Block) ToolHandler.blocksList.get(i);
            IWorkable workableEntry = (IWorkable) entry;
            
            if(world.getBlockId(x, y, z) == workableEntry.getBlockForWorking(stack).blockID)
            {
                blockIdRequired = workableEntry.getBlockForWorking(stack).blockID;
            }
        }
        
        if(blockIdRequired == -1)
        {
            return false;
        }
        
        ToolHandler.performBlockWorking(stack, player, world, x, y, z);
        return true;
    }

    @Override
    public Item reqItem(ItemStack wand)
    {
        return SCObjects.vordictool;
    }
}
