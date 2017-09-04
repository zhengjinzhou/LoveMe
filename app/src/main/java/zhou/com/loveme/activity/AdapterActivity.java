package zhou.com.loveme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhou.com.loveme.R;
import zhou.com.loveme.bean.ContantsValue;
import zhou.com.loveme.utils.SpUtil;
import zhou.com.loveme.view.Xcircleindicator;

public class AdapterActivity extends Activity {

    private List<View> viewList;
    private TextView tv_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        ViewPager viewpager = findViewById(R.id.viewpager);
        final Xcircleindicator mXcircleindicator = findViewById(R.id.adapter_circle);

        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.adapter1, null);
        View view2 = inflater.inflate(R.layout.adapter2, null);
        View view3 = inflater.inflate(R.layout.adapter3, null);
        tv_next = view3.findViewById(R.id.tv_next);
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdapterActivity.this,LoginActivity.class);
                SpUtil.putString(getApplicationContext(), ContantsValue.OVER_ADAPTER,"adapter");
                startActivity(intent);
                finish();
            }
        });
        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewpager.setAdapter(new MyPagerAdapter());
        //设置总共的页数
        mXcircleindicator.initData(viewList.size(),0);
        //设置当前的页面
        mXcircleindicator.setCurrentPage(0);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mXcircleindicator.setCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }
    }


}
