package jcm2606.mods.sorcerycraft.api;

import jcm2606.mods.sorcerycraft.core.SCObjects;
import jcm2606.mods.sorcerycraft.element.ElementAir;
import jcm2606.mods.sorcerycraft.element.ElementDark;
import jcm2606.mods.sorcerycraft.element.ElementEarth;
import jcm2606.mods.sorcerycraft.element.ElementEnergy;
import jcm2606.mods.sorcerycraft.element.ElementFire;
import jcm2606.mods.sorcerycraft.element.ElementIce;
import jcm2606.mods.sorcerycraft.element.ElementLight;
import jcm2606.mods.sorcerycraft.element.ElementMagic;
import jcm2606.mods.sorcerycraft.element.ElementWater;
import net.minecraft.item.ItemStack;

public class ElementManager
{
    public static IElement[] elementList = new IElement[1024];
    
    public static IElement fire = new ElementFire();
    public static IElement water = new ElementWater();
    public static IElement earth = new ElementEarth();
    public static IElement air = new ElementAir();
    public static IElement ice = new ElementIce();
    public static IElement energy = new ElementEnergy();
    public static IElement magic = new ElementMagic();
    public static IElement light = new ElementLight();
    public static IElement dark = new ElementDark();
    
    public static int getTotalNumberOfElements()
    {
        int num = 0;
        
        for (int i = 0; i < elementList.length; i++)
        {
            if (elementList[i] != null)
            {
                num++;
            }
        }
        
        return num;
    }
    
    public static void loadElements()
    {
        registerElements(fire, water, earth, air, ice, energy, magic, light, dark);
    }
    
    public static IElement[] getElementList()
    {
        return elementList;
    }
    
    public static void registerElements(IElement... elements)
    {
        for (IElement element : elements)
        {
            registerElement(element);
        }
    }
    
    public static void registerElement(IElement element)
    {
        elementList[getNextFreeID()] = element;
    }
    
    private static int getNextFreeID()
    {
        int id = -1;
        
        for (int i = 0; i < 1024; i++)
        {
            IElement element = elementList[i];
            
            if (element == null)
            {
                id = i;
                break;
            }
        }
        
        return id;
    }
    
    public static int getListSlotNumFor(IElement element)
    {
        for (int i = 0; i < 1024; i++)
        {
            IElement element1 = elementList[i];
            
            if (element1 == element)
            {
                return i;
            }
        }
        
        return -1;
    }
    
    public static ItemStack getElementGemFor(IElement element)
    {
        return new ItemStack(SCObjects.gemElemental, 1, getListSlotNumFor(element));
    }
}
