package nz.co.liuming.cityfriends.users.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nz.co.liuming.cityfriends.CityFreindsApplication;
import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.basecomponents.BaseActivity;
import nz.co.liuming.cityfriends.common.utils.FragmentManagerUtils;
import nz.co.liuming.cityfriends.users.fragments.UserProfileFragment;
import nz.co.liuming.cityfriends.users.model.Friend;

/**
 * Created by liuming on 29/09/16.
 */

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendViewHolder> {

    private Activity mActivity;

    public FriendListAdapter(Activity activity){
        mActivity = activity;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FriendViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false));
    }

    @Override
    public void onBindViewHolder(final FriendViewHolder holder, int position) {
        List<Friend> friendList = CityFreindsApplication.get().getUserDelegate().getFriends();
        if (position >= 0 && position < friendList.size()) {
            final Friend friend = friendList.get(position);
            holder.mFriendNameTV.setText(friend.getName());
            holder.mFriendNameTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BaseActivity activity = (BaseActivity) mActivity;
                    FragmentManagerUtils.addFragmentAndAddToBackStack(activity, UserProfileFragment.newInstance(friend), UserProfileFragment.TAG, FragmentManagerUtils.Animation.SLIDE_IN_RIGHT);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return CityFreindsApplication.get().getUserDelegate().getFriends().size();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.friend_name)
        TextView mFriendNameTV;

        public FriendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
