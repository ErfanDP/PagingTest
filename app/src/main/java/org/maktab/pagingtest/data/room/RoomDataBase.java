package org.maktab.pagingtest.data.room;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import org.maktab.pagingtest.data.model.Concert;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@TypeConverters({RoomConverters.class})
@androidx.room.Database(entities = {Concert.class},version = 1)
public abstract class RoomDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "criminal_intent.db";
    private static final int NUMBER_OF_THREADS = 2;

    public static ExecutorService dataBaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract RoomDao getCrimeDAO();

    public static RoomDataBase getDataBase(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                RoomDataBase.class,
                RoomDataBase.DATABASE_NAME).build();
    }
}
