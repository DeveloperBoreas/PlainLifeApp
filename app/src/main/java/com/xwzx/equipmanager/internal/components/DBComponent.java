package com.xwzx.equipmanager.internal.components;

import com.xwzx.equipmanager.db.LoginModelDao;
import com.xwzx.equipmanager.internal.modules.DBModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = DBModule.class)
@Singleton
public interface DBComponent {
    LoginModelDao getLoginModelDao();
}
