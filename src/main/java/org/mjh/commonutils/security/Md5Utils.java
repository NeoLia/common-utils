package org.mjh.commonutils.security;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 信息摘要-MD5工具类
 * @author Neo Lia
 * @date 2023/6/2 15:17
 */
public class Md5Utils {
    private static final String DIGEST_ALGORITHM_MD5 = "MD5";
    private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 将给定数据转换为MD5信息摘要
     * @param data - 被转换的数据
     * @param charset - 数据的字符编码
     * @return byte[] - MD5信息摘要
     * @throws NoSuchAlgorithmException
     */
    public static byte[] md5(String data, Charset charset) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM_MD5);
        messageDigest.update(data.getBytes(charset));
        return messageDigest.digest();
    }

    /**
     * 将给定数据转换为MD5信息摘要
     * @param data - 被转换的数据
     * @return byte[] - MD5信息摘要
     * @throws NoSuchAlgorithmException
     * @author Neo Lia
     */
    public static byte[] md5(String data) throws NoSuchAlgorithmException {
        return md5(data, StandardCharsets.UTF_8);
    }

    /**
     * 将给定数据转换为十六进制的32位md5信息摘要字符串
     * @param data - 被转换的数据
     * @param charset - 数据的字符编码
     * @return String - 返回十六进制的32位md5信息摘要字符串
     * @throws NoSuchAlgorithmException
     * @author Neo Lia
     */
    public static String md5HexString(String data, Charset charset) throws NoSuchAlgorithmException {
        byte[] md5Bytes = md5(data, charset);
        String md5HexString = encodeMd5DigestToHex(md5Bytes);
        return md5HexString;
    }

    /**
     * 将给定数据转换为十六进制的32位md5信息摘要字符串
     * @param data - 被转换的数据
     * @return String - 返回十六进制的32位md5信息摘要字符串
     * @throws NoSuchAlgorithmException
     * @author Neo Lia
     */
    public static String md5HexString(String data) throws NoSuchAlgorithmException {
        byte[] md5Bytes = md5(data, StandardCharsets.UTF_8);
        String md5HexString = encodeMd5DigestToHex(md5Bytes);
        return md5HexString;
    }

    /**
     * 将MD5信息摘要转为十六进制的32位字符串
     * @param bytes - MD5信息摘要
     * @return String - 返回十六进制的32位字符串
     * @author Neo Lia
     */
    private static String encodeMd5DigestToHex(byte[] bytes) {
        char[] chars = new char[32];

        for(int i = 0; i < chars.length; i += 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[b >>> 4 & 15];
            chars[i + 1] = HEX_CHARS[b & 15];
        }

        return new String(chars);
    }
}
