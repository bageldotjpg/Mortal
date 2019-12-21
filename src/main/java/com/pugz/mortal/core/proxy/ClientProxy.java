package com.pugz.mortal.core.proxy;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends ServerProxy {
    @Override
    public void init() {
    }
}