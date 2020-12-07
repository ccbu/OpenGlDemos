package cc.ccbu.opengl.shape;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ShapeRender implements GLSurfaceView.Renderer {
    private Shape mShape = null;
    private int mWidth = 0;
    private int mHeight = 0;

    private boolean mGlCreated = false;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.5f,0.5f,0.5f,1.0f);
        if (mShape != null) {
            mShape.onInit();
        }
        mGlCreated = true;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        mWidth = width;
        mHeight = height;
        GLES20.glViewport(0,0,width,height);
        if (mShape != null) {
            mShape.setSize(width, height);
        }

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT|GLES20.GL_DEPTH_BUFFER_BIT);
        if (mShape != null) {
            mShape.onDraw();
        }
    }

    public void setShape(Shape shape) {
        mShape = shape;
        if (mGlCreated) {
            mShape.onInit();
            if (mWidth != 0 && mHeight != 0) {
                mShape.setSize(mWidth, mHeight);
            }
        }
    }
}
