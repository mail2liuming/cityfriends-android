package nz.co.liuming.cityfriends.users.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import nz.co.liuming.cityfriends.CityFreindsApplication;
import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.basecomponents.BaseFragment;
import nz.co.liuming.cityfriends.common.rest.RestModule;
import nz.co.liuming.cityfriends.common.rest.model.ResultResponse;
import nz.co.liuming.cityfriends.home.model.MessageFeed;
import nz.co.liuming.cityfriends.users.model.User;
import nz.co.liuming.cityfriends.users.model.Userable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by liuming on 28/09/16.
 */

public class UserProfileFragment extends BaseFragment {
    public static final String TAG = UserProfileFragment.class.getSimpleName();
    public static final int TYPE_REQUEST_FRIEND = 1;
    public static final int TYPE_ACCEPT_FRIEND = 2;
    public static final int TYPE_REJECT_FRIEND = 3;
    public static final int TYPE_COMMON_TEXT = 4;

    @BindView(R.id.user_profile_name)
    TextView mNameTV;
    @BindView(R.id.user_profile_email)
    TextView mEmailTV;
    @BindView(R.id.user_profile_submit)
    Button mSubmitBtn;
    @BindView(R.id.user_profile_message)
    EditText mMessageET;
    private Userable mUser;

    public static UserProfileFragment newInstance(Userable user) {
        Bundle args = new Bundle();
        args.putParcelable("User", user);
        UserProfileFragment fragment = new UserProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Userable user = getArguments().getParcelable("User");
        mUser = user;
        if (user == null) {
            getFragmentManager().popBackStack();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, v);
        prepateViews();
        mEmailTV.setText(mUser.getEmail());
        mNameTV.setText(mUser.getName());
        return v;
    }

    private void prepateViews() {
        boolean isFriend = CityFreindsApplication.get().getUserDelegate().isFriend(mUser);
        if (isFriend) {
            mSubmitBtn.setVisibility(View.VISIBLE);
            mMessageET.setVisibility(View.VISIBLE);
            mSubmitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buildAndSendMessage(mMessageET.getText().toString(), TYPE_COMMON_TEXT);
                }
            });
        } else if (CityFreindsApplication.get().getUserDelegate().isLogin() && CityFreindsApplication.get().getUserDelegate().getId() != mUser.getId()) {
            mSubmitBtn.setVisibility(View.VISIBLE);
            mSubmitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buildAndSendMessage("Add Friend", TYPE_REQUEST_FRIEND);
                }
            });
        }
    }

    private void buildAndSendMessage(String content, int type) {
        MessageFeed messageFeed = new MessageFeed();
        messageFeed.setReceiver_id(mUser.getId());
        messageFeed.setMsg_content(content);
        messageFeed.setMsg_type(type);

        RestModule.getApis().createMessage(messageFeed).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<ResultResponse>() {
            @Override
            public void call(ResultResponse resultResponse) {
                getFragmentManager().popBackStack();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
