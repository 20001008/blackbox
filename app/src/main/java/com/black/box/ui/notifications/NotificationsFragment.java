package com.black.box.ui.notifications;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.black.box.MainActivity;
import com.black.box.R;

import java.util.ArrayList;
import java.util.List;

import com.black.box.ui.home.HomeFragment;
import com.black.box.ui.list.*;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private List<list_user_note> data=new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        RecyclerView recyclerView=root.findViewById(R.id.user_recycle);
        data.add(new list_user_note(R.mipmap.iconmonstr_document_thin_24,"我的帖子"));
        data.add(new list_user_note(R.mipmap.iconmonstr_clock_thin_24,"浏览历史"));
        data.add(new list_user_note(R.mipmap.iconmonstr_mail_thin_24,"我的消息"));
        data.add(new list_user_note(R.mipmap.iconmonstr_speech_bubble_comments_thin_24,"我的私信"));
        data.add(new list_user_note(R.mipmap.iconmonstr_clock_thin_24,"浏览历史"));
        list_user_helper user_helper=new list_user_helper(R.layout.layout_list_user,data);
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL,false));
      //  View view=View.inflate(getContext(),R.layout.layout_user_empty,null);
        View view= LinearLayout.inflate(inflater.getContext(),R.layout.layout_user_empty,null);
        ImageView head_bg=view.findViewById(R.id.user_empty_bg);
        ImageView head_icon=view.findViewById(R.id.user_empty_icon);
        TextView tittle=view.findViewById(R.id.user_empty_tittle);
        TextView subtittle=view.findViewById(R.id.user_empty_subtittle);
        TextView qb_text=view.findViewById(R.id.user_empty_qb);
        TextView jf_text=view.findViewById(R.id.user_empty_jf);
        TextView qb_num=view.findViewById(R.id.user_empty_qb_num);
        TextView jf_num=view.findViewById(R.id.user_empty_jf_num);
        qb_num.getPaint().setFakeBoldText(true);
        jf_num.getPaint().setFakeBoldText(true);
        qb_text.getPaint().setFakeBoldText(true);
        jf_text.getPaint().setFakeBoldText(true);
        Glide.with(inflater.getContext()).asGif().load(R.mipmap.head).diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop().listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                e.printStackTrace();
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                resource.setLoopCount(100);
                resource.start();
                return false;
            }
        }).into(head_icon);
        tittle.setTextColor(Color.WHITE);
        tittle.getPaint().setFakeBoldText(true);
        tittle.setShadowLayer(1,1,1,0xff666666);
        subtittle.setTextColor(Color.WHITE);
        subtittle.getPaint().setFakeBoldText(true);
        subtittle.setShadowLayer(1,1,1,0xff666666);
        Drawable background_draw=getResources().getDrawable(R.mipmap.bg1);
       background_draw.setColorFilter(Color.parseColor("#C7C7C7"), PorterDuff.Mode.MULTIPLY);
        Glide.with(inflater.getContext()).load(background_draw).into(head_bg);
        user_helper.setHeaderView(view);
        recyclerView.setAdapter(user_helper);


        return root;
    }
}