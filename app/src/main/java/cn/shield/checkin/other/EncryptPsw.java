package cn.shield.checkin.other;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * author: lixin(<CHARS href="mailto:lixin@danlu.com">lixin@danlu.com</CHARS>)<br/>
 * version: 1.0.0<br/>
 * since: 2017-10-12 14:41<br/>
 *
 * <p>
 * 内容描述区域
 * </p>
 */
public class EncryptPsw {

    private static final char[] CHARS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    public static String encrypt(String paramString) {
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramString.getBytes());
            paramString = encrypt(localMessageDigest.digest());
            return paramString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String encrypt(byte[] paramArrayOfByte) {
        StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
        for (int i = 0; i < paramArrayOfByte.length; i++) {
            localStringBuilder.append(CHARS[((paramArrayOfByte[i] & 0xF0) >>> 4)]);
            localStringBuilder.append(CHARS[(paramArrayOfByte[i] & 0xF)]);
        }
        return localStringBuilder.toString();
    }
}
