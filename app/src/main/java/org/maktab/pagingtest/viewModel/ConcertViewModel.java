package org.maktab.pagingtest.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import org.maktab.pagingtest.data.model.Concert;
import org.maktab.pagingtest.data.repository.Repository;
import org.maktab.pagingtest.data.room.RoomDao;

public class ConcertViewModel extends ViewModel {
    private final Repository mRepository;
    public final LiveData<PagedList<Concert>> concertList;


    public ConcertViewModel() {
        mRepository =Repository.getInstance();
        concertList = new LivePagedListBuilder<>(
                mRepository.getRoomDao().concertsByDate(), /* page size */ 50).build();
    }
}
