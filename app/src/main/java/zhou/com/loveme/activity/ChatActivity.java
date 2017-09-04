package zhou.com.loveme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import zhou.com.loveme.R;

public class ChatActivity extends Activity implements View.OnClickListener{

    private ImageView iv_back;
    private TextView tv_it_name;
    private ImageView iv_her_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initUI();
        initLogic();
    }

    private void initLogic() {

    }

    private void initUI() {
        iv_back = findViewById(R.id.iv_back);
        tv_it_name = findViewById(R.id.tv_it_name);
        iv_her_news = findViewById(R.id.iv_her_news);

        iv_back.setOnClickListener(this);
        iv_her_news.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case
            R.id.iv_her_news:
                intent = new Intent(getApplicationContext(),HerNewsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
