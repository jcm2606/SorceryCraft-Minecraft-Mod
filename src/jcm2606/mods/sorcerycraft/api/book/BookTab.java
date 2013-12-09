package jcm2606.mods.sorcerycraft.api.book;



public class BookTab
{
    private static final BookTab[] tabs = new BookTab[10];
    
    public static final BookTab playerInformation = new BookTab(0, "Player Information", 33);
    public static final BookTab lore = new BookTab(1, "Lore", 43);
    
    private int id = 0;
    public String name;
    public int iconY = 44;
    
    public BookTab(int id, String name, int iconY)
    {
        this.id = id;
        this.name = name;
        this.iconY = iconY;
    }
    
    public int getID()
    {
        return this.id;
    }
    
    public static void register(BookTab tab)
    {
        tabs[tab.id] = tab;
    }
    
    public static void register(BookTab[] Tabs)
    {
        for(BookTab tab : Tabs)
        {
            register(tab);
        }
    }
    
    public static BookTab[] getList()
    {
        return tabs;
    }
    
    public static void loadDefaultTabs()
    {
        BookTab[] list = {
                playerInformation,
                lore,
                lore
        };
        
        register(list);
    }
}
