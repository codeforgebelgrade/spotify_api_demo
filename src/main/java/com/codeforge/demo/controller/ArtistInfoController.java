package com.codeforge.demo.controller;

import com.codeforge.demo.request.SpotifyAPIRequestHandler;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/artist-info")
public class ArtistInfoController {

    @Bean
    public HandlerExceptionResolver sentryExceptionResolver() {
        return new io.sentry.spring.SentryExceptionResolver();
    }

    @Bean
    public ServletContextInitializer sentryServletContextInitializer() {
        return new io.sentry.spring.SentryServletContextInitializer();
    }

    @GetMapping
    @RequestMapping(method = RequestMethod.GET)
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

    @RequestMapping("/sentry")
    @ResponseBody
    String home() {
        int x = 1 / 0;

        return "Hello World!";
    }
}
