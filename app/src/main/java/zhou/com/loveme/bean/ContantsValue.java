package zhou.com.loveme.bean;

/**
 * Created by a8B410 on 2017/8/2.
 */

public class ContantsValue {

    //记住是否已经登录过
    public static final String LOGIN_OVER = "login_over";
    //保存登录时的密码
    public static final String PASSWORD = "password";
    //记住是否已经进入过  省去绑定手机号码
    public static final String  TIME_TWO = "time_two";

    //登录  注册-发送验证码  获取个人信息设置选项 获取个人信息
    public static final String URL_ONE = "http://172.25.129.195:8080/LoveMe/app/index.do";

    //提交验证码：接口
    public static final String URL_TWO = "http://123.207.55.87:8080/LoveMe/app/index.do";

    //修改会员的个人信息
    public static final String URL_THREE = "http://172.25.129.195:8080/LoveMe/app/update.do";

    public static final String URL_PHOTO = "http://172.25.129.195:8080/LoveMe";

    //保存当前用户的uid
    public static final String UID = "uid";

    //获取口令值
    public static final String TOKEN = "token";

    /**
     * 密钥说明
     *
     *  APP_ENCRYPTION_KEY:用于与服务器端参数传递的密钥。

     * APP_ENCRYPTION_KEY = "IAASIDuioponuYBIUNLIK123ikoIO";

     * ENCRYPTION_KEY:用于密码，用户id加解密的密钥。

     * ENCRYPTION_KEY = "ASDHOjhudhaos23asdihoh80";
     */
    public static final String APP_ENCRYPTION_KEY="IAASIDuioponuYBIUNLIK123ikoIO";
    public static final String ENCRYPTION_KEY = "ASDHOjhudhaos23asdihoh80";

    //保存手机号码
    public static final String TEL_PHONE = "tel_phone";

    //保存是否经过导航页面
    public static final String OVER_ADAPTER = "over_adapter";

}
