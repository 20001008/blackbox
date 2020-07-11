package com.black.box.ui.list;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.black.box.R;
import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class list_home_helper extends BaseQuickAdapter {
private List<list_home_note> data;
    public list_home_helper(int layoutResId, @Nullable List<list_home_note> data) {
        super(layoutResId, data);
        this.data=data;

    }

    public list_home_helper(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Object o) {
    baseViewHolder.setIsRecyclable(false);
        int pos=baseViewHolder.getAdapterPosition()-1;
        TextView tittle=baseViewHolder.itemView.findViewById(R.id.list_home_tittle);
        TextView subtittle=baseViewHolder.itemView.findViewById(R.id.list_home_subtittle);
        TextView time=baseViewHolder.itemView.findViewById(R.id.list_home_time);
        TextView user=baseViewHolder.itemView.findViewById(R.id.list_home_user);
        tittle.setText(data.get(pos).getTittle());
        tittle.getPaint().setFakeBoldText(true);
        subtittle.setText(data.get(pos).getSubTittle());
        time.setText(data.get(pos).getTime());
        user.setText(data.get(pos).getUser());
        user.getPaint().setFakeBoldText(true);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
}
