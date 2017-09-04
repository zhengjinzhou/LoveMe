package zhou.com.loveme.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import zhou.com.loveme.R;
import zhou.com.loveme.bean.ContantsValue;
import zhou.com.loveme.json.JsonOwer;
import zhou.com.loveme.utils.FileDataUtil;
import zhou.com.loveme.view.Xcircleindicator;

import static com.squareup.picasso.MemoryPolicy.NO_CACHE;
import static com.squareup.picasso.MemoryPolicy.NO_STORE;

public class AllNewsActivity extends Activity {

    private ImageView iv_all_back;
    private TextView tv_all_edit;
    private TextView tv_username;
    private TextView tv_constellation;
    private TextView tv_labels;
    private TextView tv_sports;
    private TextView tv_musics;
    private TextView tv_foods;
    private TextView tv_films;
    private TextView tv_books;
    private TextView tv_travels;
    private TextView tv_province;
    private TextView tv_city;
    private TextView tv_major;
    private ViewPager list_pager;
    private Xcircleindicator mXcircleindicator;
    private List<String> photoUrlList;
    private List<View> list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_news);
        initUI();
        initRequest();//网络请求数据  实际是从应用中获取
        initClick();
    }

    private void initClick() {
        iv_all_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_all_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 网络请求数据  实际是从应用中获取
     */
    private void initRequest() {
        String data_my_message = FileDataUtil.loadDataFile(getApplicationContext(), "data_my_message");
        Gson gson = new Gson();
        JsonOwer jsonOwer = gson.fromJson(data_my_message, JsonOwer.class);
        JsonOwer.MemInfoBean memInfo = jsonOwer.getMemInfo();
        String realname = memInfo.getRealname();
        String books = memInfo.getBooks();
        String films = memInfo.getFilms();
        String foods = memInfo.getFoods();
        String labels = memInfo.getLabels();
        String constellation = memInfo.getConstellation();
        String music = memInfo.getMusic();
        String sports = memInfo.getSports();
        String travels = memInfo.getTravels();
        JsonOwer.MemInfoBean.MajorBean major = memInfo.getMajor();
        String majorName = major.getMajorName();
        JsonOwer.MemInfoBean.CityBean city = memInfo.getCity();
        String cityName = city.getName();
        JsonOwer.MemInfoBean.CityBean.ProvinceBean province = city.getProvince();
        String provinceName = province.getName();
        //获取照片地址
        photoUrlList = new ArrayList<>();
        List<JsonOwer.MemInfoBean.PicturesBean> pictures = memInfo.getPictures();
        for(int j=0;j<pictures.size();j++){
            JsonOwer.MemInfoBean.PicturesBean picturesBean = pictures.get(j);
            String photoUrl = picturesBean.getPhotoUrl();
            photoUrlList.add(photoUrl);
            //System.out.println("yanzheng"+photoUrl);
        }
        //System.out.println("yanzheng"+photoUrlList.toString());
        /**
         * 将数据套入  考虑到能不用全局就不用全局到原则
         * 照片的加载另外使用函数
         */
        initPictures();

        tv_username.setText(realname);
        tv_province.setText(provinceName);
        tv_city.setText(cityName);
        tv_constellation.setText(constellation);
        tv_major.setText(majorName);
        tv_labels.setText(labels);
        tv_sports.setText(sports);
        tv_musics.setText(music);
        tv_foods.setText(foods);
        tv_films.setText(films);
        tv_books.setText(books);
        tv_travels.setText(travels);
    }

    /**
     *  对图片对处理
     */
    private void initPictures() {
        list_view = new ArrayList<>();
        System.out.println("yanzheng"+photoUrlList.toString()+"oooo"+photoUrlList.size());
        for (int i=0;i<photoUrlList.size();i++){
            //System.out.println(photoUrlList.get(i));
            LayoutInflater inflater = getLayoutInflater();
            View inflate = inflater.inflate(R.layout.fragment_page, null);
            ImageView iv_image = inflate.findViewById(R.id.iv_image);
            Picasso.with(getApplicationContext())
                    .load(ContantsValue.URL_PHOTO+photoUrlList.get(i))
                    .memoryPolicy(NO_CACHE, NO_STORE)
                    .config(Bitmap.Config.RGB_565)
                    .into(iv_image);
            list_view.add(inflate);
        }
        list_pager.setAdapter(new MypageAdapter());
        //设置总共的页数
        mXcircleindicator.initData(photoUrlList.size(), 0);
        //设置当前的页面
        mXcircleindicator.setCurrentPage(0);
        list_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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


    /**
     * PagerAdapter 适配器
     */
    public class MypageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list_view.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            container.addView(list_view.get(position));
            return list_view.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list_view.get(position));
        }
    }
    /**
     * 获取控件
     */
    private void initUI() {
        iv_all_back = findViewById(R.id.iv_all_back);
        tv_all_edit = findViewById(R.id.tv_all_edit);
        tv_username = findViewById(R.id.tv_username);
        tv_constellation = findViewById(R.id.tv_constellation);
        tv_labels = findViewById(R.id.tv_labels);
        tv_sports = findViewById(R.id.tv_sports);
        tv_musics = findViewById(R.id.tv_musics);
        tv_foods = findViewById(R.id.tv_foods);
        tv_films = findViewById(R.id.tv_films);
        tv_books = findViewById(R.id.tv_books);
        tv_travels = findViewById(R.id.tv_travels);
        tv_province = findViewById(R.id.tv_province);
        tv_city = findViewById(R.id.tv_city);
        tv_major = findViewById(R.id.tv_major);
        list_pager =  findViewById(R.id.list_pager);
        mXcircleindicator = findViewById(R.id.Xcircleindicator);
    }
}
