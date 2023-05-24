package org.mjh.commonutils.security;

/**
 * @author Neo Lia
 * @date 2023/5/24 11:33
 */
public enum SignatureAlgorithmEnum {
    /**
     * MD5withRSA
     */
    MD5_WITH_RSA("MD5withRSA"),
    /**
     * SHA1withRSA
     */
    SHA1_WITH_RSA("SHA1withRSA"),
    /**
     * SHA256withRSA
     */
    SHA256_WITH_RSA("SHA256withRSA"),
    /**
     * SHA384withRSA"
     */
    SHA384_WITH_RSA("SHA384withRSA"),
    /**
     * SHA512withRSA
     */
    SHA512_WITH_RSA("SHA512withRSA")
    ;

    private final String code;

    SignatureAlgorithmEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
