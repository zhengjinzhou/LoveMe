package zhou.com.loveme.utils;

/**
 * Created by zhou on 2017/8/10.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTimeUtil {
    public static String nowTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return format.format(date);
    }
}
