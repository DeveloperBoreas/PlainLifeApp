package com.boreas.plainlife.internal.modules;

import com.boreas.plainlife.App;
import com.boreas.plainlife.Constant;
import com.boreas.plainlife.db.DBHelper;
import com.boreas.plainlife.db.DaoMaster;
import com.boreas.plainlife.db.DaoSession;
import com.boreas.plainlife.db.LoginModelDao;
import com.boreas.plainlife.framwork.MigrationHelper;

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
