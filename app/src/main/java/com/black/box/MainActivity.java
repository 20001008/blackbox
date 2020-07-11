package com.black.box;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.ImageViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //沉浸
        if(Build.VERSION.SDK_INT>=21)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.WHITE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        DrawerLayout drawerLayout=findViewById(R.id.main_drawer);
        navigationView= drawerLayout.findViewById(R.id.main_navview);
         ImageView nav_imageView=navigationView.getHeaderView(0).findViewById(R.id.nav_imageView);
         ImageView nav_head=navigationView.getHeaderView(0).findViewById(R.id.nav_head);
        TextView nav_tittle=navigationView.getHeaderView(0).findViewById(R.id.nav_tittle);
        TextView nav_subtittle=navigationView.getHeaderView(0).findViewById(R.id.nav_subTittle);

        nav_tittle.getPaint().setFakeBoldText(true);
        nav_subtittle.getPaint().setFakeBoldText(true);
        nav_subtittle.setShadowLayer(1,1,1,0xff666666);
        nav_tittle.setShadowLayer(1,1,1,0xff666666);
        Glide.with(MainActivity.this).asGif().load(R.mipmap.head).diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop().listener(new RequestListener<GifDrawable>() {
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
        }).into(nav_head);
        Drawable background_draw=getResources().getDrawable(R.mipmap.bg1);
       background_draw.setColorFilter(Color.parseColor("#C7C7C7"), PorterDuff.Mode.MULTIPLY);
       Glide.with(MainActivity.this).load(background_draw).into(nav_imageView);
        final ConstraintLayout constraintLayout=findViewById(R.id.main_container);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                constraintLayout.setX(drawerView.getWidth() * slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
        //scrollview滑动禁用
    }

    private void setLoadOnce(Drawable resource) {
        // Glide 4.0 后没法再直接获取GifDecoder对象了,原因是因为GlideDrawable不再提供这个方法了。
        // 我这里是采用反射的方法获取到GifDecoder变量的,具体代码如下
        // 参考链接：https://www.aliyun.com/jiaocheng/1344.html
        try {
            Field gifStateField = GifDrawable.class.getDeclaredField("state");
            gifStateField.setAccessible(true);
            Class gifStateClass = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable$GifState");
            Field gifFrameLoaderField = gifStateClass.getDeclaredField("frameLoader");
            gifFrameLoaderField.setAccessible(true);
            Class gifFrameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader");
            Field gifDecoderField = gifFrameLoaderClass.getDeclaredField("gifDecoder");
            gifDecoderField.setAccessible(true);
            Class gifDecoderClass = Class.forName("com.bumptech.glide.gifdecoder.GifDecoder");
            Object gifDecoder = gifDecoderField.get(gifFrameLoaderField.get(gifStateField.get(resource)));
            Method getDelayMethod = gifDecoderClass.getDeclaredMethod("getDelay", int.class);
            getDelayMethod.setAccessible(true);
            GifDrawable drawable = (GifDrawable) resource;
            // 设置只播放一次
            drawable.setLoopCount(1000);
            // 获得总帧数
            int count = drawable.getFrameCount();
            int delay = 0;
            for (int i = 0; i < count; i++) {
                // 计算每一帧所需要的时间进行累加
                delay += (int) getDelayMethod.invoke(gifDecoder, i);
            }
           // handler.sendEmptyMessageDelayed(1, delay);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {

            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
