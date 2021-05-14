package com.example.employeeroom.db;

import static android.icu.text.MessagePattern.ArgType.SELECT;

import android.app.Application;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class App extends Application {

    public static App instanse;

    private AppDataBase database;

    final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Employee ADD COLUMN timeEntrySt TEXT");
        }
    };

    final Migration MIGRATION_4_5 = new Migration(4 , 5 ) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Employee ADD COLUMN timeEntryNumber INTEGER");
        }
    };

    final Migration MIGRATION_5_6 = new Migration(5 , 6 ) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Employee ADD COLUMN timeEntryNUMERIC NUMERIC");
        }
    };

    // дата как Long число адекватно хранится только в Sring и  NUMERIC
    //https://unetway.com/tutorial/sqlite-type-data
    // нужно отработать алгоритм удаления ненужного более столбца

    @Override
    public void onCreate() {
        super.onCreate();
        instanse=this;
        database= Room.databaseBuilder(this, AppDataBase.class,"database")
                .allowMainThreadQueries()
               // .fallbackToDestructiveMigration()
                .addMigrations(MIGRATION_3_4)
                .addMigrations(MIGRATION_4_5)
                .addMigrations(MIGRATION_5_6)
                .build();

    }

    public static App getInstance(){
        return instanse;
    }

    public AppDataBase getDatabase(){
        return database;
    }
}

