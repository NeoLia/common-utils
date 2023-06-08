package org.mjh.commonutils.http;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.HttpEntities;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * HTTP客户端工具（基于Apache httpclient5）
 * @author Neo Lia
 * @date 2023/6/8 16:56
 */
public class ApacheHttpUtils {
    /**
     * 发起Http请求（GET）
     * @param urlStr - HTTP/HTTPS URL
     * @return String - 返回响应报文体数据（JSON格式）
     * @throws IOException
     * @author Neo Lia
     */
    public static String requestGet(String urlStr) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(urlStr);
            httpGet.addHeader(HttpHeaderEnum.ACCEPT.getKey(), HttpHeaderEnum.ACCEPT.getValue());
            httpGet.addHeader(HttpHeaderEnum.CONNECTION.getKey(), HttpHeaderEnum.CONNECTION.getValue());
            httpGet.addHeader(HttpHeaderEnum.USER_AGENT.getKey(), HttpHeaderEnum.USER_AGENT.getValue());
            // handler
            HttpClientResponseHandler<String> responseHandler = handleResponse();

            final String responseBody = httpClient.execute(httpGet, responseHandler);
            return responseBody;
        }
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
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(urlStr);
            httpPost.addHeader(HttpHeaderEnum.ACCEPT.getKey(), HttpHeaderEnum.ACCEPT.getValue());
            httpPost.addHeader(HttpHeaderEnum.CONNECTION.getKey(), HttpHeaderEnum.CONNECTION.getValue());
            httpPost.addHeader(HttpHeaderEnum.USER_AGENT.getKey(), HttpHeaderEnum.USER_AGENT.getValue());
            // body
            HttpEntity requestEntity = HttpEntities.create(jsonData, StandardCharsets.UTF_8);
            httpPost.setEntity(requestEntity);
            // handler
            HttpClientResponseHandler<String> responseHandler = handleResponse();

            final String responseBody = httpClient.execute(httpPost, responseHandler);
            return responseBody;
        }
    }

    /**
     * 处理响应报文
     * @return HttpClientResponseHandler<String>
     * @author Neo Lia
     */
    private static HttpClientResponseHandler<String> handleResponse() {
        return response -> {
            if (response == null) {
                throw new ClientProtocolException("Response is null");
            }
            final int responseCode = response.getCode();
            if (HttpStatus.SC_OK > responseCode || HttpStatus.SC_IM_USED < responseCode) {
                throw new ClientProtocolException("Unexpected response code: " + responseCode);
            }

            final HttpEntity responseEntity = response.getEntity();
            final String result = responseEntity == null ? null : EntityUtils.toString(responseEntity);
            return result;
        };
    }
}
