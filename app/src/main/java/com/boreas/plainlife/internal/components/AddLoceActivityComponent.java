package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.internal.modules.AddLoveActivityModule;
import com.boreas.plainlife.internal.scopers.ActivityScope;
import com.boreas.plainlife.mvp.views.activitys.AddLoveActivity;

import dagger.Component;

@ActivityScope
@Component(modules = AddLoveActivityModule.class,dependencies = {NetComponent.class,AppComponent.class})
public interface AddLoceActivityComponent {
    void inject(AddLoveActivity addLoveActivity);
}
