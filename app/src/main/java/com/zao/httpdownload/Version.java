package com.zao.httpdownload;

/**
 * @author : zw
 * @email : zsky@live.com
 * @motto : To be, or not to be.
 * @date : 2019/3/26 15:21
 */
public class Version {

        private String versionName;
        private String versionDes;
        private String versionCode;
        private String downloadUrl;

    public String getVersionName() {
        return versionName;
    }

    public String getVersionDes() {
        return versionDes;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    @Override
    public String toString() {
        return  " 版本名称：" + versionName + ",\n" +
                " 版本描述：'" + versionDes + ",\n" +
                " 版本号：" + versionCode + ",\n" +
                " 下载链接：" + downloadUrl + "\n\n" +

                "Version{" +
                "versionName='" + versionName + '\'' +
                ", versionDes='" + versionDes + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
