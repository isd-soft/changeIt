package com.internship.changeit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.changeit.googleModels.GeocodeResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/geocode")
public class GoogleMapsController {

    @Value("${apiKey}")
    private String apiKey;

    @GetMapping
    public ResponseEntity<?> getGeocode(@RequestParam String address) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        Request request = new Request.Builder()
                .url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=en&address=" + encodedAddress)
                .get()
                .addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
                .addHeader("x-rapidapi-key", apiKey)
                .build();
        ResponseBody responseBody = client.newCall(request).execute().body();

        ObjectMapper objectMapper = new ObjectMapper();
        GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);

        return ResponseEntity.ok(result);
    }
}
