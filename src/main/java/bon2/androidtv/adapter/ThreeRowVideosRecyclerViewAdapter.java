package bon2.androidtv.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bon2.androidtv.R;
import bon2.androidtv.activity.PeopleActivity;
import bon2.androidtv.activity.VideoActivity;
import bon2.androidtv.view.TVRecyclerView;

public class ThreeRowVideosRecyclerViewAdapter extends TVRecyclerViewAdapter<TVRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "ThreeRowVideosAdapter";

    Context mContext;

    public ThreeRowVideosRecyclerViewAdapter(Context context) {
        mContext = context;
        setHasStableIds(true);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Log.e(TAG, "onItemClick:" + pos);

                Intent i = new Intent(mContext, VideoActivity.class);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public TVRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_one_three_row_video, parent, false);
        view.setBackground(mContext.getResources().getDrawable(R.drawable.rv_item_bg));
        TVRecyclerViewAdapter.ViewHolder viewHolder = new TVRecyclerViewAdapter.ViewHolder(mContext, view);
        return viewHolder;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    protected void focusOut(View v,int pos) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.02f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.02f, 1.0f);
        AnimatorSet set = new AnimatorSet();
        set.play(scaleX).with(scaleY);
        set.start();
        v.findViewById(R.id.item_title1).setSelected(false);
        v.findViewById(R.id.item_title2).setSelected(false);
    }

    @Override
    protected void focusIn(View v,int pos) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.02f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.02f);
        AnimatorSet set = new AnimatorSet();
        set.play(scaleX).with(scaleY);
        set.start();
        v.findViewById(R.id.item_title1).setSelected(true);
        v.findViewById(R.id.item_title2).setSelected(true);
    }

    @Override
    protected void onDataBinding(TVRecyclerViewAdapter.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class ViewHolder extends TVRecyclerViewAdapter.ViewHolder {

        public ViewHolder(Context context, View itemView) {
            super(context, itemView);
        }
    }

}
