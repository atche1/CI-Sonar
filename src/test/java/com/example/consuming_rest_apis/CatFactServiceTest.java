package com.example.consuming_rest_apis;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.StringReader;

@ExtendWith(MockitoExtension.class)
public class CatFactServiceTest {

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
        catFactService = new CatFactService(catFactRepository);
    }

    @Test
    public void testGetCatFactSuccess() throws Exception {
        String expectedFact = "{\"fact\":\"Cats are great!\"}";
        StringEntity entity = new StringEntity(expectedFact);

        when(httpClient.execute(any(HttpGet.class))).thenReturn(httpResponse);
        when(httpResponse.getEntity()).thenReturn(entity);

        try (BufferedReader reader = new BufferedReader(new StringReader(expectedFact))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String actualFact = catFactService.getCatFact();
            assertEquals(result.toString(), actualFact);
            verify(catFactRepository, times(1)).save(any(CatFact.class));
        }
    }

    @Test
    public void testGetCatFactIOException() throws Exception {
        when(httpClient.execute(any(HttpGet.class))).thenThrow(new IOException());

        assertThrows(IOException.class, () -> {
            catFactService.getCatFact();
        });

        verify(catFactRepository, never()).save(any(CatFact.class));
    }
}
