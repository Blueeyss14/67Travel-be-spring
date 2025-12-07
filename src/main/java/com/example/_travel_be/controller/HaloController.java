package com.example._travel_be.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HaloController {

    @GetMapping("/hello")
    public Map<String, Object> home() {
        Map<String, Object> res = new HashMap<>();
        res.put("message", "woy hallo");
        res.put("status", "ok");
        return res;
    }
}
