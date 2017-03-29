package com.hi_depok.hi_depok.Sikepok_Panic.SearchFunction;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.hi_depok.hi_depok.databinding.ItemExampleBinding;

public class ExampleViewHolder extends SortedListAdapter.ViewHolder<ExampleModel> {

    private final ItemExampleBinding mBinding;

    public ExampleViewHolder(ItemExampleBinding binding, ExampleAdapter.Listener listener) {
        super(binding.getRoot());
        binding.setListener(listener);

        mBinding = binding;
    }

    @Override
    protected void performBind(ExampleModel item) {
        mBinding.setModel(item);
    }
}
