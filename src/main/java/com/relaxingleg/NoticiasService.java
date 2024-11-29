package com.relaxingleg;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

public class NoticiasService {
    private static final String API_URL = "";
    private static final String API_KEY = "";
    private static OkHttpClient client = new OkHttpClient();

    @NotNull
    public static String getNoticiasDeporte(String deporte) {
        try {
            String url = API_URL + deporte + "&apiKey=" + API_KEY;
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();


            return "Últimas noticias de " + deporte + ": " + "X noticias";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al obtener noticias.";
        }
    }
}
