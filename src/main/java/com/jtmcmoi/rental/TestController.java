package com.jtmcmoi.rental;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/ping")
    public String getPing() {
        return "Pong";
    }
    

}
