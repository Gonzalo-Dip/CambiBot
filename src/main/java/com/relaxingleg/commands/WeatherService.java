package com.relaxingleg.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WeatherService {

    private final String apiKey;

    // Constructor para inicializar la API Key
    public WeatherService(String apiKey) {
        this.apiKey = apiKey;
    }

    // Método público para obtener el pronóstico del clima por ciudad
    public String obtenerPronostico(String ciudad) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Construir la URL para la petición
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + ciudad + "&units=metric&lang=es&appid=" + apiKey;

        // Crear la petición
        Request request = new Request.Builder()
                .url(url)
                .build();

        // Ejecutar la petición y manejar la respuesta
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

    // Método privado para analizar el JSON de respuesta y extraer el pronóstico
    private String parseWeatherJson(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        // Extraer datos principales
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

        double tempMinima = jsonObject
                .getAsJsonObject("main")
                .get("temp_min")
                .getAsDouble();

        double tempMaxima = jsonObject
                .getAsJsonObject("main")
                .get("temp_max")
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

        // Extraer datos de probabilidad de lluvia (si están disponibles)
        String probabilidadLluvia = "Sin datos de lluvia";
        if (jsonObject.has("rain")) {
            double lluvia = jsonObject
                    .getAsJsonObject("rain")
                    .get("1h") // Llueve en la última hora
                    .getAsDouble();
            probabilidadLluvia = "Probabilidad de lluvia: " + lluvia + " mm";
        }

        // Formatear los tiempos de amanecer y atardecer
        String horaAmanecer = convertirUnixATiempo(amanecer);
        String horaAtardecer = convertirUnixATiempo(atardecer);

        // Formatear la salida
        return String.format(
                "Clima: %s\n" +
                        "Temperatura actual: %.1f°C\n" +
                        "Temperatura mínima: %.1f°C\n" +
                        "Temperatura máxima: %.1f°C\n" +
                        "Sensación térmica: %.1f°C\n" +
                        "Humedad: %d%%\n" +
                        "Velocidad del viento: %.1f m/s\n" +
                        "Presión atmosférica: %d hPa\n" +
                        "%s\n" +
                        "Amanecer: %s\n" +
                        "Atardecer: %s",
                descripcion,
                temperatura,
                tempMinima,
                tempMaxima,
                sensacionTermica,
                humedad,
                velocidadViento,
                presion,
                probabilidadLluvia,
                horaAmanecer,
                horaAtardecer
        );
    }

    // Método para convertir tiempo UNIX a formato de hora legible
    private String convertirUnixATiempo(long unixTime) {
        java.util.Date fecha = new java.util.Date(unixTime * 1000L);
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(java.util.TimeZone.getDefault());
        return sdf.format(fecha);
    }
}