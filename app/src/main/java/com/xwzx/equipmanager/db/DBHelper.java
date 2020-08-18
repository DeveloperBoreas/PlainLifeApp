package com.xwzx.equipmanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.xwzx.equipmanager.framwork.MigrationHelper;

import org.greenrobot.greendao.database.Database;

public class DBHelper extends DaoMaster.OpenHelper {

    public DBHelper(Context context, String name) {
        super(context, name);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        },LoginModelDao.class);
    }
}
