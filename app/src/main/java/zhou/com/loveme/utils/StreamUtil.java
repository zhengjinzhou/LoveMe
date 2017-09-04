package zhou.com.loveme.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by a8B410 on 2017/8/2.
 */

public class StreamUtil {
    public static String streamToString(InputStream is){
        //在读取数据的过程中，将读取的内存存储到缓存中，然后一次行转换称字符串返回
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //读取流操作，读到没有为止
        byte[] buffer = new byte[1024];
        //记录读取内容的临时变量
        int len;
        try {
            while ((len=is.read(buffer))!=-1){
                bos.write(buffer,0,len);
            }
            return bos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
