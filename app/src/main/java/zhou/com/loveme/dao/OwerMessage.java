package zhou.com.loveme.dao;

import android.content.Context;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import zhou.com.loveme.bean.ContantsValue;
import zhou.com.loveme.utils.CurrentTimeUtil;
import zhou.com.loveme.utils.FileDataUtil;
import zhou.com.loveme.utils.Md5Util;
import zhou.com.loveme.utils.SpUtil;

/**
 * Created by zhou on 2017/8/14.
 *
 * 获取个人信息
 *
 * data_my_message
 *
 */

public class OwerMessage {


    public static void message(final Context context,String token,String uid){
        //String uid = SpUtil.getString(context, ContantsValue.UID,"");
        //String token = SpUtil.getString(context,ContantsValue.TOKEN,"");
        final String opt = "5";
        final String _t = CurrentTimeUtil.nowTime();
        String joint = "_t=" + _t + "&opt=" + opt + "&token="+token +"&uid="+ uid + ContantsValue.APP_ENCRYPTION_KEY;
        System.out.println("--------joint-------"+joint);
        final String _s = Md5Util.encoder(joint);
        System.out.println("--------_s-------"+_s);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("uid", uid)
                .add("token",token)
                .add("opt", opt)
                .add("_t", _t)
                .add("_s", _s)
                .build();
        Request request = new Request.Builder().url(ContantsValue.URL_ONE).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                FileDataUtil.saveDataToFile(context,response.body().string(),"data_my_message");
            }
        });
    }
}
