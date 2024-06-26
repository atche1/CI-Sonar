//package com.example.consuming_rest_apis;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.io.IOException;
//
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//
//import org.apache.http.util.EntityUtils;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class CatFactServiceTest {
//
//    @Mock
//    private CatFactRepository catFactRepository;
//
//    @InjectMocks
//    private CatFactService catFactService;
//
//    @Mock
//    private CloseableHttpClient httpClient;
//
//    @Mock
//    private CloseableHttpResponse httpResponse;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testPostCatFactRepositoryException() throws Exception {
//        CatFact catFact = new CatFact();
//        catFact.setFact("Cats are awesome!");
//
//        doThrow(new RuntimeException("Database error")).when(catFactRepository).save(any(CatFact.class));
//
//        try {
//            catFactService.postCatFact(catFact);
//        } catch (RuntimeException e) {
//            assertEquals("Database error", e.getMessage());
//        }
//    }
//
//}

