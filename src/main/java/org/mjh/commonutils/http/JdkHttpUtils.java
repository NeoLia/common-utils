package org.mjh.commonutils.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.HttpStatus;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * HTTP客户端工具（基于JDK）
 * @author Neo Lia
 * @date 2023/6/8 16:07
 */
public class JdkHttpUtils {
    /**
     * 默认创建连接最大时间（单位：毫秒）
     */
    public final static int DEFAULT_CONNECTION_TIMEOUT = 15000;
    /**
     * 默认创建读取连接最大时间（单位：毫秒）
     */
    public final static int DEFAULT_READ_TIMEOUT = 15000;
    
    /**
     * 发起Http请求（GET）
     * @param urlStr - HTTP/HTTPS URL
     * @return String - 返回响应报文体数据（JSON格式）
     * @throws IOException
     * @author Neo Lia
     */
    public static String requestGet(String urlStr) throws IOException {
        StringBuilder result = new StringBuilder();
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);
            connection.setRequestProperty(HttpHeaderEnum.ACCEPT.getKey(), HttpHeaderEnum.ACCEPT.getValue());
            connection.setRequestProperty(HttpHeaderEnum.CONNECTION.getKey(), HttpHeaderEnum.CONNECTION.getValue());
            connection.setRequestProperty(HttpHeaderEnum.USER_AGENT.getKey(), HttpHeaderEnum.USER_AGENT.getValue());

            connection.connect();
            final int responseCode = connection.getResponseCode();
            if (HttpStatus.SC_OK > responseCode || HttpStatus.SC_IM_USED < responseCode) {
                throw new IOException("异常响应码: " + responseCode);
            }

            inputStream = connection.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                String temp;
                while ((temp = bufferedReader.readLine()) != null) {
                    result.append(temp);
                }
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return result.toString();
    }

    /**
     * 发起Http请求（POST）
     * @param urlStr - HTTP/HTTPS URL
     * @param jsonData - 请求报文体数据（JSON格式）
     * @return String - 返回响应报文体数据（JSON格式）
     * @throws IOException
     * @author Neo Lia
     */
    public static String requestPost(String urlStr, String jsonData) throws IOException {
        StringBuilder result = new StringBuilder();
        HttpURLConnection connection = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);
            connection.setReadTimeout(DEFAULT_READ_TIMEOUT);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty(HttpHeaderEnum.ACCEPT.getKey(), HttpHeaderEnum.ACCEPT.getValue());
            connection.setRequestProperty(HttpHeaderEnum.CONNECTION.getKey(), HttpHeaderEnum.CONNECTION.getValue());
            connection.setRequestProperty(HttpHeaderEnum.USER_AGENT.getKey(), HttpHeaderEnum.USER_AGENT.getValue());
            connection.setRequestProperty(HttpHeaderEnum.CONTENT_TYPE.getKey(), HttpHeaderEnum.CONTENT_TYPE.getValue());

            // output request body data
            if (StringUtils.isNotBlank(jsonData)) {
                outputStream = connection.getOutputStream();
                outputStream.write(jsonData.getBytes(StandardCharsets.UTF_8));
            }

            connection.connect();
            final int responseCode = connection.getResponseCode();
            if (HttpStatus.SC_OK > responseCode || HttpStatus.SC_IM_USED < responseCode) {
                throw new IOException("异常响应码: " + responseCode);
            }

            inputStream = connection.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                String temp;
                while ((temp = bufferedReader.readLine()) != null) {
                    result.append(temp);
                }
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return result.toString();
    }
}
