package org.mjh.commonutils.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mjh.commonutils.http.ApacheHttpUtils;

import java.io.IOException;

/**
 * @author Neo Lia
 * @date 2023/6/9 9:08
 */
public class ApacheHttpUtilsTest {
    @ParameterizedTest
    @CsvSource({"https://www.baidu.com"})
    public void testGet(String urlStr) {
        try {
            String responseBody = ApacheHttpUtils.requestGet(urlStr);
            Assertions.assertTrue(StringUtils.isNotBlank(responseBody), "响应报文体为空");
            System.out.println("responseBody: " + responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({"https://www.baidu.com"})
    public void testPost(String urlStr) {
        try {
            String responseBody = ApacheHttpUtils.requestPost(urlStr, "");
            System.out.println("responseBody: " + responseBody);
            Assertions.assertTrue(StringUtils.isNotBlank(responseBody), "响应报文体为空");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
