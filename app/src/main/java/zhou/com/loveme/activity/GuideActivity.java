package zhou.com.loveme.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import zhou.com.loveme.R;

public class GuideActivity extends Activity {


    private ImageView iv_guide_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initUI();
        initClick();
    }

    private void initClick() {
        iv_guide_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initUI() {
        iv_guide_back = findViewById(R.id.iv_guide_back);
    }
}
