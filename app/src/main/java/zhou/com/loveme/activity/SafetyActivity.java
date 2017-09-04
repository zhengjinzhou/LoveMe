package zhou.com.loveme.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zhou.com.loveme.R;
import zhou.com.loveme.bean.ContantsValue;
import zhou.com.loveme.utils.SpUtil;
import zhou.com.loveme.utils.ToastUtil;

public class SafetyActivity extends Activity implements View.OnClickListener{

    private static final int RESULT_OK = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);
        initUI();
    }

    private void initUI() {
        //获取控件
        ImageView iv_back = findViewById(R.id.iv_back);
        LinearLayout ll_alter = findViewById(R.id.ll_alter);
        TextView tv_phone = findViewById(R.id.tv_phone);

        String phone = SpUtil.getString(getApplicationContext(), ContantsValue.TEL_PHONE, "");
        tv_phone.setText(phone);
        //设置点击事件
        iv_back.setOnClickListener(this);
        ll_alter.setOnClickListener(this);
    }

    /**
     * 对点击事件进行处理
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.iv_back:
                //finish();这个可以，但是可能要返回参数，先不用这个了
                intent = new Intent();
                intent.putExtra("dd","SafetyActivity");
                setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                finish();//此处一定要调用finish()方法
                break;
            case R.id.ll_alter:
                ToastUtil.show(getApplicationContext(),"修改密码");
                showAlterPawDialog();
                break;
        }
    }

    private void showAlterPawDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog; dialog = builder.create();
        final View inflate = View.inflate(getApplicationContext(), R.layout.dialog_alter_password, null);
        dialog.setView(inflate,0,0,0,0);
        dialog.show();
        Button bt_list_ensure = inflate.findViewById(R.id.bt_list_ensure);
        Button bt_list_cancel = inflate.findViewById(R.id.bt_list_cancel);
        bt_list_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et_old_password = inflate.findViewById(R.id.et_old_password);
                EditText et_new_password = inflate.findViewById(R.id.et_new_password);
                EditText et_ensure_password = inflate.findViewById(R.id.et_ensure_password);

                String oldPassword = et_old_password.getText().toString().trim();
                String newPassword = et_new_password.getText().toString().trim();
                String ensurePassword = et_ensure_password.getText().toString().trim();

                //确定旧密码是不是和刚开始输入的同样，不同意则不行（if else）
                if(TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword) || TextUtils.isEmpty(ensurePassword)){
                    ToastUtil.show(getApplicationContext(),"输入不能为空");
                }else{
                    //获取初始密码
                    String loginPassword = SpUtil.getString(getApplicationContext(), ContantsValue.PASSWORD, "");
                    if(oldPassword.equals(loginPassword)){

                        //更改密码逻辑   判断两密码一致不  要保存到本地，以及发送到服务器
                        if(newPassword.equals(ensurePassword)){
                            SpUtil.putString(getApplicationContext(),ContantsValue.PASSWORD,newPassword);//更改本地到密码
                            /**
                             * 发送到服务器更新   暂代 记得要mdes加密上传
                             */
                            ToastUtil.show(getApplicationContext(),"修改密码成功");
                            dialog.dismiss();
                        }else{
                            ToastUtil.show(getApplicationContext(),"新密码与确认密码不一致");
                        }
                    }else{
                        ToastUtil.show(getApplicationContext(),"原始密码不正确");
                    }
                }
            }
        });
        bt_list_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
