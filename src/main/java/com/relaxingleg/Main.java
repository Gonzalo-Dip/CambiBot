package com.relaxingleg;

import com.relaxingleg.commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JDA jda = JDABuilder.createDefault("MTI4MjUwNzI1MDUzOTE3MTg0MA.G_ny7B.i8GF4EAFwlbp5iIw3jYudQ7lIlQeFwzxCDg2As", GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES).build();
        jda.addEventListener((new Listeners()));
        CommandManager manager = new CommandManager();
        manager.add(new Sum());
        manager.add(new Play());
        manager.add(new Embeds());
        manager.add(new Staff());
        manager.add(new UnStaff());
        manager.add(new Mute());
        manager.add(new Unmute());
        manager.add(new Detect());
        jda.addEventListener(manager);

        jda.addEventListener(new Listeners());
        jda.addEventListener(new CotizacionCommand());
    }
}