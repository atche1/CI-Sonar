package com.example.consuming_rest_apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="cat-post",url = "https://jsonplaceholder.typicode.com")
public interface CatFactPost {
    @PostMapping("/posts")
    CatFact postFact(CatFact catFact);
}
