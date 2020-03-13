package com.codeforge.demo.request;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class SpotifyAPIRequestHandler {

    public static String executeHttpRequest(String artistName, String auth) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet("https://api.spotify.com/v1/search?q="+ artistName + "&type=artist");
        getRequest.setHeader("Authorization", auth);
        CloseableHttpResponse response = client.execute(getRequest);
        String responseContent = null;

        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseContent = EntityUtils.toString(entity);
            }
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return responseContent;
    }
}
