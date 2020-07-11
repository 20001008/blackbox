package com.black.box.ui.list;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.black.box.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class list_user_helper extends BaseQuickAdapter {
    private List<list_user_note> data;
    public list_user_helper(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
        this.data=data;
    }

    public list_user_helper(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Object o) {
        baseViewHolder.setIsRecyclable(false);
        int pos=0;
        if (this.hasHeaderLayout())
        {
            pos=baseViewHolder.getAdapterPosition()-1;
        }else
        {
             pos=baseViewHolder.getAdapterPosition();
        }
        ImageView icon=baseViewHolder.itemView.findViewById(R.id.list_user_icon);
        TextView tittle=baseViewHolder.itemView.findViewById(R.id.list_user_tittle);
        Glide.with(getContext()).load(getContext().getResources().getDrawable(data.get(pos).getIcon())).into(icon);
        tittle.setText(data.get(pos).getName());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
}
