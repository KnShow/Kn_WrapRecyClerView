package recyclerview.itcast.cn.kn_wraprecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class WrapRecyclerView extends RecyclerView {
    private ArrayList<View> headerViewInfo = new ArrayList<>();
    private ArrayList<View> footViewInfo = new ArrayList<>();
    private Adapter mAdapter;

    public WrapRecyclerView(Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public void addHeaderView(View view) {
        headerViewInfo.add(view);
        if (mAdapter != null) {
            if (mAdapter instanceof HeaderAndFooterViewAdapter) {
                mAdapter = new HeaderAndFooterViewAdapter(mAdapter, headerViewInfo, footViewInfo);
            }
        }
    }

    public void addFooterView(View view) {
        footViewInfo.add(view);
        if (mAdapter != null) {
            if (mAdapter instanceof HeaderAndFooterViewAdapter) {
                mAdapter = new HeaderAndFooterViewAdapter(mAdapter, headerViewInfo, footViewInfo);
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (headerViewInfo.size() > 0 || footViewInfo.size() > 0) {
            mAdapter = new HeaderAndFooterViewAdapter(adapter, headerViewInfo, footViewInfo);
        } else {
            mAdapter = adapter;
        }
        super.setAdapter(mAdapter);
    }
}
