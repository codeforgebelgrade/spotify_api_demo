package com.codeforge.demo.exceptions;

public class BlacklistedArtistException extends RuntimeException {

    public BlacklistedArtistException(String artistName) {
        super("Your search cannot be executed because the following artist is blacklisted: " + artistName);
    }
}
