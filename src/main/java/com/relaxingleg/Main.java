package com.relaxingleg;

import com.relaxingleg.commands.Embeds;
import com.relaxingleg.commands.Play;
import com.relaxingleg.commands.Sum;
import com.relaxingleg.commands.Staff;
import com.relaxingleg.commands.UnStaff;
import com.relaxingleg.commands.Mute;
import com.relaxingleg.commands.Unmute;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {

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





        try {
            URL url = new URL ("https://dolarapi.com/v1/dolares/blue");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int codigoRespuesta = connection.getResponseCode();

            if(codigoRespuesta == 200){

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder respuesta = new StringBuilder();
                String line; //Leer linea por linea

                while((line = reader.readLine()) != null){
                    respuesta.append(line);
                }

                System.out.println("Respuesta obtenida de la API: "+ respuesta.toString());

                connection.disconnect();

            }else{
                throw new RuntimeException("Error al obtener la info de la API: " + codigoRespuesta);

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

