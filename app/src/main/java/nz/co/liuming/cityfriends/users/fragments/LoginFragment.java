package nz.co.liuming.cityfriends.users.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nz.co.liuming.cityfriends.CityFreindsApplication;
import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.basecomponents.BaseFragment;
import nz.co.liuming.cityfriends.common.utils.LogUtil;
import nz.co.liuming.cityfriends.users.events.LoginEvent;
import retrofit2.http.Body;
import rx.Subscription;

/**
 * Created by liuming on 22/06/16.
 */
public class LoginFragment extends BaseFragment {

    @BindView(R.id.login_email)
    EditText mEmailView;
    @BindView(R.id.login_password)
    EditText mPasswordView;
    @BindView(R.id.login_confirm_password)
    EditText mConfirmView;
    @BindView(R.id.login_signup_switch)
    SwitchCompat mSwitchView;
    @BindView(R.id.login_forget_password)
    TextView mForgetPasswordView;
    @BindView(R.id.login_user_name)
    EditText mNameView;
    @BindView(R.id.login_submit)
    Button mSubmitView;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_main, container, false);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this, view);

        mSubmitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d(" Login onSubmit");
                if (mSwitchView.isChecked()) {
                    CityFreindsApplication.get().getUserDelegate().signup(mNameView.getText().toString(),mEmailView.getText().toString(),mPasswordView.getText().toString(),mConfirmView.getText().toString());
                } else {
                    CityFreindsApplication.get().getUserDelegate().login(mEmailView.getText().toString(), mPasswordView.getText().toString());
                }
            }
        });

        mSwitchView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    mConfirmView.setVisibility(View.VISIBLE);
                    mNameView.setVisibility(View.VISIBLE);
                }
                else{
                    mConfirmView.setVisibility(View.GONE);
                    mNameView.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(LoginEvent event) {
        final String errorMsg = event.errorMsg;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (null == errorMsg) {
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
