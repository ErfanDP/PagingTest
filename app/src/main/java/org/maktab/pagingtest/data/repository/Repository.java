package org.maktab.pagingtest.data.repository;

import android.content.Context;

import org.maktab.pagingtest.data.model.Concert;
import org.maktab.pagingtest.data.room.RoomDao;
import org.maktab.pagingtest.data.room.RoomDataBase;

import java.util.List;

public class Repository {
    private static Repository sRepository;
    private static Context mContext;
    private RoomDao mRoomDao;

    public static Repository getInstance(Context context) {
        mContext = context.getApplicationContext();
        if (sRepository == null) {
            sRepository = new Repository();
        }
        return sRepository;
    }
    public static Repository getInstance() {
        if (sRepository == null) {
            sRepository = new Repository();
        }
        return sRepository;
    }

    public RoomDao getRoomDao() {
        return mRoomDao;
    }

    public Repository() {
        RoomDataBase crimeRoomDataBase = RoomDataBase.getDataBase(mContext);
        mRoomDao = crimeRoomDataBase.getCrimeDAO();
    }

    public void insertConcert(List<Concert> concert) {
        RoomDataBase.dataBaseWriteExecutor.execute(() -> mRoomDao.insert(concert));
    }



}
