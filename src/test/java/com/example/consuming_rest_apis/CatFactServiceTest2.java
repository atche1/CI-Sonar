package com.example.consuming_rest_apis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CatFactServiceTest2 {
    @Mock
    private CatFactRepository catFactRepository;

    @InjectMocks
    private CatFactService catFactService;

    @Mock
    private CloseableHttpClient httpClient;

    @Mock
    private CloseableHttpResponse httpResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCatFactIOException() throws Exception {
        when(httpClient.execute(any(HttpGet.class))).thenThrow(new IOException("Network error"));

        try {
            catFactService.getCatFact();
        } catch (IOException e) {
            assertEquals("Network error", e.getMessage());
        }
    }
}
