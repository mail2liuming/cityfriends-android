package nz.co.liuming.cityfriends.home.fragments;

import android.os.Bundle;

import nz.co.liuming.cityfriends.common.rest.RestModule;
import nz.co.liuming.cityfriends.common.utils.LogUtil;
import nz.co.liuming.cityfriends.home.adapter.BaseFeedAdapter;
import nz.co.liuming.cityfriends.home.adapter.CalendarAdapter;
import nz.co.liuming.cityfriends.home.model.CalendarFeed;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liuming on 17/06/16.
 */
public class CalendarFragment extends BaseFeedFragment<CalendarFeed> {

    public static CalendarFragment newInstance() {
        Bundle args = new Bundle();

        CalendarFragment fragment = new CalendarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public String getFragmentTitle() {
        return "Calendar";
    }

    @Override
    public BaseFeedAdapter<CalendarFeed, ?> getAdapter() {
        return new CalendarAdapter();
    }

    @Override
    protected Subscription doRequest() {
        LogUtil.d(this.getClass().getSimpleName() + " : doLoadData");
        return RestModule.getApis().getCalendarFeeds(mCurPage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onSuccessListner, onErrorListner);
    }

}
