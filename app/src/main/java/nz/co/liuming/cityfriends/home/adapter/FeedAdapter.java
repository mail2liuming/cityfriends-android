package nz.co.liuming.cityfriends.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.basecomponents.BaseActivity;
import nz.co.liuming.cityfriends.common.utils.DateUtils;
import nz.co.liuming.cityfriends.common.utils.FragmentManagerUtils;
import nz.co.liuming.cityfriends.common.utils.LogUtil;
import nz.co.liuming.cityfriends.home.fragments.CreateCalendarFragment;
import nz.co.liuming.cityfriends.home.model.Feed;

/**
 * Created by liuming on 13/07/16.
 */

public class FeedAdapter extends BaseFeedAdapter<Feed, FeedAdapter.ViewHolder> {
    @Override
    protected FeedAdapter.ViewHolder onCreateHolder(ViewGroup parent) {
        LogUtil.d(this.getClass().getSimpleName() + " : onCreateHolder ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindHolder(FeedAdapter.ViewHolder holder, int position) {
        if (position >= 0 && position < mFeedEntries.size()) {
            final Feed entry = mFeedEntries.get(position);
            attachText(holder.mContentView,entry.getFeed_content());
            attachText(holder.mUserNameView,entry.getUser_name());
            attachText(holder.mStartPlaceView,entry.getStart_place());
            attachDateText(holder.mStartTimeView, entry.getStart_time());
            holder.mAddToCalendarView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Log.d("Calendar","onClick");
                    if(context instanceof BaseActivity){
                        Log.d("Calendar","is BaseActiviey");
                        BaseActivity baseActivity = (BaseActivity) context;
                        FragmentManagerUtils.addFragmentAndAddToBackStack(baseActivity, CreateCalendarFragment.newInstance(entry),CreateCalendarFragment.TAG, FragmentManagerUtils.Animation.SLIDE_IN_BOTTOM);
                    }
                }
            });
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
        @BindView(R.id.item_feed_content)
        TextView mContentView;
        @BindView(R.id.item_feed_user_name)
        TextView mUserNameView;
        @BindView(R.id.item_feed_start_place)
        TextView mStartPlaceView;
        @BindView(R.id.item_feed_start_time)
        TextView mStartTimeView;
        @BindView(R.id.item_feed_add_to_calendar)
        Button mAddToCalendarView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
