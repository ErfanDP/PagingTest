package org.maktab.pagingtest.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
@Entity(tableName = "concerts")
public class Concert {
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    private UUID mId;
    @ColumnInfo(name = "date")
    private Date mDate;
    @ColumnInfo(name = "name")
    private String mName;


    public Concert() {
    }

    public Concert(@NotNull UUID id, Date date, String name) {
        mId = id;
        mDate = date;
        mName = name;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concert concert = (Concert) o;
        return mDate == concert.mDate &&
                Objects.equals(mName, concert.mName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mDate, mName);
    }


}
