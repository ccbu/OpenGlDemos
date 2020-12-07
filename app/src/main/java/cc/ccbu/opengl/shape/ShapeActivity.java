package cc.ccbu.opengl.shape;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;

import cc.ccbu.opengl.R;

public class ShapeActivity extends Activity {

    private GlView mGlView;
    private SparseArray<Class<?>> mShapeClassArray = new SparseArray<>();
    private int mShapeId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);

        mGlView = (GlView)findViewById(R.id.shapeGlView);
        mShapeClassArray.put(R.id.shape_triangle, Triangle.class);

        updateShape(R.id.shape_triangle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_shape, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (!updateShape(item.getItemId())) {
            return super.onMenuItemSelected(featureId, item);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGlView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGlView.onPause();
    }

    public boolean updateShape(int id) {
        if (mShapeId == id) {
            return true;
        }
        Class<?> cls = mShapeClassArray.get(id);
        if (cls != null) {
            try {
                Shape shape = (Shape) cls.newInstance();
                updateShape(shape);
                mShapeId = id;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public void updateShape(Shape shape) {
        mGlView.updateShape(shape);
    }
}
