package nz.co.liuming.cityfriends.home.fragments;

import android.os.Bundle;

import nz.co.liuming.cityfriends.common.rest.RestModule;
import nz.co.liuming.cityfriends.common.utils.LogUtil;
import nz.co.liuming.cityfriends.home.adapter.BaseFeedAdapter;
import nz.co.liuming.cityfriends.home.adapter.FeedAdapter;
import nz.co.liuming.cityfriends.home.model.FeedEntry;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liuming on 17/06/16.
 */
public class FeedFragment extends BaseFeedFragment<FeedEntry> {

    public static FeedFragment newInstance() {
        Bundle args = new Bundle();

        FeedFragment fragment = new FeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public String getFragmentTitle() {
        return "Feed";
    }

    @Override
    public BaseFeedAdapter<FeedEntry, ?> getAdapter() {
        return new FeedAdapter();
    }

    @Override
    protected Subscription doRequest() {
        LogUtil.d(this.getClass().getSimpleName() + " : doLoadData");
        return RestModule.getApis().getEntries(mCurPage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onSuccessListner, onErrorListner);
    }

}
