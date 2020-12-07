package cc.ccbu.opengl.shape;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import cc.ccbu.opengl.utils.ShaderUtils;

public class Triangle implements Shape{

    private FloatBuffer vertexBuffer;

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                "void main() {" +
                "  gl_Position = vPosition;" +
                "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                "uniform vec4 vColor;" +
                "void main() {" +
                "  gl_FragColor = vColor;" +
                "}";

    static final int COORDS_PER_VERTEX = 3;

    static float triangleCoords[] = {
            0.5f,  0.5f, 0.0f, // top
            -0.5f, -0.5f, 0.0f, // bottom left
            0.5f, -0.5f, 0.0f  // bottom right
    };

    float color[] = { 1.0f, 0.1f, 0.1f, 1.0f };

    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4;

    private int mProgram;
    private int mPositionHandle;
    private int mColorHandle;

    @Override
    public void onInit() { // call after Render's onSurfaceCreated called
        if (mProgram == 0 ) {
            ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
            bb.order(ByteOrder.nativeOrder());
            vertexBuffer = bb.asFloatBuffer();
            vertexBuffer.put(triangleCoords);
            vertexBuffer.position(0);

            int vertexShader = ShaderUtils.loadVertexShader(vertexShaderCode);
            int fragmentShader = ShaderUtils.loadFragmentShader(fragmentShaderCode);
            mProgram = ShaderUtils.linkProgram(vertexShader, fragmentShader);
        }
    }

    public void onDraw(){
        GLES20.glUseProgram(mProgram);
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

    @Override
    public void setSize(int width, int height) {

    }
}
