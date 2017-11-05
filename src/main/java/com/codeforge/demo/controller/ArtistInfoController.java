package com.codeforge.demo.controller;

import com.codeforge.demo.request.SpotifyAPIRequestHandler;
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
    public CompletableFuture<String> getArtistInfo(@RequestParam(value="artistname", required = true) String artistName,
                                @RequestHeader(value = "Authorization", required = true) String auth) throws InterruptedException, ExecutionException, TimeoutException {

        CompletableFuture<String> responseFuture = CompletableFuture.supplyAsync(() -> {

            String response = null;
            try {
                response = SpotifyAPIRequestHandler.executeHttpRequest(artistName, auth);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        });

        return responseFuture;
    }


}
