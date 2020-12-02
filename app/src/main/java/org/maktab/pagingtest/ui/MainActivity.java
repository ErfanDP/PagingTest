package org.maktab.pagingtest.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.maktab.pagingtest.R;
import org.maktab.pagingtest.adapters.ConcertAdapter;
import org.maktab.pagingtest.data.model.Concert;
import org.maktab.pagingtest.data.repository.Repository;
import org.maktab.pagingtest.databinding.ActivityMainBinding;
import org.maktab.pagingtest.viewModel.ConcertViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private Repository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = Repository.getInstance(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ConcertViewModel viewModel =
                new ViewModelProvider(this).get(ConcertViewModel.class);
        Log.d("Tag","init");
        ConcertAdapter adapter = new ConcertAdapter(this);
        viewModel.concertList.observe(this, concerts -> {
            Log.d("Tag","observed change");
            adapter.submitList(concerts);
        });

        List<Concert> list = new ArrayList<>();
        for (int i = 0; i < 100 ; i++) {
            list.add(new Concert(UUID.randomUUID(),new Date(),String.valueOf(i)));
        }
        mRepository.insertConcert(list);
        Log.d("Tag","items inserted");
        mBinding.concertList.setLayoutManager(new LinearLayoutManager(this));
        mBinding.concertList.setAdapter(adapter);
    }
}