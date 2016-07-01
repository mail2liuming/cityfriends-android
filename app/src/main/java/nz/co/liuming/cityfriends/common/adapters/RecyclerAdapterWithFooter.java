package nz.co.liuming.cityfriends.common.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nz.co.liuming.cityfriends.CityFreindsApplication;
import nz.co.liuming.cityfriends.R;

/**
 * Created by liuming on 1/07/16.
 */
public abstract class RecyclerAdapterWithFooter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int FOOT_STATUS_NONE = 0;
    public static final int FOOT_STATUS_LOAD_MORE = 1;
    public static final int FOOT_STATUS_ERROR = 2;
    public static final int FOOT_STATUS_NO_MORE = 3;

    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_FOOTER = 1;

    private int mFootViewStatus = FOOT_STATUS_NONE;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE_NORMAL:
                return onCreateHolder(parent);
            case ITEM_TYPE_FOOTER:
                View view = LayoutInflater.from(CityFreindsApplication.get()).inflate(R.layout.list_footer, parent, false);
                return new RecyclerViewHolder(view, ITEM_TYPE_FOOTER);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position > 0 && position < getDataCount()) {
            onBindHolder((VH) holder, position);
        } else {
            RecyclerViewHolder footerHolder = (RecyclerViewHolder) holder;
            footerHolder.mTextView.setText(getFootText());
        }
    }

    @Override
    public int getItemCount() {
        return getDataCount() + ((mFootViewStatus == FOOT_STATUS_NONE) ? 0 : 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (position > 0 && position < getDataCount()) {
            return ITEM_TYPE_NORMAL;
        }

        return ITEM_TYPE_FOOTER;
    }

    protected abstract int getDataCount();

    protected abstract VH onCreateHolder(ViewGroup parent);

    protected abstract void onBindHolder(VH holder, int position);


    public void setFootViewStatus(int aStatus) {
        mFootViewStatus = aStatus;
        notifyDataSetChanged();
    }

    private String getFootText() {
        switch (mFootViewStatus) {
            case FOOT_STATUS_ERROR:
                return "error";
            case FOOT_STATUS_LOAD_MORE:
                return "load more";
            case FOOT_STATUS_NO_MORE:
                return "no more data";
            case FOOT_STATUS_NONE:
            default:
                return "";
        }
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public int viewType;

        public RecyclerViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
            mTextView = (TextView) itemView.findViewById(R.id.footer_text);

        }
    }
}
