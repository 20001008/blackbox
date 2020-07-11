package com.black.box.ui.home;

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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.ArrayList;
import java.util.List;
import com.black.box.ui.list.*;
public class HomeFragment extends Fragment {
    private List<list_home_note> data=new ArrayList<>();
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView home_head= root.findViewById(R.id.home_head);
        ImageView home_icon1=root.findViewById(R.id.home_icon1);
        FastScrollRecyclerView fastScrollRecyclerView=root.findViewById(R.id.home_recycle);
        SmartRefreshLayout smartRefreshLayout=root.findViewById(R.id.home_smart);
        for (int n=0;n<2;n++)
        {
            data.add(new list_home_note("恋如雨止","2020-7-9","总结一下找工作的几个要点","大学毕业走向社会的这段时间，对于刚进社会的学生而言，是一段充满刺激也满是迷茫的过程。尽管在此之前，或多或少有过职场实习经验。但要注意，实习生于正式工之间有着天壤之别。这不同之处不仅仅只是表现在薪酬方面，还表现在用人单位的培养意愿、接触到的公司资源等方方面面。",null,null,null,null));
        }
        list_home_helper helper= new list_home_helper(R.layout.layout_list_home,data);
        View view =LayoutInflater.from(getContext()).inflate(R.layout.layout_home_empty,null);
        helper.setHeaderView(view);
        fastScrollRecyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL,false));
        fastScrollRecyclerView.setAdapter(helper);

//添加

        Glide.with(HomeFragment.this).asGif().load(R.mipmap.head).diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop().listener(new RequestListener<GifDrawable>() {
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
        }).into(home_head);
        Drawable background_draw=getResources().getDrawable(R.mipmap.bg1);
        background_draw.setColorFilter(Color.parseColor("#C7C7C7"), PorterDuff.Mode.MULTIPLY);
        Glide.with(HomeFragment.this).load(R.mipmap.iconmonstr_speech_bubble_comments_thin_32).centerCrop().into(home_icon1);
        home_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return root;
    }
}