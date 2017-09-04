package zhou.com.loveme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import zhou.com.loveme.R;

public class HerNewsActivity extends Activity {

    private ImageView iv_back;
    private TextView tv_it_news;
    private ImageView iv_her_photo;
    private TextView tv_name;
    private TextView tv_age;
    private TextView tv_constellation;
    private TextView tv_professional;
    private TextView tv_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_her_news);
        initUI();
        initLogic();
    }

    private void initLogic() {
        //还有她的信息有待处理，这里尚未
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //intent.putExtra("data_return","这是返回的数据哦");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        //进入聊天界面
        tv_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 找到各个控件
     */
    private void initUI() {
        iv_back = findViewById(R.id.iv_back);
        tv_it_news = findViewById(R.id.tv_it_news);
        iv_her_photo = findViewById(R.id.iv_her_photo);
        tv_name = findViewById(R.id.tv_name);
        tv_age = findViewById(R.id.tv_age);
        tv_constellation = findViewById(R.id.tv_constellation);
        tv_professional = findViewById(R.id.tv_professional);
        tv_chat = findViewById(R.id.tv_chat);
    }
}
