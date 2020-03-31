package com.codeforge.demo.controller;

import com.codeforge.demo.configuration.MyConfiguration;
import com.codeforge.demo.request.SpotifyAPIRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/artist-info")
public class ArtistInfoController {

    @Autowired
    private Environment env;

    @Autowired
    private MyConfiguration properties;

    @GetMapping("/consul")
    public String getConfigFromProperty() {
        return properties.getProp();
    }

    @GetMapping
    public CompletableFuture<String> getArtistInfo(@RequestParam(value="artistname") String artistName,
                                @RequestHeader(value = "Authorization") String auth) {

        return CompletableFuture.supplyAsync(() -> {

            String response = null;
            try {
                response = SpotifyAPIRequestHandler.executeHttpRequest(artistName, auth);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        });
    }
}
