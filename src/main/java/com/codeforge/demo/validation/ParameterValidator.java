package com.codeforge.demo.validation;

import java.util.ArrayList;
import java.util.List;

public class ParameterValidator {

    public static boolean checkForSpecialCharacters(String queryParam){
        return queryParam.contains("*") ||
                queryParam.contains("!") ||
                queryParam.contains("#");
    }

    public static boolean checkForBlacklistedArtist(String artistName) {
        List<String> blacklistedArtists = new ArrayList<>();
        blacklistedArtists.add("Ceca");
        blacklistedArtists.add("Jeca");
        blacklistedArtists.add("Dara");
        return blacklistedArtists.contains(artistName);
    }
}
