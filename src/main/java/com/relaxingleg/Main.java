package com.relaxingleg;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) {

        JDA jda = JDABuilder.createDefault("").build();
        jda.addEventListener((new Listeners()));

    }
}
