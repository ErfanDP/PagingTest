package org.maktab.pagingtest.data.room;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import org.maktab.pagingtest.data.model.Concert;

import java.util.List;

@Dao
public interface RoomDao {
    // The Integer type parameter tells Room to use a PositionalDataSource
    // object, with position-based loading under the hood.
    @Query("SELECT * FROM concerts ORDER BY date DESC")
    DataSource.Factory<Integer, Concert> concertsByDate();
    @Insert
    void insert(List<Concert> concertList);
}
