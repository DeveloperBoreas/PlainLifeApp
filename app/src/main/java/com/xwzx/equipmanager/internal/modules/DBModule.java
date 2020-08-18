package com.xwzx.equipmanager.internal.modules;

import com.xwzx.equipmanager.App;
import com.xwzx.equipmanager.Constant;
import com.xwzx.equipmanager.db.DBHelper;
import com.xwzx.equipmanager.db.DaoMaster;
import com.xwzx.equipmanager.db.DaoSession;
import com.xwzx.equipmanager.db.LoginModelDao;
import com.xwzx.equipmanager.framwork.MigrationHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
@Singleton
public class DBModule {

    @Provides
    @Singleton
    public DaoSession provideDB() {
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(App.getInstance().getApplicationContext(), Constant.DB_NAME, null);
//        SQLiteDatabase db = helper.getWritableDatabase();
//        DaoMaster master = new DaoMaster(db);

        MigrationHelper.DEBUG = Constant.DEBUG;
        DBHelper helper = new DBHelper(App.getInstance().getApplicationContext(), Constant.DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        DaoSession session = daoMaster.newSession();
        return session;
    }

    @Provides
    @Singleton
    public LoginModelDao provideLoginModelDao(DaoSession session) {
        return session.getLoginModelDao();
    }


}
