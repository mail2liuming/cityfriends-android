package nz.co.liuming.cityfriends.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.basecomponents.BaseFragment;
import nz.co.liuming.cityfriends.common.rest.RestModule;
import nz.co.liuming.cityfriends.common.rest.model.ResultResponse;
import nz.co.liuming.cityfriends.home.model.Feed;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by liuming on 13/07/16.
 */
public class CreateFeedFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.radio_button_provider)
    RadioButton mRadioButtonForProvider;
    @BindView(R.id.radio_button_consumer)
    RadioButton mRadioButtonForConsumer;

    @BindView(R.id.feed_available)
    EditText mAvailableView;
    @BindView(R.id.feed_start_place)
    EditText mStartPlaceView;
    @BindView(R.id.feed_start_time)
    DatePicker mStartTimeView;
    @BindView(R.id.feed_end_place)
    EditText mEndPlaceView;
    @BindView(R.id.feed_end_time)
    DatePicker mEndTimeView;

    @BindView(R.id.feed_button_submit)
    Button mFeedSubmitButton;


    public static CreateFeedFragment newInstance() {
        Bundle args = new Bundle();
        CreateFeedFragment fragment = new CreateFeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_feed, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public String getFragmentTitle() {
        return "New Feed";
    }

    @Override
    protected Subscription doRequest() {
        Feed feed = new Feed();
        feed.setAvailable(mAvailableView.getText().toString());
        feed.setFeed_type(1);
        return RestModule.getApis().createFeed(feed).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<ResultResponse>() {
            @Override
            public void call(ResultResponse resultResponse) {

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio_button_provider:
                mAvailableView.setHint("Available");
                break;
            case R.id.radio_button_consumer:
                mAvailableView.setHint("Needed");
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.feed_button_submit)
    void onSubmit() {
        if (validate()) {
            request();
        }
    }

    private boolean validate() {
        return true;
    }
}
