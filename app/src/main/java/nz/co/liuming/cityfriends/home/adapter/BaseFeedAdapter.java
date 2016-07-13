package nz.co.liuming.cityfriends.home.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nz.co.liuming.cityfriends.common.adapters.RecyclerAdapterWithFooter;
import nz.co.liuming.cityfriends.common.utils.LogUtil;

/**
 * Created by liuming on 13/07/16.
 */

public abstract class BaseFeedAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerAdapterWithFooter<VH> {
    protected List<T> mFeedEntries = new ArrayList<>();

    public void setData(List<T> aFeedEntries) {
        mFeedEntries = aFeedEntries;
    }

    public void appendData(List<T> aFeedEntries) {
        mFeedEntries.addAll(aFeedEntries);
    }

    public void clearData() {
        mFeedEntries.clear();
    }

    @Override
    protected int getDataCount() {
        LogUtil.d(this.getClass().getSimpleName() + " : getDataCount " + mFeedEntries.size());
        return mFeedEntries.size();
    }
}
