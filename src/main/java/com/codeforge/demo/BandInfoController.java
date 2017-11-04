package com.codeforge.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BandInfoController {

    @RequestMapping("/demo")
    public String welcome() {
        return "Hey! Welcome!";
    }
}
