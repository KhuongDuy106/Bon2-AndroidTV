package bon2.androidtv.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bon2.androidtv.R;
import bon2.androidtv.activity.VideoActivity;

public class VideosRecyclerViewAdapter extends TVRecyclerViewAdapter<TVRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "VideosAdapter";

    Context mContext;

    public VideosRecyclerViewAdapter(Context context) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_one_video, parent, false);
        view.setBackground(mContext.getResources().getDrawable(R.drawable.rv_item_bg));
        return new VideoVH(mContext, view);
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
        v.findViewById(R.id.video_item_name).setSelected(false);
        v.findViewById(R.id.video_item_title).setSelected(false);
    }

    @Override
    protected void focusIn(View v,int pos) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.02f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.02f);
        AnimatorSet set = new AnimatorSet();
        set.play(scaleX).with(scaleY);
        set.start();
        v.findViewById(R.id.video_item_name).setSelected(true);
        v.findViewById(R.id.video_item_title).setSelected(true);
    }

    @Override
    protected void onDataBinding(TVRecyclerViewAdapter.ViewHolder holder, int position) {
        if (holder instanceof VideoVH) {
            String str = ((VideoVH) holder).user_name.getText().toString();
            ((VideoVH) holder).user_name.setText(str + " " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class VideoVH extends TVRecyclerViewAdapter.ViewHolder {
        LinearLayout layout;
        ImageView logo;
        TextView user_name, title, sub_title1, sub_title2;

        public VideoVH(Context context, View itemView) {
            super(context, itemView);
            layout = itemView.findViewById(R.id.item_one_video);

            logo = itemView.findViewById(R.id.video_item_logo);
            user_name = itemView.findViewById(R.id.video_item_name);
            title = itemView.findViewById(R.id.video_item_title);
            sub_title1 = itemView.findViewById(R.id.video_item_subtitle1);
            sub_title2 = itemView.findViewById(R.id.video_item_subtitle2);
        }
    }

}
