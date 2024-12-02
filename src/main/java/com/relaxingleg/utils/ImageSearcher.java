package com.relaxingleg.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ImageSearcher {

    private final String accessKey;


    public ImageSearcher(String accessKey) {
        this.accessKey = accessKey;
    }


    public String buscarImagen(String palabraClave) throws IOException {
        OkHttpClient client = new OkHttpClient();


        String url = "https://api.unsplash.com/photos/random?query=" + palabraClave + "&client_id=" + accessKey;


        Request request = new Request.Builder()
                .url(url)
                .build();


        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                return extractImageUrlFromJson(responseBody);
            } else {
                System.out.println("Request failed with code: " + response.code());
                return null;
            }
        }
    }


    private String extractImageUrlFromJson(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        return jsonObject.getAsJsonObject("urls").get("regular").getAsString();
    }
}