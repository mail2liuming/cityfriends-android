package nz.co.liuming.cityfriends.common.basecomponents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.utils.DateUtils;
import rx.Subscription;

/**
 * Created by liuming on 15/06/16.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @BindView(R.id.progress)
    View mLoadingView;

    private Subscription mSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new TextView(getContext());
    }

    public  String getFragmentTitle(){return "";}

    public void navigateToFragment(Fragment fragment) {
        navigateToFragment(fragment, false, false);
    }


    public void navigateToFragment(Fragment fragment, boolean animIn, boolean animOut) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        if (animIn && animOut) {
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        } else if (animIn) {
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        } else if (animOut) {
            fragmentTransaction.setCustomAnimations(0, 0, android.R.anim.fade_in, android.R.anim.fade_out);
        }

        fragmentTransaction.addToBackStack(fragment.getTag());
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    protected  Subscription doRequest() {return null;}

    public void request() {
        unSubscribe();
        mSubscription = doRequest();
    }

    private void unSubscribe() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    protected void showLoadingView() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.VISIBLE);
        }
    }

    protected void hideLoadingView() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unSubscribe();
    }

    /**
     *
     * @param calendar
     * @param dateSetListener
     * @return
     */
    protected DatePickerDialog configureDatePicker(Calendar calendar, DatePickerDialog.OnDateSetListener dateSetListener) {
        //  Date picker dialog null handling.
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));


        return datePickerDialog;
    }

    /**
     * Update the text view with the selected date
     *
     * @param selectedYear  Year
     * @param selectedMonth Month
     * @param selectedDay   Day
     */
    protected void updateTextView(TextView tv, int selectedYear, int selectedMonth, int selectedDay) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(selectedYear, selectedMonth, selectedDay);
        tv.setText(DateUtils.getFormattedDate(calendar.getTime(), DateUtils.DATE_FORMAT_DATE));
//        updateTextView(calendar.getTime());
    }
}
