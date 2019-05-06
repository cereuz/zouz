package com.zao.httpdownload;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.zao.utils.ApkUtils;
import com.zao.utils.Constant;
import com.zao.utils.LogZ;
import com.zao.utils.ToastUtil;
import com.zao.zouz.R;

import java.io.File;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/25 10:32
 */
public class UrlDownloadIntentService extends IntentService {

    private String mDownloadFileName;
    private Context mContext;

    public UrlDownloadIntentService() {
        super("InitializeService");
        mContext = this;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String downloadUrl = intent.getExtras().getString("download_url");
        final int downloadId = intent.getExtras().getInt("download_id");
        mDownloadFileName = intent.getExtras().getString("download_file");

        LogZ.e("download_url --" + downloadUrl);
        LogZ.e("download_file --" + mDownloadFileName);

        final File file = new File(Constant.APP_ROOT_PATH_PRE + Constant.ZOU_DIR + mDownloadFileName);
        long range = 0;
        int progress = 0;
        if (file.exists()) {
            range = SPDownloadUtil.getInstance().get(downloadUrl, 0);
            progress = (int) (range * 100 / file.length());
            if (range == file.length()) {
//                installApp(file);
//                ApkUtils.installAPk(mContext,file);
                ToastUtil.showT(mContext,"下载成功！");
                return;
            }
        }

        LogZ.e("range = " + range);

        final RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notify_download);
        remoteViews.setTextViewText(R.id.tv_title,mDownloadFileName);
        remoteViews.setProgressBar(R.id.pb_progress, 100, progress, false);
        remoteViews.setTextViewText(R.id.tv_progress, "已下载 " + progress + "%");

        final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final Notification notification = new NotificationCompat.Builder(this, Constant.CHANNEL_ID_ZOU_CHAT)
                .setContent(remoteViews)
                .setTicker("正在下载")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(null,0)
                .build();
        manager.notify(downloadId,notification);

        UrlRetrofitHttp.getInstance().downloadFile(range, downloadUrl, mDownloadFileName, new DownloadCallBack() {
            @Override
            public void onProgress(int progress) {
                remoteViews.setProgressBar(R.id.pb_progress, 100, progress, false);
                remoteViews.setTextViewText(R.id.tv_progress, "已下载 " + progress + "%");
                manager.notify(downloadId, notification);
            }

            @Override
            public void onCompleted() {
                LogZ.e("下载完成");
                manager.cancel(downloadId);
//                installApp(file);
                ApkUtils.installAPk(mContext,file);
            }

            @Override
            public void onError(String msg) {
                manager.cancel(downloadId);
                LogZ.e("下载发生错误--" + msg);
            }
        });
    }
}
