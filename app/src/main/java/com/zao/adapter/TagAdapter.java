package com.zao.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.zao.bean.TagBean;
import com.zao.utils.LogZ;
import com.zao.zouz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/4/29 16:01
 */
public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {

    private List<TagBean> tagList;

    private boolean isSelected = false;

    private List<TagBean> selectList;
    private OnItemClickListener mOnItemClickListener;

    public TagAdapter(List<TagBean> tagList) {
        this.tagList = tagList;
        selectList = new ArrayList<>();
    }

    @Override
    public TagAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final TagAdapter.ViewHolder holder, final int position) {
        holder.mTextView.setText(tagList.get(position).getTag_name());
        holder.itemView.setTag(tagList.get(position));
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected = !holder.mTextView.isSelected();
                if (isSelected) {
                    holder.mTextView.setSelected(true);
                    holder.mTextView.setBackgroundResource(R.drawable.tag_checked_bg);
                    selectList.add(tagList.get(position));
                } else {
                    holder.mTextView.setSelected(false);
                    holder.mTextView.setBackgroundResource(R.drawable.tag_normal_bg);
                    selectList.remove(tagList.get(position));
                }
                mOnItemClickListener.onClick(position);

                for(int i = 0; i < selectList.size();i++){
                    LogZ.e(selectList.get(i).toString());
                }
            }
        });

        holder.mTextView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                mOnItemClickListener.onLongClick(position);
                return false;
            }
        });
    }

       public interface OnItemClickListener{
           void onClick(int position);
           void onLongClick( int position);
       }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
             this.mOnItemClickListener=onItemClickListener;
        }

        public void setOnItemLongClickListener(OnItemClickListener onItemLongClickListener ){
             this.mOnItemClickListener=onItemLongClickListener;
        }


    @Override
    public int getItemCount() {
        return tagList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tag_tv);
        }
    }

    public List<TagBean> getSelectData(){
        return selectList;
    }
}
