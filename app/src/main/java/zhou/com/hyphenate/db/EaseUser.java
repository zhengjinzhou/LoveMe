package zhou.com.hyphenate.db;

import com.hyphenate.chat.EMContact;

/**
 * Created by zhou on 2017/8/31.
 */

public class EaseUser extends EMContact {
    /**
     * 昵称首字母
     */
    protected String initialLetter;
    /**
     * 用户头像
     */
    protected String avatar;

    public EaseUser(String username){
        this.username = username;
    }

    public String getInitialLetter(){
        return initialLetter;
    }

    public void setInitialLetter(String initialLetter){
        this.initialLetter = initialLetter;
    }

    public String getAvatar(){
        return avatar;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    @Override
    public int hashCode() {
        return 17*getUsername().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof EaseUser)){
            return false;
        }
        return getUsername().equals(((EaseUser)obj).getUsername());
    }

    @Override
    public String toString() {
        return nick == null ? username : nick;
    }
}
