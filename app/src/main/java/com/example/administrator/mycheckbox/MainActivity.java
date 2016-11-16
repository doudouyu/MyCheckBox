package com.example.administrator.mycheckbox;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private RecyclerView rl;
    private RecyclerView.ViewHolder holder;
    private MyAdapter adapter;
    private ImageView checkBox;

    private List<CheckBean> list = new ArrayList<CheckBean>();
    private Button btn3;
    private int mSelectedPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add(new CheckBean(false, "优惠券10元"));
        list.add(new CheckBean(false, "优惠券20元"));
        list.add(new CheckBean(true, "优惠券30元"));
        list.add(new CheckBean(false, "优惠券40元"));
        list.add(new CheckBean(false, "优惠券50元"));
        list.add(new CheckBean(false, "优惠券60元"));
        list.add(new CheckBean(false, "优惠券70元"));
        list.add(new CheckBean(false, "优惠券80元"));
        list.add(new CheckBean(false, "优惠券90元"));
        list.add(new CheckBean(false, "优惠券100元"));
        list.add(new CheckBean(false, "下次好运"));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getChecked()) {
                mSelectedPos = i;
            }
        }
        rl = (RecyclerView) findViewById(R.id.recycle);
        rl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new MyAdapter();
        rl.setAdapter(adapter);
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_1:
                //全选
                for (CheckBean check : list) {
                    check.setChecked(true);
                    Log.i("我是全选", check.getChecked() + "");
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_2:
                for (CheckBean check : list) {
                    check.setChecked(false);
                    Log.i("我是全不选", check.getChecked() + "");
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_3:
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setChecked(!list.get(i).getChecked());
                    Log.i("我是反选", list.get(i).getChecked() + "");
                }
                adapter.notifyDataSetChanged();
                break;
            default:

                break;
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = View.inflate(MainActivity.this, R.layout.item, null);

            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyHolder viewHolder, final int i) {
            viewHolder.checkBox1.setChecked(list.get(i).getChecked());
            viewHolder.textView.setText(list.get(i).getTest());
            viewHolder.checkBox1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //方法1
                    for (CheckBean checkBean : list) {
                        checkBean.setChecked(false);
                    }
                    list.get(i).setChecked(true);
                    notifyDataSetChanged();
                    //方法2
                    //实现单选方法2： RecyclerView另一种定向刷新方法：不会有白光一闪动画 也不会重复onBindVIewHolder
//                    MyHolder holder1 = (MyHolder) rl.findViewHolderForLayoutPosition(mSelectedPos);
//                    if (holder1 != null) {//还在屏幕里
//                        holder1.checkBox1.setChecked(false);
//                    }
//                    list.get(mSelectedPos).setChecked(false);//不管在不在屏幕里 都需要改变数据
//                    //设置新Item的勾选状态
//                    mSelectedPos = i;
//                    list.get(mSelectedPos).setChecked(true);
//                    viewHolder.checkBox1.setChecked(true);
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class MyHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private CheckBox checkBox1;

        private MyHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            checkBox1 = (CheckBox) itemView.findViewById(R.id.check);
        }
    }


}
