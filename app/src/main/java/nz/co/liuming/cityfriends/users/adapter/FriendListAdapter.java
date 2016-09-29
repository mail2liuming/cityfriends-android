package nz.co.liuming.cityfriends.users.adapter;

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
import nz.co.liuming.cityfriends.users.model.Friend;

/**
 * Created by liuming on 29/09/16.
 */

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendViewHolder> {


    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FriendViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false));
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position) {
        List<Friend> friendList = CityFreindsApplication.get().getUserDelegate().getFriends();
        if (position >= 0 && position < friendList.size()) {
            Friend friend = friendList.get(position);
            holder.mFriendNameTV.setText(friend.getName());
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
