package com.boreas.plainlife.internal.components;


import com.boreas.plainlife.ObjectPool;
import com.boreas.plainlife.internal.modules.BeansModule;
import com.boreas.plainlife.websocket.PlainMessage;

import dagger.Component;

@Component(modules = BeansModule.class)
public interface BeansComponent {
    PlainMessage getPlainMessage();
    ObjectPool getObjectPool();
}
