package com.relaxingleg.commands;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class BuscadorImagen {

    private final String accessKey;

    // Constructor para inicializar la API Key
    public BuscadorImagen(String accessKey) {
        this.accessKey = accessKey;
    }

    // Método para buscar una imagen por palabra clave
    public String buscarImagen(String palabraClave) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Construir la URL para la petición
        String url = "https://api.unsplash.com/photos/random?query=" + palabraClave + "&client_id=" + accessKey;

        // Crear la petición
        Request request = new Request.Builder()
                .url(url)
                .build();

        // Ejecutar la petición
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                // Obtener la URL de la imagen del JSON de respuesta
                String responseBody = response.body().string();
                return extractImageUrlFromJson(responseBody);
            } else {
                System.out.println("No se pudo realizar la búsqueda.");
                return null;
            }
        }
    }

    // Método privado para extraer la URL de la imagen del JSON
    private String extractImageUrlFromJson(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        return jsonObject.getAsJsonObject("urls").get("regular").getAsString();
    }

    // Método para mostrar la imagen descargada en un JFrame
    public void mostrarImagen(String imageUrl) {
        try {
            // Descargar la imagen desde la URL
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);

            // Crear un JFrame para mostrar la imagen
            JFrame frame = new JFrame("Imagen encontrada");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            // Crear un JLabel con la imagen descargada
            JLabel label = new JLabel(new ImageIcon(image));
            frame.getContentPane().add(label, BorderLayout.CENTER);

            // Hacer visible el JFrame
            frame.pack();
            frame.setLocationRelativeTo(null); // Centrar en la pantalla
            frame.setVisible(true);

        } catch (IOException e) {
            System.out.println("Error al mostrar la imagen: " + e.getMessage());
        }
    }
}

