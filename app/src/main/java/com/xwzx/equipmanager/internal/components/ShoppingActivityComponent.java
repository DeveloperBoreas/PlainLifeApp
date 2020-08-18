package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.internal.modules.ShoppingActivityModule;
import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.activitys.ShoppingActivity;

import dagger.Component;

@ActivityScope
@Component(modules = ShoppingActivityModule.class, dependencies = {NetComponent.class, AppComponent.class})
public interface ShoppingActivityComponent {
    void inject(ShoppingActivity shoppingActivity);
}
