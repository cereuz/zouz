package com.zao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zao.zbanner2.BannerView;
import com.zao.zouz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/5/5 17:18
 */
public class BannerTextActivity extends AppCompatActivity implements View.OnClickListener  {

    public static String[] titles = new String[]{
            "每周7件Tee不重样",
            "俏皮又知性 适合上班族的漂亮衬衫",
            "名侦探柯南",
            "境界之轮回",
            "我的英雄学院",
            "全职猎人",
    };
    public static String[] urls = new String[]{//750x500
            "https://img3.doubanio.com/view/photo/l/public/p2360494161.webp",
            "https://img1.doubanio.com/view/photo/l/public/p2320164299.webp",
            "https://img3.doubanio.com/view/photo/l/public/p2535136785.webp",
            "https://img3.doubanio.com/view/photo/l/public/p2535136771.webp",
            "https://img1.doubanio.com/view/photo/l/public/p2535136759.webp",
            "https://img1.doubanio.com/view/photo/l/public/p2454230998.webp",
/*            "https://s2.mogucdn.com/mlcdn/c45406/170422_678did070ec6le09de3g15c1l7l36_750x500.jpg",
            "https://s2.mogucdn.com/mlcdn/c45406/170420_1hcbb7h5b58ihilkdec43bd6c2ll6_750x500.jpg",
            "http://s18.mogucdn.com/p2/170122/upload_66g1g3h491bj9kfb6ggd3i1j4c7be_750x500.jpg",
            "http://s18.mogucdn.com/p2/170204/upload_657jk682b5071bi611d9ka6c3j232_750x500.jpg",
            "http://s16.mogucdn.com/p2/170204/upload_56631h6616g4e2e45hc6hf6b7g08f_750x500.jpg",
            "http://s16.mogucdn.com/p2/170206/upload_1759d25k9a3djeb125a5bcg0c43eg_750x500.jpg"*/
    };
    public static class BannerItem {
        public String image;
        public String title;

        @Override
        public String toString() {
            return title;
        }
    }

    public static class BannerViewFactory implements BannerView.ViewFactory<BannerItem> {
        @Override
        public View create(BannerItem item, int position, ViewGroup container) {
            ImageView iv = new ImageView(container.getContext());
            RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA);
            Glide.with(container.getContext().getApplicationContext()).load(item.image).apply(options).into(iv);
            return iv;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bannertext);

        List<BannerItem> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            BannerItem item = new BannerItem();
            item.image = urls[i];
            item.title = titles[i];

            list.add(item);
        }

        final BannerView banner1 = (BannerView) findViewById(R.id.banner1);
        banner1.setViewFactory(new BannerViewFactory());
        banner1.setDataList(list);
        banner1.start();

        final BannerView banner2 = (BannerView) findViewById(R.id.banner2);
        banner2.setViewFactory(new BannerViewFactory());
        banner2.setDataList(list);
        banner2.start();

    }


    @Override
    public void onClick(View v) {

    }
}
