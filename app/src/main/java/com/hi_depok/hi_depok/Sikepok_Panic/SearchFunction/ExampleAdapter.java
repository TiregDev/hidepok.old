package com.hi_depok.hi_depok.Sikepok_Panic.SearchFunction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.hi_depok.hi_depok.databinding.ItemExampleBinding;

import java.util.Comparator;

/**
 * Created with Android Studio
 * User: Xaver
 * Date: 24/05/15
 */
public class ExampleAdapter extends SortedListAdapter<ExampleModel> {

    public interface Listener {
        void onExampleModelClicked(ExampleModel model);
    }

    private final Listener mListener;

    public ExampleAdapter(Context context, Comparator<ExampleModel> comparator, Listener listener) {
        super(context, ExampleModel.class, comparator);
        mListener = listener;
    }

    @Override
    protected ViewHolder<? extends ExampleModel> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemExampleBinding binding = ItemExampleBinding.inflate(inflater, parent, false);
        return new ExampleViewHolder(binding, mListener);
    }

    @Override
    protected boolean areItemsTheSame(ExampleModel item1, ExampleModel item2) {
        return item1.getId() == item2.getId();
    }

    @Override
    protected boolean areItemContentsTheSame(ExampleModel oldItem, ExampleModel newItem) {
        return oldItem.equals(newItem);
    }
}
