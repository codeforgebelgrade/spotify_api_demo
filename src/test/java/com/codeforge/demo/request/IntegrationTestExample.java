package com.codeforge.demo.request;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IntegrationTestExample {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getArtistInfo() {

        // TODO: Bearer tokens should be handled differently during test execution
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("BQBq-yVpo3cwiNYGp3jhagj2B0HyVlTQks2LPiJp8tr_pIar3o66jUvDavmurlFOaE44R3LNExDtTfKCDwAE0mNy5BbSzHU-p4wAwlEZkLXrt-TTPr6Jay9W22Mgg-10Q7blPN-1hzVALh_WSqbEe5Js7nnTLXV5KFTQUcA19yfu");
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = this.testRestTemplate.exchange("http://localhost:" + port + "/artist-info?artistname=Behemoth",
                HttpMethod.GET, request, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
