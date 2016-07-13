package nz.co.liuming.cityfriends.home.fragments;

import android.os.Bundle;

import nz.co.liuming.cityfriends.common.rest.RestModule;
import nz.co.liuming.cityfriends.common.utils.LogUtil;
import nz.co.liuming.cityfriends.home.adapter.BaseFeedAdapter;
import nz.co.liuming.cityfriends.home.adapter.MessageAdapter;
import nz.co.liuming.cityfriends.home.model.MessageFeed;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liuming on 17/06/16.
 */
public class MessageFragment extends BaseFeedFragment<MessageFeed> {

    public static MessageFragment newInstance() {
        Bundle args = new Bundle();

        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public String getFragmentTitle() {
        return "Feed";
    }

    @Override
    public BaseFeedAdapter<MessageFeed, ?> getAdapter() {
        return new MessageAdapter();
    }

    @Override
    protected Subscription doRequest() {
        LogUtil.d(this.getClass().getSimpleName() + " : doLoadData");
        return RestModule.getApis().getMessageFeeds(mCurPage).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onSuccessListner, onErrorListner);
    }

}
