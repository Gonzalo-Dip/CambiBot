package com.relaxingleg.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ImageSearcher {

    private final String accessKey;
    private final OkHttpClient client;


    public ImageSearcher(String accessKey) {
        this.accessKey = accessKey;
        this.client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();
    }


    public String buscarImagen(String palabraClave) throws IOException {
        int maxAttempts = 3;
        for (int i = 1; i <= maxAttempts; i++) {
            try {

                String imageUrl = realizarBusqueda(palabraClave);
                if (imageUrl != null) {
                    return imageUrl;
                }
                System.out.println("Intento " + i + " fallido: No se encontraron resultados.");
            } catch (IOException e) {
                System.out.println("Intento " + i + " fallido: " + e.getMessage());
            }
        }
        return null;
    }


    private String realizarBusqueda(String palabraClave) throws IOException {

        String url = "https://api.unsplash.com/photos/random?query=" + palabraClave + "&client_id=" + accessKey;


        Request request = new Request.Builder()
                .url(url)
                .build();


        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                return extractImageUrlFromJson(responseBody);
            } else {
                System.out.println("Error en la respuesta de la API: Código " + response.code());
                return null;
            }
        }
    }


    private String extractImageUrlFromJson(String jsonResponse) {
        try {
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonObject urlsObject = jsonObject.getAsJsonObject("urls");
            if (urlsObject != null && urlsObject.has("regular")) {
                return urlsObject.get("regular").getAsString();
            } else {
                System.out.println("Estructura del JSON inesperada: " + jsonResponse);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al analizar el JSON: " + e.getMessage());
            return null;
        }
    }
}
