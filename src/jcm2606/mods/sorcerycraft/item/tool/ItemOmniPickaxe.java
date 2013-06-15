package jcm2606.mods.sorcerycraft.item.tool;

import java.util.List;

import jcm2606.mods.jccore.helper.RarityHelper;
import jcm2606.mods.sorcerycraft.SCObjects;
import jcm2606.mods.sorcerycraft.item.SCPick;
import jcm2606.mods.sorcerycraft.lib.Rarities;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemOmniPickaxe extends SCPick {
    /** an array of the blocks this pickaxe is effective against */
    public static final Block[] blocksEffectiveAgainst = new Block[] {
            Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab,
            Block.stone, Block.sandStone, Block.cobblestoneMossy,
            Block.oreIron, Block.blockIron, Block.oreCoal, Block.blockGold,
            Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice,
            Block.netherrack, Block.oreLapis, Block.blockLapis,
            Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail,
            Block.railDetector, Block.railPowered, Block.grass, Block.dirt,
            Block.sand, Block.gravel, Block.snow, Block.blockSnow,
            Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium };

    public ItemOmniPickaxe(int par1) {
        super(par1, SCObjects.PICKAXE_OMNI, blocksEffectiveAgainst,
                "toolPickOmni");
        this.setNoRepair();
    }

    /**
     * Returns if the item (tool) can harvest results from the block type.
     */
    @Override
    public boolean canHarvestBlock(Block par1Block)
    {
        return par1Block == Block.obsidian ? this.toolMaterial
                .getHarvestLevel() == 3
                : (par1Block != Block.blockDiamond
                        && par1Block != Block.oreDiamond ? (par1Block != Block.oreEmerald
                        && par1Block != Block.blockEmerald ? (par1Block != Block.blockGold
                        && par1Block != Block.oreGold ? (par1Block != Block.blockIron
                        && par1Block != Block.oreIron ? (par1Block != Block.blockLapis
                        && par1Block != Block.oreLapis ? (par1Block != Block.oreRedstone
                        && par1Block != Block.oreRedstoneGlowing ? (par1Block.blockMaterial == Material.rock ? true
                        : (par1Block.blockMaterial == Material.iron ? true
                                : par1Block.blockMaterial == Material.anvil))
                        : this.toolMaterial.getHarvestLevel() >= 2)
                        : this.toolMaterial.getHarvestLevel() >= 1)
                        : this.toolMaterial.getHarvestLevel() >= 1)
                        : this.toolMaterial.getHarvestLevel() >= 2)
                        : this.toolMaterial.getHarvestLevel() >= 2)
                        : this.toolMaterial.getHarvestLevel() >= 2);
    }

    /**
     * Returns the strength of the stack against a given block. 1.0F base,
     * (Quality+1)*2 if correct blocktype, 1.5F if sword
     */
    @Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return par2Block != null
                && (par2Block.blockMaterial == Material.iron
                        || par2Block.blockMaterial == Material.anvil || par2Block.blockMaterial == Material.rock) ? this.efficiencyOnProperMaterial
                : super.getStrVsBlock(par1ItemStack, par2Block);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return RarityHelper.getCustomRarityType(Rarities.LEGENDARY);
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack stack, EntityPlayer player, List list,
            boolean par4)
    {
    }
    /*
     * @Override public String getModeName() { return "tool"; }
     * 
     * @Override public String getModeLocalName() { return "Block Breaking"; }
     * 
     * @Override public String getMessage() { return
     * "This Wand of Casting has been set to interact with a Pickaxe of the Omniverse to break any block the pickaxe can."
     * ; }
     * 
     * @Override public int energyCost() { return 1; }
     * 
     * @Override public boolean onUse(ItemStack wand, ItemStack stack,
     * EntityPlayer player, World world, int x, int y, int z) { for(Block block
     * : Block.blocksList) { if(block != null) { if(block.blockMaterial ==
     * Material.rock || block.blockMaterial == Material.grass ||
     * block.blockMaterial == Material.ground || block.blockMaterial ==
     * Material.iron) { if(block != Block.bedrock) { if(block.blockID ==
     * world.getBlockId(x, y, z)) { world.setBlock(x, y, z, 0);
     * block.dropBlockAsItem(world, x, y, z, 0, 0);
     * 
     * if(block instanceof BlockOre) { if(world.rand.nextInt(100) < 6) {
     * block.dropBlockAsItem(world, x, y, z, 0, 0); wand.damageItem(1, player);
     * } }
     * 
     * stack.damageItem(4, player); } } } } }
     * 
     * return true; }
     * 
     * @Override public Item reqItem(ItemStack wand) { return this; }
     * 
     * @Override public void wandSound(ItemStack wand, ItemStack stack,
     * EntityPlayer player, World world, int x, int y, int z) { for(Block block
     * : Block.blocksList) { if(block != null) { if(block.blockMaterial ==
     * Material.rock || block.blockMaterial == Material.grass ||
     * block.blockMaterial == Material.ground || block.blockMaterial ==
     * Material.iron) { if(block != Block.bedrock) { if(block.blockID ==
     * world.getBlockId(x, y, z)) { ItemWandCasting wandItem = (ItemWandCasting)
     * wand.getItem();
     * 
     * wandItem.playWandSound(block.stepSound.getBreakSound(), player, world,
     * 1.0f, 0.85f); wandItem.playWandSound("sorcerycraft.wand_use", player,
     * world, 0.1f, 1.0f); } } } } } }
     */
}
