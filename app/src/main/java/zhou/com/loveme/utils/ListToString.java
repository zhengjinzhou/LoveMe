package zhou.com.loveme.utils;

import java.util.List;

/**
 * Created by zhou on 2017/8/15.
 *
 * List转为字符串，不是字符串数组，不是字符串数组，不是字符串数组
 */

public class ListToString
{
    public static String listToString(List<String> list){
        if(list == null){
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        boolean first = true;
        //第一个前面不拼接","
        for(String string : list){
            if(first){
                first=false;
            }else{
                stringBuffer.append(",");
            }
            stringBuffer.append(string);
        }
        return stringBuffer.toString();
    }
}
