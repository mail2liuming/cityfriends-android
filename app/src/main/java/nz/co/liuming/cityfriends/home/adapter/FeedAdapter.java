package nz.co.liuming.cityfriends.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.utils.DateUtils;
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
            if(entry.getFeed_type() == FeedEntry.TYPE_PROVIDER){
                holder.mTypeView.setText("Provider");
                holder.mAddToCalendarView.setVisibility(View.VISIBLE);
            }
            else {
                holder.mTypeView.setText("Consumer");
                holder.mAddToCalendarView.setVisibility(View.GONE);
            }


            attachText(holder.mUserNameView,entry.getUser_name());
            attachText(holder.mStartPlaceView,entry.getStart_place());
            attachDateText(holder.mStartTimeView, entry.getStart_time());
            attachText(holder.mEndPlaceView,entry.getEnd_place());
            attachDateText(holder.mEndTimeView,entry.getEnd_time());
        }
    }

    private void attachDateText(TextView tv, String content) {
        if (!TextUtils.isEmpty(content)) {
            tv.setText(DateUtils.getFormattedDate(DateUtils.getDateFromString(content,DateUtils.DATE_FORMAT_DEFAULT),DateUtils.DATE_FORMAT_DATE));
        } else {
            tv.setText("");
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
        @BindView(R.id.item_feed_add_to_calendar)
        ImageView mAddToCalendarView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
