package jcm2606.mods.sorcerycraft.block.astral;

import java.util.List;

import jcm2606.mods.sorcerycraft.block.tile.astral.TileEntityAstralMechanism;
import jcm2606.mods.sorcerycraft.core.helper.SCHelper;
import jcm2606.mods.sorcerycraft.core.lib.Rarities;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

public class BlockAstralMechanism extends BlockAstral {
    public BlockAstralMechanism(int par1) {
        super(par1, Material.iron, "astralMechanismBlock", Rarities.ADVANCED, "astral_mechanism_anim");
        this.useIconIndex = false;
        this.renderAsNormalBlock = false;
        this.isOpaqueCube = false;
        this.renderID = -1;
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityAstralMechanism();
    }
    
    @Override
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4) {
        if(SCHelper.playerHasPerceptionMedallion(player))
        {
            if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.keyCode))
            {
                list.add("An Astral block which was");
                list.add("created with Astral Mechanism");
                list.add("Drives. The drives used to");
                list.add("create this block allows this");
                list.add("block to be used where normal");
                list.add("mechanism bases cannot.");
            } else {
                list.add("<Hold SHIFT>");
            }
        }
    }
}
