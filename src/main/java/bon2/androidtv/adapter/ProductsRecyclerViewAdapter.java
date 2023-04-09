package bon2.androidtv.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bon2.androidtv.R;
import bon2.androidtv.activity.ProductActivity;
import bon2.androidtv.helper.RoundedImageView;

public class ProductsRecyclerViewAdapter extends TVRecyclerViewAdapter<TVRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "VideosAdapter";

    Context mContext;

    public ProductsRecyclerViewAdapter(Context context) {
        mContext = context;
        setHasStableIds(true);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Log.e(TAG, "onItemClick:" + pos);

                Intent i = new Intent(mContext, ProductActivity.class);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public TVRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_one_product, parent, false);
        return new ProductVH(mContext, view);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    protected void focusOut(View v,int pos) {
        ((RoundedImageView)v.findViewById(R.id.product_item_logo)).setBorderColor(Color.parseColor("#0000ff00"));
        v.findViewById(R.id.product_item_name).setSelected(false);
    }

    @Override
    protected void focusIn(View v,int pos) {
        ((RoundedImageView)v.findViewById(R.id.product_item_logo)).setBorderColor(Color.parseColor("#ff00ff00"));
        v.findViewById(R.id.product_item_name).setSelected(true);
    }

    @Override
    protected void onDataBinding(TVRecyclerViewAdapter.ViewHolder holder, int position) {
        if (holder instanceof ProductVH) {
            String str = ((ProductVH) holder).name.getText().toString();
            ((ProductVH) holder).name.setText(str + " " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class ProductVH extends TVRecyclerViewAdapter.ViewHolder {
        LinearLayout layout;
        ImageView logo;
        TextView name;

        public ProductVH(Context context, View itemView) {
            super(context, itemView);
            layout = itemView.findViewById(R.id.item_one_product);

            logo = itemView.findViewById(R.id.product_item_logo);
            name = itemView.findViewById(R.id.product_item_name);
        }
    }

}
