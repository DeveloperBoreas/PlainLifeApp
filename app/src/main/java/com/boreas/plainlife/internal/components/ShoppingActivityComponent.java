package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.ShoppingActivityModule;
import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.activitys.ShoppingActivity;

import dagger.Component;

@ActivityScope
@Component(modules = ShoppingActivityModule.class, dependencies = {NetComponent.class, AppComponent.class})
public interface ShoppingActivityComponent {
    void inject(ShoppingActivity shoppingActivity);
}
