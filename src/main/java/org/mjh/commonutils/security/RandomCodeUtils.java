package org.mjh.commonutils.security;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author Neo Lia
 * @date 2023/11/16 13:58
 */
public class RandomCodeUtils {
    public static String randomUuidCode() {
        String code = UUID.randomUUID().toString().replace("-", "");
        return code;
    }

    public static void main(String[] args) {
        String code = randomUuidCode();
        System.out.println(code);
        System.out.println(Base64.encodeBase64URLSafeString(code.getBytes(StandardCharsets.UTF_8)));
    }
}
