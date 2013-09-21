package jcm2606.mods.sorcerycraft.core;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SCCreativeTab
{
    public static class TabBlocks extends CreativeTabs
    {
        public TabBlocks(int par1, String par2Str)
        {
            super(par1, par2Str);
        }
        
        @Override
        @SideOnly(Side.CLIENT)
        public String getTranslatedTabLabel()
        {
            return "SorceryCraft Blocks";
        }
        
        @Override
        @SideOnly(Side.CLIENT)
        /**
         * the itemID for the item to be displayed on the tab
         */
        public int getTabIconItemIndex()
        {
            return SCObjects.workbenchArcane.blockID;
        }
    }
    
    public static class TabBlocksDeco extends CreativeTabs
    {
        public TabBlocksDeco(int par1, String par2Str)
        {
            super(par1, par2Str);
        }
        
        @Override
        @SideOnly(Side.CLIENT)
        public String getTranslatedTabLabel()
        {
            return "SorceryCraft Decoration Blocks";
        }
        
        @Override
        @SideOnly(Side.CLIENT)
        /**
         * the itemID for the item to be displayed on the tab
         */
        public int getTabIconItemIndex()
        {
            return SCObjects.brickDarkQuartz.blockID;
        }
    }
    
    public static class TabItems extends CreativeTabs
    {
        public TabItems(int par1, String par2Str)
        {
            super(par1, par2Str);
        }
        
        @Override
        @SideOnly(Side.CLIENT)
        public String getTranslatedTabLabel()
        {
            return "SorceryCraft Items";
        }
        
        @Override
        @SideOnly(Side.CLIENT)
        /**
         * the itemID for the item to be displayed on the tab
         */
        public int getTabIconItemIndex()
        {
            return SCObjects.stoneArcane.itemID;
        }
    }
}
