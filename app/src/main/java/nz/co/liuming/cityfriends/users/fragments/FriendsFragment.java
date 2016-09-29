package nz.co.liuming.cityfriends.users.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import nz.co.liuming.cityfriends.CityFreindsApplication;
import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.basecomponents.BaseFragment;
import nz.co.liuming.cityfriends.users.adapter.FriendListAdapter;

/**
 * Created by liuming on 28/09/16.
 */

public class FriendsFragment extends BaseFragment {

    public static final String TAG = FriendsFragment.class.getSimpleName();

    @BindView(R.id.friend_list)
    RecyclerView mFriendListView;

    public static FriendsFragment newInstance() {
        Bundle args = new Bundle();
        FriendsFragment fragment = new FriendsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CityFreindsApplication.get().getUserDelegate().loadFriends();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_friends_list, container, false);
        ButterKnife.bind(this, v);

        mFriendListView.setAdapter(new FriendListAdapter());
        return v;
    }


}
