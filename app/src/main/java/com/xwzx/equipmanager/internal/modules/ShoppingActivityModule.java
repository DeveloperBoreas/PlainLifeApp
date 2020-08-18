package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.internal.scopers.ActivityScope;
import com.xwzx.equipmanager.mvp.views.viewinterfaces.ShoppingActivityInterface;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class ShoppingActivityModule {
    private ShoppingActivityInterface shoppingActivityInterface;

    public ShoppingActivityModule(ShoppingActivityInterface shoppingActivityInterface) {
        this.shoppingActivityInterface = shoppingActivityInterface;
    }

    @Provides
    public ShoppingActivityInterface provideShoppingActivityInterface(){
        return this.shoppingActivityInterface;
    }
}
