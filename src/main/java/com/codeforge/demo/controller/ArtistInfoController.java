package com.codeforge.demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/artist-info")
public class ArtistInfoController {

    @GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public String getArtistInfo(@RequestParam(value="artistname", required=true) String artistName,
                                @RequestHeader(value = "Authorization") String auth) throws IOException, InterruptedException, ExecutionException, TimeoutException {

        CompletableFuture<String> responseFuture = CompletableFuture.supplyAsync(() -> {

            String response = null;
            try {
                response = executeHttpRequest(artistName, auth);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        });

        return responseFuture.get(5, TimeUnit.SECONDS);
    }

    private String executeHttpRequest(String artistName, String auth) throws IOException {

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
