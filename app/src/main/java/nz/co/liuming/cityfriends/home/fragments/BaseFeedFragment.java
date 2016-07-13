package nz.co.liuming.cityfriends.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.adapters.RecyclerAdapterWithFooter;
import nz.co.liuming.cityfriends.common.basecomponents.BaseFragment;
import nz.co.liuming.cityfriends.common.rest.RestModule;
import nz.co.liuming.cityfriends.common.utils.LogUtil;
import nz.co.liuming.cityfriends.home.adapter.BaseFeedAdapter;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by liuming on 17/06/16.
 */
public abstract class BaseFeedFragment<FT> extends BaseFragment {

    protected BaseFeedAdapter mAdapter;
    @BindView(R.id.feed_list)
    RecyclerView mListView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mRefreshView;
    protected Action1<Throwable> onErrorListner = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            hideLoadingView();
            mRefreshView.setRefreshing(false);
            LogUtil.d("" + throwable.toString());
        }
    };
    protected int mCurPage = 1;
    protected Action1<List<FT>> onSuccessListner = new Action1<List<FT>>() {
        @Override
        public void call(List<FT> feedEntries) {
            mAdapter.appendData(feedEntries);
            mAdapter.notifyDataSetChanged();
            hideLoadingView();
            mRefreshView.setRefreshing(false);
            if (feedEntries.size() > 0) {
                mCurPage++;
            }
        }
    };

    public abstract BaseFeedAdapter<FT, ?> getAdapter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = getAdapter();
        request();
        showLoadingView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_list, container, false);
        ButterKnife.bind(this, view);
        mListView.setAdapter(mAdapter);
        mAdapter.setFootViewStatus(RecyclerAdapterWithFooter.FOOT_STATUS_NONE);

        mRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurPage = 1;
                mAdapter.clearData();
                request();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
