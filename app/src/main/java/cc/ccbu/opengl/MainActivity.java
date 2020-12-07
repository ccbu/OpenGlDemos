package cc.ccbu.opengl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cc.ccbu.opengl.shape.ShapeActivity;

public class MainActivity extends Activity {

    private ListView mMainList;

    private static final String[] strDatas = new String[] {
            "Shape"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainList = (ListView)findViewById(R.id.mainList);
        mMainList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strDatas));

        mMainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(ShapeActivity.class);
                        break;
                }
            }
        });
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }
}
