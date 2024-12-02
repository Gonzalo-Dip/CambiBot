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


        JsonObject weather = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject();
        String descripcion = weather.get("description").getAsString();
        String iconCode = weather.get("icon").getAsString();
        String icono = obtenerIcono(iconCode);


        double temperatura = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
        double tempMinima = jsonObject.getAsJsonObject("main").get("temp_min").getAsDouble();
        double tempMaxima = jsonObject.getAsJsonObject("main").get("temp_max").getAsDouble();
        int humedad = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
        double velocidadViento = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
        int presion = jsonObject.getAsJsonObject("main").get("pressure").getAsInt();


        String probabilidadLluvia = jsonObject.has("rain")
                ? jsonObject.getAsJsonObject("rain").get("1h").getAsString() + "%"
                : "No disponible";


        long amanecerUnix = jsonObject.getAsJsonObject("sys").get("sunrise").getAsLong();
        long atardecerUnix = jsonObject.getAsJsonObject("sys").get("sunset").getAsLong();
        String amanecer = convertirUnixATiempo(amanecerUnix);
        String atardecer = convertirUnixATiempo(atardecerUnix);


        return String.format(
                "Clima: %s %s\n" +
                        "Temperatura actual: %.1f°C\n" +
                        "Temperatura mínima: %.1f°C\n" +
                        "Temperatura máxima: %.1f°C\n" +
                        "Humedad: %d%%\n" +
                        "Presión atmosférica: %d hPa\n" +
                        "Probabilidad de lluvia: %s\n" +
                        "Velocidad del viento: %.1f m/s\n" +
                        "Amanecer: %s\n" +
                        "Atardecer: %s",
                icono, descripcion,
                temperatura, tempMinima, tempMaxima,
                humedad, presion, probabilidadLluvia,
                velocidadViento, amanecer, atardecer
        );
    }

    private String obtenerIcono(String iconCode) {
        switch (iconCode) {
            case "01d": return "☀️";
            case "01n": return "🌙";
            case "02d": case "02n": return "🌤";
            case "03d": case "03n", "04d", "04n": return "☁️";
            case "09d": case "09n": return "🌧";
            case "10d": case "10n": return "🌦";
            case "11d": case "11n": return "⛈";
            case "13d": case "13n": return "❄️";
            case "50d": case "50n": return "🌫";
            default: return "";
        }
    }

    private String convertirUnixATiempo(long unixTimestamp) {
        java.time.Instant instant = java.time.Instant.ofEpochSecond(unixTimestamp);
        java.time.ZoneId zoneId = java.time.ZoneId.systemDefault();
        java.time.ZonedDateTime zonedDateTime = java.time.ZonedDateTime.ofInstant(instant, zoneId);
        return zonedDateTime.toLocalTime().toString();
    }
}