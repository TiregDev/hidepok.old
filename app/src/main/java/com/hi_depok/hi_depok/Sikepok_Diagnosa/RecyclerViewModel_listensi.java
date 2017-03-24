package com.hi_depok.hi_depok.Sikepok_Diagnosa;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

/**
 * Created by User on 21/03/17.
 */

public class RecyclerViewModel_listensi implements SortedListAdapter.ViewModel {
    private final long mId;
    private final String mText;

    public RecyclerViewModel_listensi(long id, String text) {
        mId = id;
        mText = text;
    }

    public long getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecyclerViewModel_listensi model = (RecyclerViewModel_listensi) o;

        if (mId != model.mId) return false;
        return mText != null ? mText.equals(model.mText) : model.mText == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mText != null ? mText.hashCode() : 0);
        return result;
    }
}
