package nz.co.liuming.cityfriends.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.utils.LogUtil;
import nz.co.liuming.cityfriends.home.model.FeedEntry;

/**
 * Created by liuming on 13/07/16.
 */

public class FeedAdapter extends BaseFeedAdapter<FeedEntry, FeedAdapter.ViewHolder> {
    @Override
    protected FeedAdapter.ViewHolder onCreateHolder(ViewGroup parent) {
        LogUtil.d(this.getClass().getSimpleName() + " : onCreateHolder ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindHolder(FeedAdapter.ViewHolder holder, int position) {
        if (position >= 0 && position < mFeedEntries.size()) {
            FeedEntry entry = mFeedEntries.get(position);
            holder.mTypeView.setText(entry.getFeed_type() + "");
            LogUtil.d(this.getClass().getSimpleName() + " : onBindHolder " + entry.getFeed_type());
            if (!TextUtils.isEmpty(entry.getUser_name())) {
                holder.mUserNameView.setText(entry.getUser_name());
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        @BindView(R.id.item_feed_type)
        TextView mTypeView;
        @BindView(R.id.item_feed_user_name)
        TextView mUserNameView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
