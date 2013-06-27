package jcm2606.mods.sorcerycraft.compat;

import jcm2606.mods.jccore.compat.container.HandlerMethod;
import jcm2606.mods.jccore.compat.container.SubContainer;

@SubContainer("TestSubContainer")
public class TestContainer {
    @HandlerMethod(100)
    public void test()
    {
        System.out.println("test");
    }
}
