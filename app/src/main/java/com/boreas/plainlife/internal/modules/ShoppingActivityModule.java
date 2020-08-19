package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.ShoppingActivityInterface;

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
