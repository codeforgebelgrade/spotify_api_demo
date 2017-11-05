package com.codeforge.demo.request;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class SpotifyAPIRequestTest {

    @Test
    public void getArtisInfoTest() throws IOException {

        String responseContent = SpotifyAPIRequestHandler.executeHttpRequest("Nickelback",
                "Bearer BQASdWGDWXoU6uCy5pVrPwsAZLwczxHaaeaPXA-K8S2iFIiAzy_KzNZFhoym0SeGQRphi28iLbELmWsxJR6ecvKVPIdO7zH076aVZdEpLiridpsbY2GJESblYm4ECUMa2ctPM36IkYRz");

        Assert.assertNotNull(responseContent);
    }
}