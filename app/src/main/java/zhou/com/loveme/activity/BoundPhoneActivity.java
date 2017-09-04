package zhou.com.loveme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import zhou.com.loveme.R;
import zhou.com.loveme.json.JsonLogin;
import zhou.com.loveme.bean.ContantsValue;
import zhou.com.loveme.utils.CurrentTimeUtil;
import zhou.com.loveme.utils.DownTimeUtil;
import zhou.com.loveme.utils.Md5Util;
import zhou.com.loveme.utils.PhoneUtil;
import zhou.com.loveme.utils.SpUtil;
import zhou.com.loveme.utils.ToastUtil;

public class BoundPhoneActivity extends Activity {

    private static final int UPDATE_TEXT = 1;
    private EditText et_phone;
    private EditText et_verification;
    private TextView tv_get_verification;
    private Button bt_next;
    private String etPhone;

    /**
     * 发送验证码UI更新消息机制
     */
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    DownTimeUtil downTimeUtil = new DownTimeUtil(tv_get_verification, 60000, 1000);
                    downTimeUtil.start();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_phone);
        initUI();
        initData();
    }

    private void initData() {

        Intent intent = getIntent();
        final String token = intent.getStringExtra("data_token");//获取上一activity传来的值
        /**
         * 验证码业务逻辑 提交验证码
         */
        tv_get_verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etPhone = et_phone.getText().toString().trim();//获取手机号码
                System.out.println("=====etPhonoe====="+ etPhone);
                if(etPhone.isEmpty()){
                    ToastUtil.show(getApplicationContext(),"手机号码不能为空");
                }else if(!PhoneUtil.isPhoneNumber(etPhone)){
                    ToastUtil.show(getApplicationContext(),"请输入正确的手机号码");
                }else{

                    SpUtil.putString(getApplicationContext(),ContantsValue.TEL_PHONE,etPhone);//保存手机号码

                    String _t = CurrentTimeUtil.nowTime();
                    String joint = "_t=" + _t + "&mobile=" + etPhone + "&opt=" + "0"  + "&token="+token  + ContantsValue.APP_ENCRYPTION_KEY;
                    String _s = Md5Util.encoder(joint);

                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = new FormEncodingBuilder()
                            .add("token", token)
                            .add("mobile",etPhone)
                            .add("opt", "0")
                            .add("_t", _t)
                            .add("_s", _s)
                            .build();
                    Request request = new Request.Builder().url(ContantsValue.URL_ONE).post(requestBody).build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            Log.i("BoundPhoneActivity",e.getMessage());
                        }
                        @Override
                        public void onResponse(Response response) throws IOException {
                            String string = response.body().string();
                            try {
                                JSONObject jsonObject = new JSONObject(string);
                                final String msg = jsonObject.getString("msg");
                                String error = jsonObject.getString("error");
                                if(error.equals("-3")){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ToastUtil.show(getApplicationContext(),msg);
                                        }
                                    });
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.what = UPDATE_TEXT;
                            mHandler.sendMessage(message);
                        }
                    }).start();
                }
            }
        });
        /**
         * 下一步（提交验证码）的业务逻辑
         */
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取验证码
                String code = et_verification.getText().toString().trim();
                final String opt = "1";
                final String _t = CurrentTimeUtil.nowTime();
                String joint = "_t=" + _t +"&code="+code+ "&mobile=" + etPhone + "&opt=" + opt  + "&token="+ token  + ContantsValue.APP_ENCRYPTION_KEY;
                String _s = Md5Util.encoder(joint);

                if(TextUtils.isEmpty(code)){
                    ToastUtil.show(getApplicationContext(),"验证码不能为空");
                }
                else {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormEncodingBuilder()
                            .add("token", token)
                            .add("mobile", etPhone)
                            .add("code", code)
                            .add("opt", opt)
                            .add("_t", _t)
                            .add("_s", _s)
                            .build();
                    Request request = new Request.Builder().url(ContantsValue.URL_TWO).post(requestBody).build();
                    client.newCall(request).enqueue(callback);
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
        final String msg = jsonLogin.getMsg();
        if(error.equals("-1")){
            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(intent);
            finish();
        }else if(error.equals("-3")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.show(getApplicationContext(),msg);
                }
            });
        }
    }

    private void initUI() {
        et_phone = findViewById(R.id.et_phone);
        et_verification = findViewById(R.id.et_verification);
        tv_get_verification = findViewById(R.id.tv_get_verification);
        bt_next = findViewById(R.id.bt_next);
    }
}
