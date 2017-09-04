package zhou.com.loveme.utils;

import java.nio.charset.Charset;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by zhou on 2017/8/10.
 */
public class DES3Util {
    public static String encrypt3DES(String input, String key) {
        return encrypt3DES(input, key, Charset.forName("GB2312"));
    }

    public static String encrypt3DES(String input, String key, Charset charset) {
        try {
            return byte2hex(DES3Util.encrypt(input.getBytes(charset.name()),
                    key.getBytes()));
        } catch (Exception localException) {
        }
        return "";
    }

    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(1, securekey, sr);

        return cipher.doFinal(src);
    }

    public static String byte2hex(byte[] buffer) {
        String hs = "";
        String stmp = "";

        for (int n = 0; n < buffer.length; n++) {
            stmp = Integer.toHexString(buffer[n] & 0xFF);

            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }

        return hs.toUpperCase();
    }
}