package com.relaxingleg;

import com.relaxingleg.commands.Embeds;
import com.relaxingleg.commands.Play;
import com.relaxingleg.commands.Sum;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) {

        JDA jda = JDABuilder.createDefault("", GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES).build();
        jda.addEventListener((new Listeners()));
        CommandManager manager = new CommandManager();
        manager.add(new Sum());
        manager.add(new Play());
        manager.add(new Embeds());
        jda.addEventListener(manager);

    }
}
