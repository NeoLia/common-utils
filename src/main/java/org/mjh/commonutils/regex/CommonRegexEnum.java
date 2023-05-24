package org.mjh.commonutils.regex;

/**
 * 常用正则表达式枚举
 * @author Neo Lia
 * @date 2023/5/8 16:40
 */
public enum CommonRegexEnum {
    /**
     * 中国大陆手机号码
     */
    CHINESE_MAINLAND_MOBILE_PHONE("1(3|5|6|7|8|9)[0-9]{9}$", "中国大陆手机号码"),
    /**
     * 电子邮箱
     */
    EMAIL("^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", "电子邮箱"),
    /**
     * 身份证号码
     */
    ID_NO("^(\\d{18}|\\d{15}|\\d{17}(x|X))$", "身份证号码")
    ;

    private String regex;
    private String description;

    CommonRegexEnum(String regex, String description) {
        this.regex = regex;
        this.description = description;
    }

    public String getRegex() {
        return regex;
    }

    public String getDescription() {
        return description;
    }
}
