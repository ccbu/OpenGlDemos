package cc.ccbu.opengl.utils;

import android.opengl.GLES20;
import android.util.Log;


public class ShaderUtils {

    private static final String TAG = "ShaderUtils";

    public static int loadVertexShader(String source){
        return loadShader(GLES20.GL_VERTEX_SHADER, source);
    }

    public static int loadFragmentShader(String source){
        return loadShader(GLES20.GL_FRAGMENT_SHADER, source);
    }

    public static int loadShader(int shaderType, String source){

        int shader = GLES20.glCreateShader(shaderType);

        if(0 != shader){
            GLES20.glShaderSource(shader, source);
            GLES20.glCompileShader(shader);
            int[] compiled = new int[1];
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled,0);
            if(compiled[0] != GLES20.GL_TRUE){
                Log.e(TAG,"Could not compile shader:" + shaderType);
                Log.e(TAG,"GLES20 Error:" + GLES20.glGetShaderInfoLog(shader));
                GLES20.glDeleteShader(shader);
                shader = 0;
            }

        }

        return shader;
    }

    public static int linkProgram(int vertexShaderId, int fragmentShaderId) {
        int programObjectId = GLES20.glCreateProgram();

        // Attach the vertex shader to the program.
        GLES20.glAttachShader(programObjectId, vertexShaderId);
        // Attach the fragment shader to the program.
        GLES20.glAttachShader(programObjectId, fragmentShaderId);
        // link the shader to program
        GLES20.glLinkProgram(programObjectId);
        // get link status
        final int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(programObjectId, GLES20.GL_LINK_STATUS, linkStatus, 0);

        if (linkStatus[0] != GLES20.GL_TRUE) {
            // If it failed, delete the program object.
            GLES20.glDeleteProgram(programObjectId);
            programObjectId = 0;
        }

        // Return the program object ID.
        return programObjectId;
    }

}
