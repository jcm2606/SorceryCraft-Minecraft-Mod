package jcm2606.mods.sorcerycraft.core.lib;

public enum CapeTypes
{
    CAPE_TYPE_DEV("http://farm9.staticflickr.com/8370/8501890532_99c5353cb0_t.jpg"), CAPE_TYPE_BETA_TESTER(
            "http://farm9.staticflickr.com/8229/8500782793_2cfd2d3698_t.jpg");
    
    public String capeURL;
    
    CapeTypes(String capeURL)
    {
        this.capeURL = capeURL;
    }
}
