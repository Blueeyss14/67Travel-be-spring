package com.example._travel_be.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HaloController {

    @GetMapping("/hello")
    public String home() {
        return "woy hallo";
    }
}
