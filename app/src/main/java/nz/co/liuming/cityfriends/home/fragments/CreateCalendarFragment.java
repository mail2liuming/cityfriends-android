package nz.co.liuming.cityfriends.home.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.basecomponents.BaseFragment;
import nz.co.liuming.cityfriends.common.rest.RestModule;
import nz.co.liuming.cityfriends.common.rest.model.ResultResponse;
import nz.co.liuming.cityfriends.home.model.CalendarFeed;
import nz.co.liuming.cityfriends.home.model.Feed;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by liuming on 9/08/16.
 */
public class CreateCalendarFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.radio_button_provider)
    RadioButton mRadioButtonForProvider;
    @BindView(R.id.radio_button_consumer)
    RadioButton mRadioButtonForConsumer;

    @BindView(R.id.feed_exact_time)
    TextView mExactTimeView;


    public static CreateCalendarFragment newInstance() {
        Bundle args = new Bundle();
        CreateCalendarFragment fragment = new CreateCalendarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_feed, container, false);
        ButterKnife.bind(this, view);

        mExactTimeView.setOnClickListener(this);
        return view;
    }

    @Override
    public String getFragmentTitle() {
        return "New Calendar";
    }

    @Override
    protected Subscription doRequest() {
        CalendarFeed feed = new CalendarFeed();

        return RestModule.getApis().createCalendar(feed).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<ResultResponse>() {
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
            case R.id.feed_exact_time:
                final TextView tv = (TextView) v;
                configureDatePicker(Calendar.getInstance(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        updateTextView(tv, year, monthOfYear, dayOfMonth);
                    }
                }).show(getActivity().getFragmentManager(), "DatePicker");

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
