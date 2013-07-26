package jcm2606.mods.sorcerycraft.block.main;

import java.util.List;

import jcm2606.mods.sorcerycraft.block.SCBlockContainer;
import jcm2606.mods.sorcerycraft.block.tile.TileEntityArcaneWorkbench;
import jcm2606.mods.sorcerycraft.core.SorceryCraft;
import jcm2606.mods.sorcerycraft.core.helper.SCHelper;
import jcm2606.mods.sorcerycraft.core.lib.GuiIDs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockArcaneWorkbench extends SCBlockContainer {
    public BlockArcaneWorkbench(int par1) {
        super(par1, Material.rock, "arcaneWorkbench");
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return new TileEntityArcaneWorkbench();
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
            EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (player.isSneaking()) {
            return false;
        } else {
            player.openGui(SorceryCraft.instance, GuiIDs.ARCANE_WORKBENCH, world,
                    x, y, z);
            return true;
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconBuffer = new Icon[3];
        
        this.iconBuffer[0] = par1IconRegister.registerIcon("SorceryCraft:arcaneWorkbenchTop");
        this.iconBuffer[1] = par1IconRegister.registerIcon("SorceryCraft:arcaneWorkbenchSide");
        this.iconBuffer[2] = Block.planks.getIcon(0, 0);
    }
    
    @Override
    public Icon getIcon(int blockSide, int par2)
    {
        return blockSide == 0 ? iconBuffer[2] : (blockSide == 1 ? iconBuffer[0] : iconBuffer[1]);
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
                list.add("A unique workbench imbued with");
                list.add("the energy of the Vordic Dust");
                list.add("used and the constructability of");
                list.add("the Arcane Stone used to construct it.");
            } else {
                list.add("<Hold SHIFT>");
            }
        }
    }
}
