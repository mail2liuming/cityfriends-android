package nz.co.liuming.cityfriends.users.fragments;

import android.os.Bundle;

import nz.co.liuming.cityfriends.common.basecomponents.BaseFragment;

/**
 * Created by liuming on 28/09/16.
 */

public class FriendsFragment extends BaseFragment {
    public static FriendsFragment newInstance(){
        Bundle args = new Bundle();
        FriendsFragment fragment = new FriendsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
