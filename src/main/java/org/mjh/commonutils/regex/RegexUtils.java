package org.mjh.commonutils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Neo Lia
 * @date 2023/5/8 16:31
 */
public class RegexUtils {
    public static boolean isChineseMainlandMobilePhone(String str) {
        return match(CommonRegexEnum.CHINESE_MAINLAND_MOBILE_PHONE.getRegex(), str);
    }

    public static boolean isEmail(String str) {
        return match(CommonRegexEnum.EMAIL.getRegex(), str);
    }

    public static boolean isIdNo(String str) {
        return match(CommonRegexEnum.ID_NO.getRegex(), str);
    }

    /**
     * 字符串是否匹配指定正则表达式
     * @param regex - 正则表达式
     * @param str - 待校验字符串
     * @return boolean
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String str = "4418811998082274";
        System.out.println(isIdNo(str));
        int[] a = new int[] {1,2};
    }
}
