package com.example.consuming_rest_apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class CatFactService {

    private CatFactRepository catFactRepository;

    public CatFactService(CatFactRepository catFactRepository) {
        this.catFactRepository = catFactRepository;
    }

    public String getCatFact() throws IOException {
        String url = "https://catfact.ninja/fact";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = client.execute(request)) {
                String line;
                StringBuilder result = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                CatFact catFact = new CatFact();
                catFact.setFact(result.toString());
                catFactRepository.save(catFact);
                return result.toString();
            }
        }
    }

    public String postCatFact(CatFact catFact) throws IOException {
        String url = "https://jsonplaceholder.typicode.com/posts";

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(catFact);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(url);
            StringEntity entity = new StringEntity(jsonData, ContentType.APPLICATION_JSON);
            request.setEntity(entity);
            catFactRepository.save(catFact);
            try (CloseableHttpResponse response = client.execute(request)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    return "You have sent your post request successfully!";
                } else {
                    return "Your post request sent a message:" + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase();
                }
            }
        }

    }
}
