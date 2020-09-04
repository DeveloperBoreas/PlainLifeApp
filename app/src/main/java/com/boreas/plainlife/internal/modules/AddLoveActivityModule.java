package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.viewinterfaces.AddLoveActivityInterface;
import com.boreas.plainlife.mvp.views.viewinterfaces.LoginActivityInterface;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class AddLoveActivityModule {
    private AddLoveActivityInterface addLoveActivityInterface;

    public AddLoveActivityModule(AddLoveActivityInterface addLoveActivityInterface) {
        this.addLoveActivityInterface = addLoveActivityInterface;
    }

    @Provides
    public AddLoveActivityInterface provideAddLoveActivityInterface(){
        return this.addLoveActivityInterface;
    }
}
