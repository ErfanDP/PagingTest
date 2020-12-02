package org.maktab.pagingtest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.pagingtest.R;
import org.maktab.pagingtest.data.model.Concert;
import org.maktab.pagingtest.databinding.ListRowConcertBinding;

public class ConcertAdapter
        extends PagedListAdapter<Concert, ConcertAdapter.ConcertViewHolder> {
    private Context mContext;

    public ConcertAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
    }

    private static final DiffUtil.ItemCallback<Concert> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Concert>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Concert oldConcert, Concert newConcert) {
                    return oldConcert.getId() == newConcert.getId();
                }

                @Override
                public boolean areContentsTheSame(Concert oldConcert,
                                                  Concert newConcert) {
                    return oldConcert.equals(newConcert);
                }
            };

    @NonNull
    @Override
    public ConcertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConcertViewHolder(DataBindingUtil
                .inflate(LayoutInflater.from(mContext)
                        , R.layout.list_row_concert
                        ,parent
                        ,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConcertViewHolder holder,
                                 int position) {
        Concert concert = getItem(position);
        if (concert != null) {
            holder.bindTo(concert);
        } else {
            // Null defines a placeholder item - PagedListAdapter automatically
            // invalidates this row when the actual object is loaded from the
            // database.
            holder.clear();
        }
    }

    class ConcertViewHolder extends RecyclerView.ViewHolder{
        private ListRowConcertBinding mBinding;
        public ConcertViewHolder(@NonNull ListRowConcertBinding listRowConcertBinding) {
            super(listRowConcertBinding.getRoot());
            mBinding = listRowConcertBinding;
        }
        void bindTo(Concert concert){
            mBinding.concertDate.setText(concert.getDate().toString());
            mBinding.concertName.setText(concert.getName());
        }
        void clear(){
            mBinding.concertName.setText("loading");
            mBinding.concertDate.setText("loading");
        }
    }
}