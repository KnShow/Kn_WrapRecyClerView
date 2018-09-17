package recyclerview.itcast.cn.kn_wraprecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private WrapRecyclerView recyclerView;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        TextView headerText = new TextView(MainActivity.this);
        headerText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        headerText.setText("headerView");
        recyclerView.addHeaderView(headerText);

        TextView footerText = new TextView(MainActivity.this);
        footerText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        footerText.setText("footerView");
        recyclerView.addFooterView(footerText);

        recyclerView.setAdapter(new MyAdapter(list));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i, "Item " + i);
        }
    }
}
