package com.ants.api.common;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by 이충일 (betterfly@wemakeprice.com)
 * Date : 2019.09.04
 */
@Component
public class RestApiClient {

    private final HttpClient client = HttpClientBuilder.create().build();

    public HttpResponse postClient(String url, String jsonMessage) throws IOException {
        HttpPost postRequest = new HttpPost(url);
        postRequest.setHeader("Accept", "application/json");
        postRequest.setHeader("Connection", "keep-alive");
        postRequest.setHeader("Content-Type", "application/json");

        postRequest.setEntity(new StringEntity(jsonMessage, HTTP.UTF_8));

        return client.execute(postRequest);
    }

    public HttpResponse getClient(String url) throws IOException {
        return client.execute(new HttpGet(url));
    }
}
