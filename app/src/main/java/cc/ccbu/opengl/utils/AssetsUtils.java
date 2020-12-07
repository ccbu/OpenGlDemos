package cc.ccbu.opengl.utils;

import android.content.Context;

import java.io.InputStream;

public class AssetsUtils {

    public static String loadFromAssetsFile(Context context, String fname) {

        StringBuilder result = new StringBuilder();

        try {
            InputStream is = context.getResources().getAssets().open(fname);
            int ch;
            byte[] buffer = new byte[1024];
            while (-1 != (ch = is.read(buffer))) {
                result.append(new String(buffer, 0, ch));
            }
        } catch (Exception e) {
            return null;
        }

        return result.toString().replaceAll("\\r\\n", "\n");
    }
}
