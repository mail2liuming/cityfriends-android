package nz.co.liuming.cityfriends.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.basecomponents.BaseActivity;
import nz.co.liuming.cityfriends.common.rest.RestModule;
import nz.co.liuming.cityfriends.common.utils.FragmentManagerUtils;
import nz.co.liuming.cityfriends.common.utils.LogUtil;
import nz.co.liuming.cityfriends.home.adapter.BaseFeedAdapter;
import nz.co.liuming.cityfriends.home.adapter.FeedAdapter;
import nz.co.liuming.cityfriends.home.model.Feed;
import nz.co.liuming.cityfriends.home.model.FeedEntry;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liuming on 17/06/16.
 */
public class FeedFragment extends BaseFeedFragment<Feed> {

    private FloatingActionButton fab;

    public static FeedFragment newInstance() {
        Bundle args = new Bundle();

        FeedFragment fragment = new FeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManagerUtils.addFragmentAndAddToBackStack((BaseActivity) getActivity(), CreateFeedFragment.newInstance(), CreateFeedFragment.TAG, FragmentManagerUtils.Animation.SLIDE_IN_BOTTOM);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != fab) {
            fab.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != fab) {
            fab.setVisibility(View.GONE);
        }
    }

    @Override
    public String getFragmentTitle() {
        return "Feed";
    }

    @Override
    public BaseFeedAdapter<Feed, ?> getAdapter() {
        return new FeedAdapter();
    }

    @Override
    protected Subscription doRequest() {
        LogUtil.d(this.getClass().getSimpleName() + " : doLoadData");
        return RestModule.getApis().getEntries(mCurPage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onSuccessListner, onErrorListner);
    }

}
