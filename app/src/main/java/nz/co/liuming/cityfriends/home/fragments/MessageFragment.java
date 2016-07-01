
package nz.co.liuming.cityfriends.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.adapters.RecyclerAdapterWithFooter;
import nz.co.liuming.cityfriends.common.basecomponents.BaseFragment;
import nz.co.liuming.cityfriends.common.rest.RestModule;
import nz.co.liuming.cityfriends.common.utils.LogUtil;
import nz.co.liuming.cityfriends.home.model.FeedEntry;
import nz.co.liuming.cityfriends.home.model.MessageFeed;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by liuming on 17/06/16.
 */
public class MessageFragment extends BaseFragment {

    private List<MessageFeed> mFeeds = new ArrayList<>();
    private FeedAdapter mAdapter = new FeedAdapter();

    private int mCurPage = 1;

    @BindView(R.id.feed_list)
    RecyclerView mListView;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mRefreshView;


    public static FeedFragment newInstance() {
        Bundle args = new Bundle();

        FeedFragment fragment = new FeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public String getTabTitle() {
        return "Feed";
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData();
        showLoadingView();
    }

    @Override
    protected Subscription doLoadData() {
        return RestModule.getApis().getMessageFeeds(mCurPage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<MessageFeed>>() {
            @Override
            public void call(List<MessageFeed> feedEntries) {
                mFeeds.addAll(feedEntries);
                mAdapter.notifyDataSetChanged();
                hideLoadingView();
                mRefreshView.setRefreshing(false);
                if (feedEntries.size() > 0) {
                    mCurPage++;
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                hideLoadingView();
                mRefreshView.setRefreshing(false);
                LogUtil.d("" + throwable.toString());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_list, container, false);
        ButterKnife.bind(this, view);
        mListView.setAdapter(mAdapter);
        mAdapter.setFootViewStatus(RecyclerAdapterWithFooter.FOOT_STATUS_NO_MORE);

        mRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurPage = 1;
                mFeeds.clear();
                loadData();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public class FeedAdapter extends RecyclerAdapterWithFooter<FeedAdapter.ViewHolder> {

        @Override
        protected FeedAdapter.ViewHolder onCreateHolder(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
            return new ViewHolder(view);
        }

        @Override
        protected void onBindHolder(FeedAdapter.ViewHolder holder, int position) {
            if (position >= 0 && position < mFeeds.size()) {
                MessageFeed entry = mFeeds.get(position);
//                holder.mTypeView.setText(entry.getFeed_type() + "");
//                if (!TextUtils.isEmpty(entry.getUser_name())) {
//                    holder.mUserNameView.setText(entry.getUser_name());
//                }
            }
        }

        @Override
        protected int getDataCount() {
            return mFeeds.size();
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


}

