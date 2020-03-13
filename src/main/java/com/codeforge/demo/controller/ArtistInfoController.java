package com.codeforge.demo.controller;

import com.codeforge.demo.exceptions.BlacklistedArtistException;
import com.codeforge.demo.exceptions.ParameterValidationException;
import com.codeforge.demo.request.SpotifyAPIRequestHandler;
import com.codeforge.demo.validation.ParameterValidator;
import com.mindscapehq.raygun4java.core.RaygunClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/artist-info")
public class ArtistInfoController {

    @GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<String> getArtistInfo(@RequestParam(value="artistname") String artistName,
                                @RequestHeader(value = "Authorization") String auth) {

        if(ParameterValidator.checkForSpecialCharacters(artistName)) {
            throw new ParameterValidationException("artistname");
        }

        if(ParameterValidator.checkForBlacklistedArtist(artistName)) {
            throw new BlacklistedArtistException(artistName);
        }

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

    @ExceptionHandler(ParameterValidationException.class)
    public final ResponseEntity handleMandatoryParameterException(ParameterValidationException ex) {
        RaygunClient client = new RaygunClient("kuMVYu0Pa2e449D9M2fC9w");
        client.send(ex);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BlacklistedArtistException.class)
    public final ResponseEntity handleBlackListedArtistException(BlacklistedArtistException ex) {
        RaygunClient client = new RaygunClient("kuMVYu0Pa2e449D9M2fC9w");
        client.send(ex);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


}
