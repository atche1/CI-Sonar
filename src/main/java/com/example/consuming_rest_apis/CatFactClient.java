package com.example.consuming_rest_apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="cat-service",url = "https://catfact.ninja")
public interface CatFactClient {
    @GetMapping("/fact")
    CatFact getCatsInfo();
}
