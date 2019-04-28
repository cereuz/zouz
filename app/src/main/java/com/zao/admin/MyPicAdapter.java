package com.zao.admin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zao.zouz.R;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/4/22 10:07
 */
public class MyPicAdapter extends RecyclerView.Adapter<MyPicAdapter.ViewHolder> {

    private Context context;
    public String[] data;

    public MyPicAdapter(Context context, String[] data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid_recycleview_0306, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvItem.setText(data[position]);
        holder.ivItem.setImageResource(R.mipmap.ic_launcher);

        //setTag为当前view添加状态，之后直接点击调用getTag便可获取其position
//        holder.itemView.setTag(position);
        //当然也可以将数据传给setTag
        holder.itemView.setTag(data[position]);

        View itemView = ((LinearLayout) holder.itemView).getChildAt(0);
        setClickListener(holder, position);

/*        //可以直接在onBindViewHolder中实现点击事件。 也可以在调用此Adapter的方法中实现点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }


    //设置点击事件
    private void setClickListener(final ViewHolder holder, int position) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    int pos = holder.getLayoutPosition()-1;//默认是第一个开始
                    int pos = holder.getLayoutPosition();//默认是第一个开始
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                int pos = holder.getLayoutPosition()-1;//默认是第一个开始
                int pos = holder.getLayoutPosition();//默认是第一个开始
                mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                return true;//返回true可以屏蔽点击监听的响应
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.length;
    }


    private OnItemClickListener mOnItemClickListener;//声明接口

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItem;
        public ImageView ivItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.tv_item_grid);
            ivItem = (ImageView) itemView.findViewById(R.id.iv_item_grid);

            //设置图片的宽高
            ViewGroup.LayoutParams params = ivItem.getLayoutParams();
            params.height = 100;
            params.width = 100;
            ivItem.setLayoutParams(params);
        }
    }
}
