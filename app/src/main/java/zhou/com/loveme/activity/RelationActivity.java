package zhou.com.loveme.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import zhou.com.loveme.R;
import zhou.com.loveme.utils.ToastUtil;

public class RelationActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation);
        initUI();
    }

    private void initUI() {
        ImageView iv_relation_back = findViewById(R.id.iv_relation_back);
        ImageView iv_response_partner1 = findViewById(R.id.iv_response_partner1);
        ListView lv_response = findViewById(R.id.lv_response);

        //lv_response 设置适配器
        lv_response.setAdapter(new MyAdapter());

        //iv_back.setOnClickListener(this);
        iv_response_partner1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()){
            case R.id.iv_relation_back:
                intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.iv_response_partner1:
                intent = new Intent(getApplicationContext(),HerNewsActivity.class);
                startActivity(intent);
                break;
        }
    }

    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int i) {
            return getItemId(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if(view == null){
                view = View.inflate(getApplicationContext(),R.layout.list_relation_item,null);
                holder = new ViewHolder();
                holder.iv_request_partner = view.findViewById(R.id.iv_request_partner);
                holder.tv_request_agree = view.findViewById(R.id.tv_request_agree);
                view.setTag(holder);
            }else{
                holder=(ViewHolder) view.getTag();
            }
            /**
             * 人头像的点击事件
             */
            holder.iv_request_partner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.show(getApplicationContext(),"有待处理，跳转");
                    Intent intent = new Intent(getApplicationContext(),HerNewsActivity.class);
                    startActivity(intent);
                }
            });
            /**
             * 同意的点击事件
             */
            final ViewHolder finalHolder = holder;
            holder.tv_request_agree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.show(getApplicationContext(),"有待处理");
                    finalHolder.tv_request_agree.setText("已同意");
                    finalHolder.tv_request_agree.setTextColor(Color.GREEN);
                }
            });
            return view;
        }
    }

    static class ViewHolder{
        ImageView iv_request_partner;
        TextView tv_request_agree;
    }
}
