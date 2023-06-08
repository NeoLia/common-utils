package org.mjh.commonutils.http;

/**
 * HTTP 报文头枚举
 * @author Neo Lia
 * @date 2023/6/8 17:06
 */
public enum HttpHeaderEnum {
    /**
     * Accept
     */
    ACCEPT("Accept", "*/*"),
    /**
     * Connection
     */
    CONNECTION("Connection", "keep-alive"),
    /**
     * User-Agent
     */
    USER_AGENT("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.41"),
    /**
     * Content-Type
     */
    CONTENT_TYPE("Content-Type", "application/json;charset=utf-8")
    ;

    private String key;
    private String value;

    HttpHeaderEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
