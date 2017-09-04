package zhou.com.loveme.json;

/**
 * Created by zhou on 2017/8/11.
 */

public class JsonLogin {


    /**
     * uid : 09147E16EF6FC65D
     * accountNumber : 2014414
     * error : -1
     * token : 5d4cc5ac1f6141e2b5eadaba25581f10
     * expires_in : 172800
     * msg : 登录成功并已注册
     */

    private String uid;
    private String accountNumber;
    private String error;
    private String token;
    private int expires_in;
    private String msg;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
