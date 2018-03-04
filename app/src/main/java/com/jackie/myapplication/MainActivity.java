package com.jackie.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView list;

    private List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();

        initView();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(linearLayoutManager);
        ListAdapter adapter = new ListAdapter(items);
        ListDecoration decoration = new ListDecoration(this, new ListDecorationCallBack() {
            @Override
            public long getGroupId(int position) {
                return Character.toUpperCase(items.get(position).charAt(0));
            }

            @Override
            public String getGroupFirstLine(int position) {
                return items.get(position).substring(0, 1).toUpperCase();
            }
        });
        list.addItemDecoration(decoration);
        list.setAdapter(adapter);
    }

    private void initData() {
        items = new ArrayList<>();
        items.add("a");
        items.add("a");
        items.add("a");
        items.add("a");
        items.add("a");
        items.add("b");
        items.add("b");
        items.add("b");
        items.add("b");
        items.add("b");
        items.add("c");
        items.add("c");
        items.add("c");
        items.add("c");
        items.add("d");
        items.add("d");
        items.add("d");
        items.add("d");
        items.add("e");
        items.add("e");
        items.add("e");
        items.add("e");
        items.add("f");
        items.add("f");
        items.add("f");
        items.add("f");
        items.add("g");
        items.add("g");
        items.add("g");
        items.add("g");
        items.add("h");
        items.add("h");
        items.add("h");
        items.add("i");
        items.add("i");
        items.add("i");
        items.add("j");
        items.add("j");
        items.add("j");
        items.add("k");
        items.add("k");
        items.add("k");
        items.add("k");
        items.add("k");
    }
}
