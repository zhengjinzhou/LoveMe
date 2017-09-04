package zhou.com.loveme.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import zhou.com.loveme.R;

/**
 * Created by zhou on 2017/8/5.
 *
 * 验证码倒计时
 */

public class DownTimeUtil extends CountDownTimer{

    private TextView mTextView;

    public DownTimeUtil(TextView textView ,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
    }

    @Override
    public void onTick(long longTime) {
        mTextView.setClickable(false);//设置不可点击
        mTextView.setText(longTime/1000 + "秒后重新获取");
        mTextView.setBackgroundResource(R.drawable.my_verification2);
    }

    @Override
    public void onFinish() {
        mTextView.setText("重新获取验证码");
        mTextView.setClickable(true);
        mTextView.setBackgroundResource(R.drawable.my_verification);
    }
}
