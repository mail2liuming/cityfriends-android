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
            attachText(holder.mUserNameView,entry.getUser_name());
            attachText(holder.mStartPlaceView,entry.getStart_place());
            attachText(holder.mStartTimeView,entry.getStart_time());
            attachText(holder.mEndPlaceView,entry.getEnd_place());
            attachText(holder.mEndTimeView,entry.getEnd_time());
        }
    }

    private void attachText(TextView tv, String content) {
        if (!TextUtils.isEmpty(content)) {
            tv.setText(content);
        } else {
            tv.setText("");
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        @BindView(R.id.item_feed_type)
        TextView mTypeView;
        @BindView(R.id.item_feed_user_name)
        TextView mUserNameView;
        @BindView(R.id.item_feed_start_place)
        TextView mStartPlaceView;
        @BindView(R.id.item_feed_start_time)
        TextView mStartTimeView;
        @BindView(R.id.item_feed_end_place)
        TextView mEndPlaceView;
        @BindView(R.id.item_feed_end_time)
        TextView mEndTimeView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
