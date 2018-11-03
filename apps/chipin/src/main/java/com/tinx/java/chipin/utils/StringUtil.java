package com.tinx.java.chipin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class StringUtil {

    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

    static final String AES_KEY_ALGORITHM = "AES";
    static final String AES_CIPHER_ECB_PKCS5 = "AES/ECB/PKCS5Padding";

    private StringUtil() {}

    public static String MD5(String value) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(value.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException e) {
            logger.error("", e);
        }
        catch (UnsupportedEncodingException e) {
            logger.error("", e);
        }

        if (messageDigest == null) {
            return null;
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            }
            else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }

        return md5StrBuff.toString();
    }

    /**
     * AES加密
     *
     * @param value    待加密内容
     * @param password 秘钥
     * @return
     */
    public static String encryptAES(String value, String password) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), AES_KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_CIPHER_ECB_PKCS5);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(value.getBytes("utf-8"));
            return bytesToHexString(encrypted);
        }
        catch (Exception e) {
            logger.error("encrypt ase failed", e);
        }
        return null;
    }

    /**
     * AES解密
     *
     * @param value
     * @param password 秘钥
     * @return
     */
    public static String decryptAES(String value, String password) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), AES_KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(AES_CIPHER_ECB_PKCS5);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] decrypted = cipher.doFinal(hexStringToBytes(value));
            return new String(decrypted);
        }
        catch (Exception e) {
            logger.error("decrypt aes failed", e);
        }
        return null;
    }

    public static String base64Encode(String value) {
        if (isBlank(value)) {
            return "";
        }
        return new BASE64Encoder().encode(value.getBytes());
    }

    public static byte[] base64Decode(String base64) {
        if (isBlank(base64)) {
            return new byte[]{};
        }
        try {
            return new BASE64Decoder().decodeBuffer(base64);
        }
        catch (Exception exp) {
            logger.error("", exp);
            return null;
        }
    }

    public static String truncate(String src, int maxLength) {
        if (src == null) {
            return "";
        }
        if (maxLength <= 0) {
            return src;
        }
        if (maxLength >= src.length()) {
            return src;
        }

        if (maxLength <= 3) {
            return src.substring(0, maxLength);
        }
        return src.substring(0, maxLength - 3) + "...";
    }

    public static boolean isBlank(String str) {
        return !isPresent(str);
    }

    public static boolean isPresent(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | (charToByte(hexChars[pos + 1]) & 0xff));
        }
        return d;
    }

    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    
    public static String valueOf(Object object, String nullCase) {
        if (object == null) {
            return nullCase;
        }
    
        return object.toString();
    }

    public static boolean equals(String obj1, String obj2) {
        if (null == obj1 && null == obj2) {
            return true;
        } else if (null != obj1) {
            return obj1.equals(obj2);
        } else {
            return obj2.equals(obj1);
        }
    }

    /**
     * 
     * 创建时间 2018-9-7 16:20
     * @author 陆国鸿
     * @param obj1
     * @param obj2
     * @return
     */
    public static int compare(String obj1, String obj2) {
        if (obj1 == null && obj2 == null) {
            return 0;
        } else if (obj1 == null && obj2.length() < 1) {
            return 0;
        } else if (obj2 == null && obj1.length() < 1) {
            return 0;
        } else {
            int result = obj1.compareTo(obj2);
            if (result > 0) {
                return 1;
            } else if (result < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
