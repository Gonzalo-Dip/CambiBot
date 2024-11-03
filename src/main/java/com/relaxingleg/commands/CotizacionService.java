package com.relaxingleg.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CotizacionService {

    private static final String BASE_URL = "https://dolarapi.com/";

    // Método para hacer una solicitud GET a un endpoint específico
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

    public static String seleccionarCotizacion(Scanner scanner) {
        System.out.println("Seleccione la moneda que desea consultar:");
        System.out.println("1. Dólar");
        System.out.println("2. Euro");
        System.out.println("3. Real");

        int moneda = scanner.nextInt();
        String endpoint = "";

        switch (moneda) {
            case 1:
                System.out.println("Seleccione el tipo de cotización del Dólar:");
                System.out.println("1. Dólar Blue");
                System.out.println("2. Dólar Oficial");
                System.out.println("3. Dólar Bolsa");
                int tipoDolar = scanner.nextInt();

                switch (tipoDolar) {
                    case 1:
                        endpoint = "v1/dolares/blue";
                        break;
                    case 2:
                        endpoint = "v1/dolares/oficial";
                        break;
                    case 3:
                        endpoint = "v1/dolares/bolsa";
                        break;
                    default:
                        return "Opción no válida para el tipo de Dólar.";
                }
                break;

            case 2:
                endpoint = "v1/cotizaciones/eur"; // Cotización del Euro
                break;

            case 3:
                endpoint = "v1/cotizaciones/brl"; // Cotización del Real
                break;

            default:
                return "Opción no válida.";
        }

        // Realiza la solicitud y devuelve la respuesta
        return get(endpoint);
    }
}
