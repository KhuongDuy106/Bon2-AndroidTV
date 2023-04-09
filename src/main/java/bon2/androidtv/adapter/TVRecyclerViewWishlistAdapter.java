package bon2.androidtv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public abstract class TVRecyclerViewWishlistAdapter<VH extends TVRecyclerViewWishlistAdapter.ViewHolder> extends RecyclerView.Adapter<VH> {

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.itemView.setFocusable(false);

        onDataBinding(holder, position);
    }

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
