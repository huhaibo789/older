package com.future.awaker.main;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.future.awaker.R;
import com.future.awaker.databinding.ActivityMainBinding;
import com.future.awaker.home.HomeActivity;
import com.future.awaker.imageloader.ImageLoader;
import com.future.awaker.util.ConstantUtils;

/**
 * Created by ruzhan on 2017/7/12.
 */

public class MainActivity extends AppCompatActivity {

    private static final String SHOW_LAUNCH = "showLaunch";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean isShow = SPUtils.getInstance().getBoolean(SHOW_LAUNCH);
        long delayMillis = !isShow && ConstantUtils.isReleaseBuild() ? 2500 : 0;
        if (delayMillis > 0) {
            SPUtils.getInstance().put(SHOW_LAUNCH, true);
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ImageLoader.get().load(binding.mainIv, R.mipmap.main_start_icon, new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                        Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                           DataSource dataSource, boolean isFirstResource) {
                binding.mainTv.setText(R.string.launch_desc);
                return false;
            }
        });

        binding.mainIv.postDelayed(() -> {
            HomeActivity.launch(this);
            finish();
        }, delayMillis);
    }

    @Override
    public void onBackPressed() {

    }
}
