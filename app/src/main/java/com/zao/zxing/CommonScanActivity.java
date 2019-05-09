package com.zao.zxing;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.zao.utils.StatusBarUtil2;
import com.zao.zouz.R;
import com.zao.zxing.zxing.ScanListener;
import com.zao.zxing.zxing.ScanManager;
import com.zao.zxing.zxing.decode.DecodeThread;
import com.zao.zxing.zxing.decode.Utils;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/5/8 9:06
 */
public class CommonScanActivity extends AppCompatActivity implements ScanListener, View.OnClickListener {
    static final String TAG = CommonScanActivity.class.getSimpleName();
    SurfaceView scanPreview = null;
    View scanContainer;
    View scanCropView;
    ImageView scanLine;
    ScanManager scanManager;
    TextView iv_light;
    TextView qrcode_g_gallery;
    TextView qrcode_ic_back;
    final int PHOTOREQUESTCODE = 1111;

    Button btn_service_register_rescan;
    ImageView myImageView;
    ImageView iv_authorize_return;

    private int scanMode;//扫描模型（条形，二维码，全部）

    TextView tv_common_title_TV_center;
    TextView tv_scan_hint;
    TextView tv_scan_result;

    int width;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil2.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil2.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil2.setStatusBarDarkTheme(this, false)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil2.setStatusBarColor(this,0x55000000);
        }

//        android:fitsSystemWindows="true"      //注意这个放的位置。
        //设置下边的控件直接作为状态栏的背景。沉浸模式
/*        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }*/

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_scan_code);
        scanMode = getIntent().getIntExtra(ConstantZ.REQUEST_SCAN_MODE, ConstantZ.REQUEST_SCAN_MODE_ALL_MODE);
        initView();

/*        StatusBarUtil.setStatusBarLightMode(this,false); //修改字体颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlack));//设置状态栏的背景色
        }*/
    }

    void initView() {

        btn_service_register_rescan = (Button)findViewById(R.id.service_register_rescan);
        myImageView =  (MyImageView)findViewById(R.id.scan_image);
        iv_authorize_return = (ImageView) findViewById(R.id.authorize_return);
        tv_common_title_TV_center = (TextView) findViewById(R.id.common_title_TV_center);
        tv_scan_hint = (TextView) findViewById(R.id.scan_hint);
        tv_scan_result = (TextView) findViewById(R.id.tv_scan_result);

        switch (scanMode) {
            case DecodeThread.BARCODE_MODE:
                tv_common_title_TV_center.setText(R.string.scan_barcode_title);
                tv_scan_hint.setText(R.string.scan_barcode_hint);
                break;
            case DecodeThread.QRCODE_MODE:
                tv_common_title_TV_center.setText(R.string.scan_qrcode_title);
                tv_scan_hint.setText(R.string.scan_qrcode_hint);
                break;
            case DecodeThread.ALL_MODE:
                tv_common_title_TV_center.setText(R.string.scan_allcode_title);
                tv_scan_hint.setText(R.string.scan_allcode_hint);
                break;
        }

        scanPreview = (SurfaceView) findViewById(R.id.capture_preview);
        scanContainer = findViewById(R.id.capture_container);
        scanCropView = findViewById(R.id.capture_crop_view);
        scanLine = (ImageView) findViewById(R.id.capture_scan_line);
        qrcode_g_gallery = (TextView) findViewById(R.id.qrcode_g_gallery);
        qrcode_g_gallery.setOnClickListener(this);
        qrcode_ic_back = (TextView) findViewById(R.id.qrcode_ic_back);
        qrcode_ic_back.setOnClickListener(this);
        iv_light = (TextView) findViewById(R.id.iv_light);
        iv_light.setOnClickListener(this);
        btn_service_register_rescan.setOnClickListener(this);
        iv_authorize_return.setOnClickListener(this);
        //构造出扫描管理器
        scanManager = new ScanManager(this, scanPreview, scanContainer, scanCropView, scanLine, scanMode, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        scanManager.onResume();
        btn_service_register_rescan.setVisibility(View.INVISIBLE);
        myImageView.setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        super.onPause();
        scanManager.onPause();
    }

    /**
     *
     */
    public void scanResult(Result rawResult, Bundle bundle) {
        //扫描成功后，扫描器不会再连续扫描，如需连续扫描，调用reScan()方法。
        //scanManager.reScan();
//		Toast.makeText(that, "result="+rawResult.getText(), Toast.LENGTH_LONG).show();

        if (!scanManager.isScanning()) { //如果当前不是在扫描状态
            //设置再次扫描按钮出现
            btn_service_register_rescan.setVisibility(View.VISIBLE);
            myImageView.setVisibility(View.VISIBLE);
            Bitmap barcode = null;
            byte[] compressedBitmap = bundle.getByteArray(DecodeThread.BARCODE_BITMAP);
            if (compressedBitmap != null) {
                barcode = BitmapFactory.decodeByteArray(compressedBitmap, 0, compressedBitmap.length, null);
                barcode = barcode.copy(Bitmap.Config.ARGB_8888, true);
            }
            myImageView.setImageBitmap(barcode);
        }
        btn_service_register_rescan.setVisibility(View.VISIBLE);
        myImageView.setVisibility(View.VISIBLE);
        tv_scan_result.setVisibility(View.VISIBLE);
        tv_scan_result.setText("结果：" + rawResult.getText());
    }

    void startScan() {
        if (btn_service_register_rescan.getVisibility() == View.VISIBLE) {
            btn_service_register_rescan.setVisibility(View.INVISIBLE);
            myImageView.setVisibility(View.GONE);
            scanManager.reScan();
        }
    }

    @Override
    public void scanError(Exception e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        //相机扫描出错时
        if (e.getMessage() != null && e.getMessage().startsWith("相机")) {
            scanPreview.setVisibility(View.INVISIBLE);
        }
    }

    public void showPictures(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String photo_path;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTOREQUESTCODE:
                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor cursor = this.getContentResolver().query(data.getData(), proj, null, null, null);
                    if (cursor.moveToFirst()) {
                        int colum_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        photo_path = cursor.getString(colum_index);
                        if (photo_path == null) {
                            photo_path = Utils.getPath(getApplicationContext(), data.getData());
                        }
                        scanManager.scanningImage(photo_path);
                    }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qrcode_g_gallery:
                showPictures(PHOTOREQUESTCODE);
                break;
            case R.id.iv_light:
                scanManager.switchLight();
                break;
            case R.id.qrcode_ic_back:
                finish();
                break;
            case R.id.service_register_rescan://再次开启扫描
                startScan();
                break;
            case R.id.authorize_return:
                finish();
                break;
            default:
                break;
        }
    }
}
