package com.devlip.gymassistant;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.Cursor;
import com.devlip.gymassistant.constants.DatabaseInitConst;
import com.devlip.gymassistant.database.Contract;
import com.devlip.gymassistant.database.DbHelper;

public class App extends Application {
    private DbHelper dbHelper;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences("TablesSizes", MODE_PRIVATE);
        dbHelper = new DbHelper(getApplicationContext(), DatabaseInitConst.DATABASE_NAME, DatabaseInitConst.DATABASE_VERSION);
        //TODO test this
        //add tables last row index to shared
        //TODO save all data to shared until training will be saved!! GOOD IDEA
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (final Class<?> clazz : Contract.MODELS) {
                    Cursor cursor = dbHelper.query("SELECT _id from " + DbHelper.getTableName(clazz));
                    cursor.moveToLast();
                    int i = cursor.getInt(cursor.getColumnIndex("_id"));
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(clazz.getName(), i);
                    editor.apply();
                    cursor.close();
                }

            }
        }).start();
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
