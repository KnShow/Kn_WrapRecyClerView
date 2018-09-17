package recyclerview.itcast.cn.kn_wraprecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HeaderAndFooterViewAdapter extends RecyclerView.Adapter {
    private final RecyclerView.Adapter mAdapter;
    private ArrayList<View> mHeaderViewInfo = new ArrayList<View>();
    private ArrayList<View> mFooterViewInfo = new ArrayList<View>();


    public HeaderAndFooterViewAdapter(RecyclerView.Adapter adapter, ArrayList<View> headerViewInfo, ArrayList<View> footViewInfo) {
        mAdapter = adapter;
        if (headerViewInfo == null) {
            mHeaderViewInfo = new ArrayList<>();
        } else {
            mHeaderViewInfo = headerViewInfo;
        }

        if (footViewInfo == null) {
            mFooterViewInfo = new ArrayList<>();
        } else {
            mFooterViewInfo = footViewInfo;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == RecyclerView.INVALID_TYPE) {
            return new HeaderViewHolder(mHeaderViewInfo.get(0));
        } else if (viewType == RecyclerView.INVALID_TYPE - 1) {
            return new HeaderViewHolder(mFooterViewInfo.get(0));
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == RecyclerView.INVALID_TYPE) {
            return;
        } else if (itemViewType == RecyclerView.INVALID_TYPE - 1) {
            return;
        } else {
            //返回普通布局
            mAdapter.onBindViewHolder(holder, position - mHeaderViewInfo.size());
        }
    }

    @Override
    public int getItemViewType(int position) {
        int numHeaders = mHeaderViewInfo.size();
        if (numHeaders > position) {
            return RecyclerView.INVALID_TYPE;
        }
        int adjPosition = position - numHeaders;
        int dataCount = 0;
        if (mAdapter != null) {
            dataCount = mAdapter.getItemCount();
            if (dataCount > adjPosition) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }
        //返回尾布局
        return RecyclerView.INVALID_TYPE - 1;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter != null ? mAdapter.getItemCount() + mHeaderViewInfo.size() + mFooterViewInfo.size() :
                mHeaderViewInfo.size() + mFooterViewInfo.size();
    }
}
