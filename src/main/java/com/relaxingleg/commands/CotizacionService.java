package com.relaxingleg.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CotizacionService {

    private static final String BASE_URL = "https://dolarapi.com/";


    public static String get(String endpoint) {
        try {
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int codigoRespuesta = connection.getResponseCode();

            if (codigoRespuesta == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder respuesta = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    respuesta.append(line);
                }

                connection.disconnect();
                return respuesta.toString();
            } else {
                throw new RuntimeException("Error al obtener la info de la API: " + codigoRespuesta);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error en la solicitud: " + e.getMessage(), e);
        }
    }



}
