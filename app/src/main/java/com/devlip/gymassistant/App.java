package com.devlip.gymassistant;

import android.app.Application;

import com.devlip.gymassistant.constants.DatabaseInitConst;
import com.devlip.gymassistant.database.DbHelper;

public class App extends Application {
    private DbHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DbHelper(getApplicationContext(), DatabaseInitConst.DATABASE_NAME, DatabaseInitConst.DATABASE_VERSION);
    }

//    @Override
//    public Object getSystemService(String name) {
//        if(name.equals("my_db")) {
//            return dbHelper;
//        }
//        return super.getSystemService(name);
//    }

    public DbHelper getDbHelper() {
        if (dbHelper == null) {
            dbHelper = new DbHelper(getApplicationContext(), DatabaseInitConst.DATABASE_NAME, DatabaseInitConst.DATABASE_VERSION);
        }
        return dbHelper;
    }
}
