package com.Cambibot;

import com.Cambibot.commands.Embeds;
import com.Cambibot.commands.Play;
import com.Cambibot.commands.Sum;
import com.Cambibot.commands.Staff;
import com.Cambibot.commands.UnStaff;
import com.Cambibot.commands.Mute;
import com.Cambibot.commands.Unmute;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

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

    }
}
