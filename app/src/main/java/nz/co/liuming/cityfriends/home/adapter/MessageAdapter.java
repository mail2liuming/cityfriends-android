package nz.co.liuming.cityfriends.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import nz.co.liuming.cityfriends.CityFreindsApplication;
import nz.co.liuming.cityfriends.R;
import nz.co.liuming.cityfriends.common.rest.RestModule;
import nz.co.liuming.cityfriends.common.rest.model.ResultResponse;
import nz.co.liuming.cityfriends.common.utils.LogUtil;
import nz.co.liuming.cityfriends.home.model.MessageFeed;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by liuming on 13/07/16.
 */

public class MessageAdapter extends BaseFeedAdapter<MessageFeed, MessageAdapter.ViewHolder> {
    @Override
    protected MessageAdapter.ViewHolder onCreateHolder(ViewGroup parent) {
        LogUtil.d(this.getClass().getSimpleName() + " : onCreateHolder ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindHolder(final MessageAdapter.ViewHolder holder, int position) {
        if (position >= 0 && position < mFeedEntries.size()) {
            final MessageFeed entry = mFeedEntries.get(position);

            holder.mContentTV.setText(entry.getMsg_content());
            int senderID = entry.getSender_id();
            if (CityFreindsApplication.get().getUserDelegate().getId() == senderID) {
                holder.mUserNameView.setText(entry.getReceiver_name());
            } else {
                holder.mUserNameView.setText(entry.getSender_name());
            }

            if (MessageFeed.TYPE_REQUEST_FRIEND == entry.getMsg_type() && entry.getStatus() == MessageFeed.STATUS_UNREAD) {
                holder.mSubmitBtn.setVisibility(View.VISIBLE);
                holder.mSubmitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buildAndSendMessage("accept friend", MessageFeed.TYPE_REQUEST_FRIEND_ACCEPTED, entry.getSender_id());
                        holder.mSubmitBtn.setVisibility(View.GONE);
                        entry.setStatus(MessageFeed.STATUS_READ);
                    }
                });
            } else {
                holder.mSubmitBtn.setVisibility(View.GONE);
            }

        }
    }

    private void buildAndSendMessage(String content, int type, int receiverId) {
        MessageFeed messageFeed = new MessageFeed();
        messageFeed.setReceiver_id(receiverId);
        messageFeed.setMsg_content(content);
        messageFeed.setMsg_type(type);

        RestModule.getApis().createMessage(messageFeed).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<ResultResponse>() {
            @Override
            public void call(ResultResponse resultResponse) {
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        @BindView(R.id.message_content)
        TextView mContentTV;
        @BindView(R.id.message_name)
        TextView mUserNameView;
        @BindView(R.id.message_submit)
        Button mSubmitBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
