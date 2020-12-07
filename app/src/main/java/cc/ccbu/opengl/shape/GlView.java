package cc.ccbu.opengl.shape;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class GlView extends GLSurfaceView {
    private ShapeRender mRender = null;
    public GlView(Context context) {
        super(context);
        init();
    }

    public GlView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setEGLContextClientVersion(2);
        mRender = new ShapeRender();
        setRenderer(mRender);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void updateShape(Shape shape) {
        mRender.setShape(shape);
    }
}
