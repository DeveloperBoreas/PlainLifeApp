package com.boreas.plainlife.internal.components;

import com.boreas.plainlife.db.LoginModelDao;
import com.boreas.plainlife.internal.modules.DBModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = DBModule.class)
@Singleton
public interface DBComponent {
    LoginModelDao getLoginModelDao();
}
