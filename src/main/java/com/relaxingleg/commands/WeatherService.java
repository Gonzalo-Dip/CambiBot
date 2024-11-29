package com.relaxingleg.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WeatherService {

    private final String apiKey;

    public WeatherService(String apiKey) {
        this.apiKey = apiKey;
    }

    public String obtenerPronostico(String ciudad) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + ciudad + "&units=metric&lang=es&appid=" + apiKey;


        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                return parseWeatherJson(responseBody); // Analizar el JSON y devolver el resultado
            } else {
                System.out.println("Request failed with code: " + response.code());
                return "Error: No se pudo obtener el pronóstico para " + ciudad;
            }
        }
    }


    private String parseWeatherJson(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        String descripcion = jsonObject
                .getAsJsonArray("weather")
                .get(0)
                .getAsJsonObject()
                .get("description")
                .getAsString();

        double temperatura = jsonObject
                .getAsJsonObject("main")
                .get("temp")
                .getAsDouble();

        double sensacionTermica = jsonObject
                .getAsJsonObject("main")
                .get("feels_like")
                .getAsDouble();

        return String.format("Clima: %s\nTemperatura: %.1f°C\nSensación térmica: %.1f°C", descripcion, temperatura, sensacionTermica);
    }
}