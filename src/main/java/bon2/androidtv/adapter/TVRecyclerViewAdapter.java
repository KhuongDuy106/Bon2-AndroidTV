package bon2.androidtv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public abstract class TVRecyclerViewAdapter<VH extends TVRecyclerViewAdapter.ViewHolder> extends RecyclerView.Adapter<VH> {

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.itemView.setFocusable(true);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    focusIn(v, position);
                } else {
                    focusOut(v, position);
                }
                ViewGroup parent = (ViewGroup) v.getParent();
                if (parent != null) {
                    parent.requestLayout();
                    parent.postInvalidate();
                }
            }
        });
        onDataBinding(holder, position);
    }

    protected abstract void focusOut(View v, int position) ;

    protected abstract void focusIn(View v, int position) ;

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected abstract void onDataBinding(VH holder, int position);


    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Context mContext;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            this.mContext = context;
        }
    }

}
