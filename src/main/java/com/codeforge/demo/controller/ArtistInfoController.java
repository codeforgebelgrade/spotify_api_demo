package com.codeforge.demo.controller;

import com.codeforge.demo.request.SpotifyAPIRequestHandler;
import jdk.jfr.ContentType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/artist-info")
public class ArtistInfoController {

    @GetMapping
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public CompletableFuture<String> getArtistInfo(@RequestParam(value="artistname") String artistName,
                                @RequestHeader(value = "Authorization") String auth) {

        return CompletableFuture.supplyAsync(() -> {

            String response = null;
            try {
                // some coment here
                // some additional comments
                // more comments here
                response = SpotifyAPIRequestHandler.executeHttpRequest(artistName, auth);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        });
    }
}
