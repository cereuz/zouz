package com.zao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zao.utils.Constant;
import com.zao.zbanner.RecyclerViewBannerBase;
import com.zao.zouz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/5/5 17:06
 */
public class NormalActivity extends AppCompatActivity {
    RecyclerViewBannerNormal banner, banner2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner2);
        banner = (RecyclerViewBannerNormal) findViewById(R.id.banner);
        banner2 = (RecyclerViewBannerNormal) findViewById(R.id.banner2);
        List<String> list = new ArrayList<>();
        Constant.addUrls(list);

        banner.initBannerImageView(list, new RecyclerViewBannerBase.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(NormalActivity.this, "clicked:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        banner2.initBannerImageView(list, new RecyclerViewBannerBase.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(NormalActivity.this, "clicked:" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

}

