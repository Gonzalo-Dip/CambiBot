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
                return parseWeatherJson(responseBody);
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

        int humedad = jsonObject
                .getAsJsonObject("main")
                .get("humidity")
                .getAsInt();

        double velocidadViento = jsonObject
                .getAsJsonObject("wind")
                .get("speed")
                .getAsDouble();

        int presion = jsonObject
                .getAsJsonObject("main")
                .get("pressure")
                .getAsInt();

        long amanecer = jsonObject
                .getAsJsonObject("sys")
                .get("sunrise")
                .getAsLong();

        long atardecer = jsonObject
                .getAsJsonObject("sys")
                .get("sunset")
                .getAsLong();

        
        String horaAmanecer = convertirUnixATiempo(amanecer);
        String horaAtardecer = convertirUnixATiempo(atardecer);

        
        return String.format(
                "Clima: %s\n" +
                        "Temperatura: %.1f°C\n" +
                        "Sensación térmica: %.1f°C\n" +
                        "Humedad: %d%%\n" +
                        "Velocidad del viento: %.1f m/s\n" +
                        "Presión atmosférica: %d hPa\n" +
                        "Amanecer: %s\n" +
                        "Atardecer: %s",
                descripcion,
                temperatura,
                sensacionTermica,
                humedad,
                velocidadViento,
                presion,
                horaAmanecer,
                horaAtardecer
        );
    }

    
    private String convertirUnixATiempo(long unixTime) {
        java.util.Date fecha = new java.util.Date(unixTime * 1000L);
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(java.util.TimeZone.getDefault());
        return sdf.format(fecha);
    }
}