package jcm2606.mods.sorcerycraft.api.astral.gauntlet;

public enum EnumUseType {
    /**
     * Block right click.
     * 
     * Return true to continue block processing and use energy.
     */
    BLOCK_RIGHT_CLICK("BlockRightClick", "BRC"),
    
    /**
     * Item right click.
     * 
     * Return to use energy.
     */
    ITEM_RIGHT_CLICK("ItemRightClick", "IRC"),
    
    /**
     * Item use.
     * 
     * Return true to use energy.
     */
    ITEM_USE("ItemUse", "IU"),
    
    /**
     * Entity hit.
     * 
     * Return true to continue entity hit processing and use energy.
     */
    ENTITY_HIT("EntityHit", "EH"),
    
    /**
     * Block left click.
     * 
     * Return true to cancel block hit processing and use energy.
     */
    BLOCK_LEFT_CLICK("BlockLeftClick", "BLC");
    
    public String identifer;
    public String suffix;
    
    EnumUseType(String identifier, String suffix)
    {
        this.identifer = identifier;
        this.suffix = suffix;
    }
}
