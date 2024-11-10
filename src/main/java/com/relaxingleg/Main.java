package com.relaxingleg;
import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relaxingleg.commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Main {

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

    public static void main(String[] args) {
        Detect.Language1();
        return;
        /*Scanner scanner = new Scanner(System.in);
        JDA jda = JDABuilder.createDefault("", GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES).build();
        jda.addEventListener((new Listeners()));
        CommandManager manager = new CommandManager();
        manager.add(new Sum());
        manager.add(new Play());
        manager.add(new Embeds());
        manager.add(new Staff());
        manager.add(new UnStaff());
        manager.add(new Mute());
        manager.add(new Unmute());
        jda.addEventListener(manager);

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
                        System.out.println("Opción no válida para el tipo de Dólar.");
                        return;
                }
                break;

            case 2:
                // Cotización del Euro
                endpoint = "v1/cotizaciones/eur";
                break;

            case 3:
                // Cotización del Real
                endpoint = "v1/cotizaciones/brl";
                break;

            default:
                System.out.println("Opción no válida.");
                return;
        }


        try {
            String respuesta = get(endpoint);
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                TipoDolar dolar = objectMapper.readValue(respuesta, TipoDolar.class);

                System.out.println("Name: " + dolar.getNombre());
                System.out.println("Age: " + dolar.getCompra());
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Respuesta para el endpoint seleccionado: " + respuesta);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }*/
    }
}