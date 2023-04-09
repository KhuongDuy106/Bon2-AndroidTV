package bon2.androidtv.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import bon2.androidtv.R;
import bon2.androidtv.activity.HomeActivity;
import bon2.androidtv.activity.PeopleActivity;
import bon2.androidtv.helper.CircleImageView;

public class PeopleRecyclerViewAdapter extends TVRecyclerViewAdapter<TVRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "PeopleAdapter";

    Context mContext;

    public PeopleRecyclerViewAdapter(Context context) {
        mContext = context;
        setHasStableIds(true);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Log.e(TAG, "onItemClick:" + pos);

                Intent i = new Intent(mContext, PeopleActivity.class);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public TVRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_one_people, parent, false);
        return new PeopleVH(mContext, view);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    protected void focusOut(View v,int pos) {
        ((CircleImageView)v.findViewById(R.id.people_item_logo)).setBorderColor(Color.parseColor("#0000ff00"));
        v.findViewById(R.id.people_item_name).setSelected(false);
    }

    @Override
    protected void focusIn(View v,int pos) {
        ((CircleImageView)v.findViewById(R.id.people_item_logo)).setBorderColor(Color.parseColor("#ff00ff00"));
        v.findViewById(R.id.people_item_name).setSelected(true);

        if( HomeActivity.selected_fragment.equals("Featured") ) {
            Toast toast = new Toast(mContext);
            toast.setDuration(Toast.LENGTH_SHORT);

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.layout_toast, null);
            toast.setView(view);
            toast.setGravity(Gravity.CENTER | Gravity.FILL_HORIZONTAL, 0, 0);
            TextView tv = view.findViewById(R.id.toast_textview);
            tv.setText("Would you like to follow this person? Please long press the Remote Control to follow.");
            toast.show();
        }
    }

    @Override
    protected void onDataBinding(TVRecyclerViewAdapter.ViewHolder holder, int position) {
        if (holder instanceof PeopleVH) {
            String str = ((PeopleVH) holder).name.getText().toString();
            ((PeopleVH) holder).name.setText(str + " " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class PeopleVH extends TVRecyclerViewAdapter.ViewHolder {
        LinearLayout layout;
        ImageView logo;
        TextView name;

        public PeopleVH(Context context, View itemView) {
            super(context, itemView);
            layout = itemView.findViewById(R.id.item_one_people);

            logo = itemView.findViewById(R.id.people_item_logo);
            name = itemView.findViewById(R.id.people_item_name);
        }
    }

}
