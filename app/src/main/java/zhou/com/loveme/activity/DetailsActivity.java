package zhou.com.loveme.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zhou.com.loveme.R;
import zhou.com.loveme.bean.JsonBean;
import zhou.com.loveme.dao.OwerMessage;
import zhou.com.loveme.dao.OwerSelectMessage;
import zhou.com.loveme.json.JsonSelectMy;
import zhou.com.loveme.bean.ContantsValue;
import zhou.com.loveme.utils.FileDataUtil;
import zhou.com.loveme.utils.GetJsonDataUtil;
import zhou.com.loveme.utils.ListToString;
import zhou.com.loveme.utils.SpUtil;
import zhou.com.loveme.utils.ToastUtil;
import zhou.com.loveme.view.FlowLayout;

public class DetailsActivity extends Activity implements View.OnClickListener {

    private static final int RESULT_DETAILS = 2;

    private static final int IMAGE_OPEN5 = 6;
    private static final int IMAGE_OPEN4 = 5;
    private static final int IMAGE_OPEN3 = 4;
    private static final int IMAGE_OPEN2 = 3;
    private final int IMAGE_OPEN1 = 1;        //打开图片标记
    //各控件
    private Button bt_submit;
    private ImageView iv_details_back;
    private TextView tv_province_city;
    private TextView tv_major;
    private EditText et_nickname;
    private RadioGroup radioGroup;
    private TextView tv_labels_others;
    private TextView tv_sports_others;
    private TextView tv_musics_others;
    private TextView tv_foods_others;
    private TextView tv_films_others;
    private TextView tv_books_others;
    private TextView tv_travels_others;

    private int sex = 1;
    //标签云
    private FlowLayout id_flowlayout;
    private FlowLayout id_sports_flowlayout;
    private FlowLayout id_books_flowlayout;
    private FlowLayout id_musics_flowlayout;
    private FlowLayout id_foots_flowlayout;
    private FlowLayout id_films_flowlayout;
    private FlowLayout id_travels_flowlayout;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private List<String> labelsList;
    private List<String> sportsList;
    private List<String> musicList;
    private List<String> foodsList;
    private List<String> filmsList;
    private List<String> booksList;
    private List<String> travelsList;
    private List<String> constellationList;
    private List<String> keyConsteList;
    private List<String> majorNameList;
    private List<Integer> majorIDList;
    private TextView tv_conste;
    private OptionsPickerView optionsPickerView;

    //要上传的数据
    private String constellation;
    private int major;
    private String labels;

    /*要上传的照片*/
    private ImageView iv_img1;
    private ImageView iv_img2;
    private ImageView iv_img3;
    private ImageView iv_img4;
    private ImageView iv_img5;
    private List<String> pathImageList;//获取照片的集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initUI();
        initCilick();
        //获取数据
        initRequest();

    }

    /**
     * 数据请求
     */
    private void initRequest() {

        String data_select_message = FileDataUtil.loadDataFile(getApplicationContext(), "data_select_message");//获取数据
        Map<String, ArrayList<String>> mapName = new HashMap<>();//定义一个集合
        Map<String, ArrayList<String>> mapKey = new HashMap<>();//定义一个集合
        //System.out.println("data_select_message的数据为："+data_select_message);
        List<String> nameList = new ArrayList<>();
        majorNameList = new ArrayList<>();
        majorIDList = new ArrayList<>();

        Gson gson = new Gson();//gson框架 json
        JsonSelectMy jsonSelectMy = gson.fromJson(data_select_message, JsonSelectMy.class);//gson解析
        //获取majorList
        List<JsonSelectMy.MajorListBean> majorList = jsonSelectMy.getMajorList();
        for (int z = 0; z < majorList.size(); z++) {
            JsonSelectMy.MajorListBean majorListBean = majorList.get(z);
            String majorName = majorListBean.getMajorName();
            int id = majorListBean.getId();
            majorIDList.add(id);
            majorNameList.add(majorName);
        }

        List<JsonSelectMy.CategoryListBean> categoryList = jsonSelectMy.getCategoryList();
        for (int i = 0; i < categoryList.size(); i++) {
            List<String> list = new ArrayList<>();// 定义一个List 装types下的数据name
            List<String> listKey = new ArrayList<>();// 定义一个List 装types下的数据key

            JsonSelectMy.CategoryListBean categoryListBean = categoryList.get(i);
            String name = categoryListBean.getName();
            nameList.add(name);
            List<JsonSelectMy.CategoryListBean.TypesBean> types = categoryListBean.getTypes();
            for (int j = 0; j < types.size(); j++) {
                JsonSelectMy.CategoryListBean.TypesBean typesBean = types.get(j);
                String nameType = typesBean.getName();
                String keyType = typesBean.getKey();//categoryList-->types-->name
                listKey.add(keyType);
                list.add(nameType);
            }
            mapName.put(name, (ArrayList<String>) list);
            mapKey.put(name, (ArrayList<String>) listKey);
        }
        /*//对集合对遍历
        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            ArrayList<String> strings1 = map.get(next);
            System.out.println(next+":"+strings1);
        }
        String s = ListToString.listToString(nameList);//List转为字符串
        System.out.println("ghj"+s);*/
        /**
         * 各框间的数据展示
         * 标签   运动   音乐   食物   影视   书籍    旅行足迹   星座
         */
        //String[] labels = ListToString.listToString(map.get(nameList.get(0))).split(",");
        labelsList = new ArrayList<>();
        sportsList = new ArrayList<>();
        musicList = new ArrayList<>();
        foodsList = new ArrayList<>();
        filmsList = new ArrayList<>();
        booksList = new ArrayList<>();
        travelsList = new ArrayList<>();
        constellationList = new ArrayList<>();

        constellationList = mapName.get(nameList.get(7));//获取星座数据
        keyConsteList = mapKey.get(nameList.get(7));//获取星座健值

        System.out.println("星座数据：" + constellationList);
        System.out.println("星座健值：" + keyConsteList);

        //用来保存上传A.   第二参数key（A.1.1）  key对应的name，就是文字   自定义布局（云标签）
        initdata(labelsList, ListToString.listToString(mapKey.get(nameList.get(0))).split(","), ListToString.listToString(mapName.get(nameList.get(0))).split(","), id_flowlayout);//标签
        initdata(sportsList, ListToString.listToString(mapKey.get(nameList.get(1))).split(","), ListToString.listToString(mapName.get(nameList.get(1))).split(","), id_sports_flowlayout);//运动
        initdata(musicList, ListToString.listToString(mapKey.get(nameList.get(2))).split(","), ListToString.listToString(mapName.get(nameList.get(2))).split(","), id_musics_flowlayout);//音乐
        initdata(foodsList, ListToString.listToString(mapKey.get(nameList.get(3))).split(","), ListToString.listToString(mapName.get(nameList.get(3))).split(","), id_foots_flowlayout);//食物
        initdata(filmsList, ListToString.listToString(mapKey.get(nameList.get(4))).split(","), ListToString.listToString(mapName.get(nameList.get(4))).split(","), id_films_flowlayout);//电影
        initdata(booksList, ListToString.listToString(mapKey.get(nameList.get(5))).split(","), ListToString.listToString(mapName.get(nameList.get(5))).split(","), id_books_flowlayout);//书籍
        initdata(travelsList, ListToString.listToString(mapKey.get(nameList.get(6))).split(","), ListToString.listToString(mapName.get(nameList.get(6))).split(","), id_travels_flowlayout);//旅游
        //initdata(constellationList,ListToString.listToString(mapKey.get(nameList.get(7))).split(","),ListToString.listToString(mapName.get(nameList.get(7))).split(","),id_constellations_flowlayout);//星座

    }

    /**
     * 找到搜索标签的控件
     */
    private void initdata(final List<String> list, String[] stringKey, String[] strings, FlowLayout flowLayout) {
        for (int i = 0; i < strings.length; i++) {
            final TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.label_tab, flowLayout, false);
            tv.setText(strings[i]);
            final String key = stringKey[i];
            final String str = tv.getText().toString();
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                    tv.setBackgroundResource(R.drawable.my_corners2);
                    list.add(key);

                }
            });
            flowLayout.addView(tv);//添加到父View
        }
    }


    /**
     * 各个点击按钮事件this
     * <p>
     * RadioGroup  性别选择
     */
    private void initCilick() {
        bt_submit.setOnClickListener(this);
        iv_details_back.setOnClickListener(this);
        tv_major.setOnClickListener(this);
        tv_conste.setOnClickListener(this);
        tv_province_city.setOnClickListener(this);
        tv_labels_others.setOnClickListener(this);
        tv_sports_others.setOnClickListener(this);
        tv_musics_others.setOnClickListener(this);
        tv_foods_others.setOnClickListener(this);
        tv_films_others.setOnClickListener(this);
        tv_travels_others.setOnClickListener(this);
        tv_books_others.setOnClickListener(this);
        iv_img1.setOnClickListener(this);
        iv_img2.setOnClickListener(this);
        iv_img3.setOnClickListener(this);
        iv_img4.setOnClickListener(this);
        iv_img5.setOnClickListener(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton rb = findViewById(radioButtonId);
                String gender = (String) rb.getText();
                if (gender.equals("男")) {
                    sex = 1;
                } else if (gender.equals("女")) {
                    sex = 0;
                }
                ToastUtil.show(getApplicationContext(), gender);
            }
        });
    }

    /**
     * 获取控件
     */
    private void initUI() {
        bt_submit = findViewById(R.id.bt_submit);
        iv_details_back = findViewById(R.id.iv_details_back);
        et_nickname = findViewById(R.id.et_nickname);
        radioGroup = findViewById(R.id.radioGroup);
        //这里是自定义带内容
        id_flowlayout = findViewById(R.id.id_flowlayout);
        id_sports_flowlayout = findViewById(R.id.id_sports_flowlayout);
        id_books_flowlayout = findViewById(R.id.id_books_flowlayout);
        id_musics_flowlayout = findViewById(R.id.id_musics_flowlayout);
        id_foots_flowlayout = findViewById(R.id.id_foots_flowlayout);
        id_films_flowlayout = findViewById(R.id.id_films_flowlayout);
        id_travels_flowlayout = findViewById(R.id.id_travels_flowlayout);
        //这里是每个其他的按钮
        //省市与学院星座
        tv_province_city = findViewById(R.id.tv_province_city);
        tv_major = findViewById(R.id.tv_major);//学院
        tv_conste = findViewById(R.id.tv_conste);//星座

        tv_labels_others = findViewById(R.id.tv_labels_others);
        tv_sports_others = findViewById(R.id.tv_sports_others);
        tv_musics_others = findViewById(R.id.tv_musics_others);
        tv_foods_others = findViewById(R.id.tv_foods_others);
        tv_films_others = findViewById(R.id.tv_films_others);
        tv_books_others = findViewById(R.id.tv_books_others);
        tv_travels_others = findViewById(R.id.tv_travels_others);

        /*要上传的照片*/
        iv_img1 = findViewById(R.id.iv_img1);
        iv_img2 = findViewById(R.id.iv_img2);
        iv_img3 = findViewById(R.id.iv_img3);
        iv_img4 = findViewById(R.id.iv_img4);
        iv_img5 = findViewById(R.id.iv_img5);

    }

    /**
     * 所有的点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.iv_img1:
                //选择图片
                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_OPEN1);
                break;

            case R.id.iv_img2:
                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_OPEN2);
                break;

            case R.id.iv_img3:
                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_OPEN3);
                break;

            case R.id.iv_img4:
                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_OPEN4);
                break;

            case R.id.iv_img5:
                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_OPEN5);
                break;

            case R.id.bt_submit:
                String nickname = this.et_nickname.getText().toString().trim();
                if (nickname.isEmpty()) {
                    ToastUtil.show(getApplicationContext(), "昵称不能为空");
                }
                //照片不能为空 else if
                else {
                    boolean isDetail = SpUtil.getBoolean(getApplicationContext(), ContantsValue.LOGIN_OVER, false);
                    //判断是否曾经进入过该界面，如果进入过，则点击的时候返回到DetailsActivity，否则返回到HomeActivity
                    if (isDetail) {
                        intent = new Intent();
                        intent.putExtra("ee", "DetailsActivity");
                        setResult(RESULT_DETAILS, intent);
                        ToastUtil.show(getApplicationContext(), "修改信息成功，上传信息到服务器");
                        finish();
                    } else {
                        ToastUtil.show(getApplicationContext(), "修改信息成功");
                        intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                        SpUtil.putBoolean(getApplicationContext(), ContantsValue.LOGIN_OVER, true);//用来判断是否进入过登录与注册界面
                    }
                }
                break;
            case R.id.iv_details_back:
                finish();
                break;
            case R.id.tv_major://学院
                initOptionPicker("我的学院：", majorNameList, tv_major);
                optionsPickerView.setPicker(majorNameList);
                optionsPickerView.show();
                break;
            case R.id.tv_conste://星座
                initOptionPicker("我的星座类型：", constellationList, tv_conste);
                optionsPickerView.setPicker(constellationList);
                optionsPickerView.show();
                break;
            case R.id.tv_province_city://省市
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initJsonData();
                    }
                });
                ShowPickerView();//展示省市
                break;
            case R.id.tv_labels_others:
                showDataDialog(labelsList, "A123", "label_others");
                break;
            case R.id.tv_sports_others:

                break;
            case R.id.tv_musics_others:

                break;
            case R.id.tv_foods_others:

                break;
            case R.id.tv_films_others:

                break;
            case R.id.tv_books_others:

                break;
            case R.id.tv_travels_others:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //打开图片
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if (!TextUtils.isEmpty(uri.getAuthority())) {
                //查询选择图片
                Cursor cursor = getContentResolver().query(
                        uri,
                        new String[]{MediaStore.Images.Media.DATA},
                        null,
                        null,
                        null);
                //返回 没找到选择图片
                if (null == cursor) {
                    return;
                }
                //光标移动至开头 获取图片路径
                cursor.moveToFirst();
                pathImageList = new ArrayList<>();//获取图片地址
                String pathImage = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
                pathImageList.add(pathImage);//获取图片地址集合
                System.out.println(pathImageList.toString());
                if (!TextUtils.isEmpty(pathImage)) {
                    Bitmap addbmp = BitmapFactory.decodeFile(pathImage);
                    if (requestCode == IMAGE_OPEN1) {
                        iv_img1.setImageBitmap(addbmp);
                    } else if (requestCode == IMAGE_OPEN2) {
                        iv_img2.setImageBitmap(addbmp);
                    } else if (requestCode == IMAGE_OPEN3) {
                        iv_img3.setImageBitmap(addbmp);
                    } else if (requestCode == IMAGE_OPEN4) {
                        iv_img4.setImageBitmap(addbmp);
                    } else if (requestCode == IMAGE_OPEN5) {
                        iv_img5.setImageBitmap(addbmp);
                    }
                }
            }
        }
    }

    /**
     * 每一个others的dialog
     *
     * @param
     * @param list
     */
    private void showDataDialog(final List<String> list, final String keyName, final String word_others) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View viewOthers = View.inflate(this, R.layout.dialog_others, null);
        dialog.setView(viewOthers, 0, 0, 0, 0);
        dialog.show();
        Button bt_other_ensure = viewOthers.findViewById(R.id.bt_other_ensure);
        Button bt_other_cancel = viewOthers.findViewById(R.id.bt_other_cancel);
        final EditText et_others = viewOthers.findViewById(R.id.et_others);
        bt_other_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        bt_other_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String erOther = et_others.getText().toString().toString();
                if (!erOther.isEmpty()) {
                    String others = keyName + "&" + word_others + "=" + erOther;
                    list.add(others);
                }
                System.out.println("list-----" + list);
                dialog.dismiss();
            }
        });

    }

    /**
     * 展示省市区 上传的时候只上传省市
     */
    private void ShowPickerView() {// 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                String show = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2);//上传数据
                tv_province_city.setText("我的地址：" + tx);

            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    /**
     * 解析数据
     */
    private void initJsonData() {
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

    }

    /**
     * @param result
     * @return
     */
    private ArrayList<JsonBean> parseData(String result) {
        //Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    /**
     * 从下而上的 学院,星座 选择
     *
     * @param txt
     */
    private void initOptionPicker(final String string, final List<String> list, final TextView txt) {
        optionsPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = list.get(options1);
                txt.setText(string + tx);
                //String s = ListToString.listToString(list);
                //System.out.println(s);
                if (constellationList.contains(tx)) {
                    constellation = keyConsteList.get(options1);//获取星座id
                    System.out.println(constellation);
                }
                if (majorNameList.contains(tx)) {
                    major = majorIDList.get(options1);
                    System.out.println(major);
                }
            }
        }).build();
    }
}
