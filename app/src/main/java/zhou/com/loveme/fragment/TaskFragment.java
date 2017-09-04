package zhou.com.loveme.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import zhou.com.loveme.R;
import zhou.com.loveme.activity.HerNewsActivity;
import zhou.com.loveme.activity.UploadActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment{


    private ImageView iv_her_news;
    private TextView tv_time;
    private TextView tv_level;
    private TextView tv_place;
    private ListView lv_task_item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        iv_her_news = view.findViewById(R.id.iv_her_news);
        tv_time = view.findViewById(R.id.tv_time);
        tv_level = view.findViewById(R.id.tv_level);
        tv_place = view.findViewById(R.id.tv_place);
        lv_task_item = view.findViewById(R.id.lv_task_item);
        initNews();
        initData();
        return  view;
    }

    /**
     * tv_level
     *
     * tv_time
     *
     * tv_place
     *
     * lv_task_item
     *
     * 处理
     */
    private void initData() {
        //请求服务器

        //lv_task_item的处理
        lv_task_item.setAdapter(new MyAdapter());
        lv_task_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //进入新的页面，上传依据
                Intent intent = new Intent(getContext(),UploadActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 适配器
     */
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 10;
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
                holder = new ViewHolder();
                view = View.inflate(getContext(), R.layout.list_task_item, null);
                holder.rl_list_task = view.findViewById(R.id.rl_list_task);
                view.setTag(holder);
            }else {
                holder=(ViewHolder) view.getTag();
            }
            return view;
        }
    }

    class ViewHolder{
        RelativeLayout rl_list_task;
    }


    private void initNews() {
        iv_her_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),HerNewsActivity.class);
                startActivity(intent);
            }
        });
    }
}
