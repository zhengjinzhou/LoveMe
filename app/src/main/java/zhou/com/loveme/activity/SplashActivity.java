package zhou.com.loveme.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import zhou.com.loveme.R;
import zhou.com.loveme.bean.ContantsValue;
import zhou.com.loveme.utils.SpUtil;
import zhou.com.loveme.utils.StreamUtil;

public class SplashActivity extends Activity {

    private static final String tag = "SplashActivity";
    private static final int UPDATE_VERSION = 0;
    private static final int ENTER_HOME = 1;
    private static final int URL_ERROR = 2;
    private static final int IO_ERROR = 3;
    private static final int JSON_ERROR = 4;
    private String mVersionDes;
    private String mDownloadUrl;

    /**
     * 消息机制
     * 因为要更新ui，即弹出对话框（其实这是不是更新ui我都忘了）
     *
     */
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_VERSION:
                    showUpdateDialog();
                    break;
                case  ENTER_HOME:
                    enterHome();
                    break;
                case  URL_ERROR:
                    enterHome();
                    break;
                case  IO_ERROR:
                    enterHome();
                    break;
                case  JSON_ERROR:
                    enterHome();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initUI();
    }

    private void initUI() {
        TextView tv_version_name = findViewById(R.id.tv_version_name);
        RelativeLayout rl_root = findViewById(R.id.rl_root);
        //实现淡入效果
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(3000);
        rl_root.startAnimation(alphaAnimation);
        //写入版本名称
        tv_version_name.setText("版本名称：" + getVersionName());//获取数据，即版本名称
        //检查版本号,使用到localVersionCode
        checkViewsion();
    }

    private void checkViewsion() {
        final int localVersionCode = getVersionCode();//获取本地版本号
        new Thread() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                Message message = Message.obtain();
                try {
                    /**
                     * 地址到时候写上
                     */
                    URL url = new URL("地址到时候写上");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(3000);
                    if (connection.getResponseCode() == 200) {
                        InputStream is = connection.getInputStream();
                        String json = StreamUtil.streamToString(is);
                        Log.i(tag, json);
                        JSONObject jsonObject = new JSONObject(json);
                        String versionName = jsonObject.getString("versionName");
                        mVersionDes = jsonObject.getString("versionDes");
                        String versionCode = jsonObject.getString("versionCode");
                        mDownloadUrl = jsonObject.getString("downloadUrl");
                        if (localVersionCode < Integer.parseInt(versionCode)) {
                            //弹出对话框，提示用户更新    需要使用到消息机制
                            //showUpdateDialog();
                            message.what = UPDATE_VERSION;
                        } else {
                            //进入应用程序主界面
                            message.what = ENTER_HOME;
                        }

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    message.what = URL_ERROR;
                } catch (IOException e) {
                    e.printStackTrace();
                    message.what = IO_ERROR;
                } catch (JSONException e) {
                    e.printStackTrace();
                    message.what = JSON_ERROR;
                }finally {
                    //指定睡眠时间，请求网络的时常超过4秒则不做处理
                    //请求网络的时常小于4秒，则强制让其睡眠4秒
                    long endTime = System.currentTimeMillis();
                    if(endTime-startTime<4000){
                        try {
                            Thread.sleep(4000-(endTime-startTime));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mHandler.sendMessage(message);
                }
            }
        }.start();
    }

    /**
     * 弹出对话框，提示用户更新
     *
     * @
     */
    private void showUpdateDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.fall_in_love);
        builder.setTitle("版本更新");
        builder.setMessage(mVersionDes);
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //下载apk，apk链接dowmloadUrl
                downloadApk();
            }
        });
        builder.setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                enterHome();
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    /**
     * 进行下载apk
     */
    private void downloadApk() {
        //判断sd卡是否可用，是否挂在上
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //获取sd卡路径
            String path = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"LoveYou.apk";
            //发送请求，获取apk，并且放在指定路径
            HttpUtils httpUtils = new HttpUtils();
            httpUtils.download(mDownloadUrl, path, new RequestCallBack<File>() {
                @Override
                public void onSuccess(ResponseInfo<File> responseInfo) {
                    Log.i(tag,"下载成功");
                    File result = responseInfo.result;
                    installApk(result);//提示用户安装
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Log.i(tag,"下载失败");
                }

                @Override
                public void onStart() {
                    Log.i(tag,"刚开始下载");
                    super.onStart();
                }

                @Override
                public void onLoading(long total, long current, boolean isUploading) {
                    Log.i(tag,"下载中......");
                    Log.i(tag,"total="+total);
                    Log.i(tag,"current="+current);
                    super.onLoading(total, current, isUploading);
                }
            });
        }
    }

    /**
     * 安装对应的apk
     * @param result
     */
    private void installApk(File result) {
        //系统应用界面，安装apk入口
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(result),"application/vnd.android.package-archive");
        startActivityForResult(intent,0);
    }

    /**
     * 开启activity后，返回结果调用的方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        enterHome();
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 进入主界面
     */
    private void enterHome() {
        String login_over = SpUtil.getString(getApplicationContext(), ContantsValue.UID, "");
        String adapter = SpUtil.getString(getApplicationContext(), ContantsValue.OVER_ADAPTER, "");
        Intent intent;
        if(!adapter.isEmpty()){
            if(!login_over.isEmpty()){
                intent = new Intent(this, HomeActivity.class);
            }else{
                intent = new Intent(this, LoginActivity.class);
            }
        }else{
            intent = new Intent(this, AdapterActivity.class);
        }
        startActivity(intent);
        finish();//开启新界面后，将导航界面关闭（导航界面只可见一次）
    }

    /**
     * 获取版本名称:清单文件中
     *
     * @return 应用版本号    返回null代表异常
     */
    private int getVersionCode() {
        PackageManager packageManager = getPackageManager();//获取包管理对象
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取版本名称:清单文件中
     *
     * @return 应用版本名称    返回null代表异常
     */
    private String getVersionName() {
        //1 包管理这对象
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
