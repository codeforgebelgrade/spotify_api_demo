package com.codeforge.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist-info")
public class ArtistInfoController {

    @GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public String welcome() {
        return "Hey! Welcome!";
    }
}
