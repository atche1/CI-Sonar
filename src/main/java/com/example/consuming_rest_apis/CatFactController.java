package com.example.consuming_rest_apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CatFactController {
    @Autowired
    private CatFactRepository catFactRepository;

    private final CatFactService catFactService;

    private final CatFactClient catFactClient;
    private CatFactPost catFactPost;

    @Autowired
    public CatFactController(CatFactService catFactService, CatFactClient catFactClient,CatFactPost catFactPost) {
        this.catFactPost = catFactPost;
        this.catFactService = catFactService;
        this.catFactClient = catFactClient;
    }
    @GetMapping("/info")
    public ResponseEntity<CatFact> getCatFactInfo(){
        CatFact catFact = catFactClient.getCatsInfo();
        catFactRepository.save(catFact);
        return ResponseEntity.ok(catFact);
    }
    @PostMapping("/add")
    public ResponseEntity<CatFact> addCatFactInfo(@RequestBody CatFact catFact){
        catFactPost.postFact(catFact);
        catFactRepository.save(catFact);
        return ResponseEntity.ok(catFact);
    }

    @GetMapping("/catfact")
    public String getCatFact() throws IOException {
        return catFactService.getCatFact();
    }

    @PostMapping("/post")
    public String postCatFact(@RequestBody CatFact catFact) throws IOException {
        return catFactService.postCatFact(catFact);
    }

}
