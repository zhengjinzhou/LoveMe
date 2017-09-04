package zhou.com.loveme.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhou on 2017/8/5.
 *
 * 写入SharePreference
 *
 */

public class SpUtil {

    private static SharedPreferences sp;
    private static String spName = "config";

    /**
     * 写入boolean变量至sp中
     * @param context	上下文环境
     * @param key	存储节点名称
     * @param value	存储节点的值 boolean
     */
    public static void putBoolean(Context context,String key,boolean value){
        if(sp==null){
            sp = context.getSharedPreferences(spName,Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key,value).commit();
    }

    /**
     * 读取boolean标示从sp中
     * @param context	上下文环境
     * @param key	存储节点名称
     * @param defValue	没有此节点默认值
     * @return		默认值或者此节点读取到的结果
     */
    public static boolean getBoolean(Context context,String key,boolean defValue){
        if(sp==null){
            sp=context.getSharedPreferences(spName,Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key,defValue);
    }

    /**
     * String
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context,String key,String value){
        if(sp==null){
            sp = context.getSharedPreferences(spName,Context.MODE_PRIVATE);
        }
        sp.edit().putString(key,value).commit();
    }

    public static String getString(Context context,String key,String defValue){
        if(sp==null){
            sp = context.getSharedPreferences(spName,Context.MODE_PRIVATE);
        }
        return sp.getString(key,defValue);
    }

    /**
     * Int
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putInt(Context context,String key,int value){
        if(sp==null){
            sp=context.getSharedPreferences(spName,Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key,value).commit();
    }

    public static int getInt(Context context,String key,int defVaule){
        if(sp==null){
            sp=context.getSharedPreferences(spName,Context.MODE_PRIVATE);
        }
        return sp.getInt(key,defVaule);
    }

    /**
     * remove
     *
     * @param context
     * @param key
     */
    public static void remove(Context context,String key){
        if(sp==null){
            sp = context.getSharedPreferences(spName,Context.MODE_PRIVATE);
        }
        sp.edit().remove(key).commit();
    }
}
