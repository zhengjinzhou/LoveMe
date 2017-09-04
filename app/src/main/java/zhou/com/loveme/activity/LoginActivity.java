package zhou.com.loveme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.nio.charset.Charset;

import zhou.com.loveme.R;
import zhou.com.loveme.dao.OwerMessage;
import zhou.com.loveme.dao.OwerSelectMessage;
import zhou.com.loveme.json.JsonLogin;
import zhou.com.loveme.bean.ContantsValue;
import zhou.com.loveme.utils.CurrentTimeUtil;
import zhou.com.loveme.utils.DES3Util;
import zhou.com.loveme.utils.FileDataUtil;
import zhou.com.loveme.utils.Md5Util;
import zhou.com.loveme.utils.SpUtil;
import zhou.com.loveme.utils.ToastUtil;


public class LoginActivity extends Activity {

    private EditText et_login_account;
    private EditText et_login_password;
    private Button bt_login_ensure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //获取
        initUI();
        //确定登录验证
        initService();
    }

    private void initService() {
        bt_login_ensure.setOnClickListener(new View.OnClickListener() {

            private String resultJson;

            @Override
            public void onClick(View view) {
                String loginAccount = et_login_account.getText().toString().trim();
                String loginPassword = et_login_password.getText().toString().trim();
                Log.i("loginAccount",loginAccount);
                Log.i("loginAccount",loginPassword);
                if(TextUtils.isEmpty(loginAccount) || TextUtils.isEmpty(loginPassword)){
                    ToastUtil.show(getApplicationContext(),"输入不能为空");
                }else{
                    SpUtil.putString(getApplicationContext(),ContantsValue.PASSWORD,loginPassword);//保存登录密码

                    //服务器需要的数据
                    String actNumber = loginAccount;
                    String pwd = DES3Util.encrypt3DES(loginPassword,ContantsValue.ENCRYPTION_KEY,Charset.forName("UTF-8"));
                    String opt = "2";
                    String _t = CurrentTimeUtil.nowTime();
                    String joint = "_t=" + _t + "&actNumber=" + actNumber + "&opt=" + opt +"&pwd=" + pwd + ContantsValue.APP_ENCRYPTION_KEY;
                    String _s = Md5Util.encoder(joint);
                    System.out.println("拼接后_t的数据--------"+joint);

                    //联网，获取返回信息  Okhttp框架
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = new FormEncodingBuilder().add("actNumber", actNumber)
                            .add("pwd", pwd)
                            .add("opt", opt)
                            .add("_t", _t)
                            .add("_s", _s)
                            .build();
                    Request request = new Request.Builder().url(ContantsValue.URL_ONE).post(requestBody).build();
                    okHttpClient.newCall(request).enqueue(callback);

                }
            }

            private Callback callback = new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    setResult(e.getMessage(), false);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    setResult(response.body().string(), true);

                }
            };
        });
    }
    private void setResult(String message, boolean b) {
        Gson gson = new Gson();
        JsonLogin jsonLogin = gson.fromJson(message, JsonLogin.class);
        String error = jsonLogin.getError();
        String actNumber = jsonLogin.getAccountNumber();
        final String msg = jsonLogin.getMsg();
        String token = jsonLogin.getToken();
        String uid = jsonLogin.getUid();
        /**
         * 将uid和token保存到sp中
         */
        SpUtil.putString(getApplicationContext(),ContantsValue.TOKEN,token);
        SpUtil.putString(getApplicationContext(),ContantsValue.UID,uid);

        /**
         * 加载服务器数据
         * data_select_message
         * data_my_message
         * 将他们保存到应用文件中去
         *
         * 之前太快了，即token和uid存进sp和读取出来的时间慢与程序读取文件中的数据，所以造成错误2014414
         */
        OwerSelectMessage.message(getApplicationContext(),token,uid);//获取个人信息设置选项
        OwerMessage.message(getApplicationContext(),token,uid);//获取个人信息

        String data_select_message = FileDataUtil.loadDataFile(getApplicationContext(), "data_select_message");
        System.out.println("data_select_message"+data_select_message);
        System.out.println("=====请求后返回的error值====="+error);
        Intent intent;
        if(error.equals("-1")){

            intent = new Intent(getApplicationContext(),DetailsActivity.class);
            startActivity(intent);
            finish();
        }else if(error.equals("-2")){
            intent = new Intent(getApplicationContext(),BoundPhoneActivity.class);
            intent.putExtra("data_token",token);
            startActivity(intent);
        }else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.show(getApplicationContext(),msg);
                }
            });
        }
    }


    private void initUI() {
        et_login_account = findViewById(R.id.et_login_account);
        et_login_password = findViewById(R.id.et_login_password);
        bt_login_ensure = findViewById(R.id.bt_login_ensure);
    }
}

