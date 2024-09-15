package com.relaxingleg;

import com.relaxingleg.commands.Sum;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) {

        JDA jda = JDABuilder.createDefault("", GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT).build();
        jda.addEventListener((new Listeners()));
        jda.addEventListener(new Sum()) ;

    }
}
