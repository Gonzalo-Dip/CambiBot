package com.relaxingleg;

import com.relaxingleg.commands.Play;
import com.relaxingleg.commands.Sum;
import com.relaxingleg.commands.Staff;
import com.relaxingleg.commands.UnStaff;
import com.relaxingleg.commands.Mute;
import com.relaxingleg.commands.Unmute;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {

    public static void main(String[] args) {

        JDA jda = JDABuilder.createDefault("MTI4MjUwNzI1MDUzOTE3MTg0MA.G11ueD.quowSDbTy_II8uHHiNysT4IXeDuscMYIotR2ZI", GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES).build();
        jda.addEventListener((new Listeners()));
        CommandManager manager = new CommandManager();
        manager.add(new Sum());
        manager.add(new Play());
        manager.add(new Staff());
        manager.add(new UnStaff());
        manager.add(new Mute());
        manager.add(new Unmute());

        jda.addEventListener(manager);

    }
}
