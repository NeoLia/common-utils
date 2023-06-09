package org.mjh.commonutils.security;

import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 非对称加密-RSA工具类
 * @author Neo Lia
 * @date 2023/5/23 17:51
 */
public class RsaUtils {
    private final static SignatureAlgorithmEnum SIGNATURE_ALGORITHM = SignatureAlgorithmEnum.SHA1_WITH_RSA;

    /**
     * Base64编码私钥字符串转换为私钥对象
     * @param privateKeyStr - Base64编码私钥字符串
     * @return PrivateKey - 返回私钥对象
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @author Neo Lia
     */
    public static PrivateKey transformToPrivateKey(String privateKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] buffer = Base64.decodeBase64(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * Base64编码公钥字符串转换为私钥对象
     * @param publicKeyStr - Base64编码公钥字符串
     * @return PublicKey - 返回公钥对象
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @author Neo Lia
     */
    public static PublicKey transformToPublicKey(String publicKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] buffer = Base64.decodeBase64(publicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 使用RSA生成Base64编码签名字符串（不补位）
     * @param privateKey - 密钥
     * @param data - 签名数据
     * @return String - Base64编码签名字符串（不补位）
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     * @author Neo Lia
     */
    public static String sign(PrivateKey privateKey, String data) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM.getCode());
        signature.initSign(privateKey);
        signature.update(data.getBytes(StandardCharsets.UTF_8));
        byte[] resultBytes = signature.sign();
        return Base64.encodeBase64URLSafeString(resultBytes).trim();
    }

    /**
     * 获取已排序的签名数据
     * @param dataMap - 签名数据TreeMap对象
     * @return String - 已排序的签名数据。如：a=1&b=2&c=3
     * @author Neo Lia
     */
    public static String getSortedSignData(TreeMap<String, String> dataMap) {
        Set<Map.Entry<String, String>> entrySet = dataMap.entrySet();
        boolean isFirst = true;
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : entrySet) {
            if (isFirst) {
                isFirst = false;
            }
            else {
                builder.append("&");
            }

            builder.append(entry.getKey());
            builder.append("=");
            builder.append(entry.getValue());
        }
        return builder.toString();
    }

    public static void main(String[] args) {
    }
}
