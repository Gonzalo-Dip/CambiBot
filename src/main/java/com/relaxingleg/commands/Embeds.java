package com.relaxingleg.commands;

import com.relaxingleg.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

public class Embeds implements ICommand {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

    }

    @Override
    public String getName() {
        return "embed";
    }

    @Override
    public String getDescription() {
        return "Will send a embed";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Comandos de CambiBot");
        builder.setDescription("Lista de comandos");
        builder.addField("/Embeds","Incrustaciones", false);
        builder.addField("/Play","Música", false);
        builder.addField("/Staff","Roles y permisos", false);
        builder.addField("/Unstaff","Sacar Roles y permisos", false);
        builder.addField("/Sum","Sumar", false);
        builder.addField("/Mute","Silenciar usario", false);
        builder.addField("/Unmute","Dejar de silenciar usuario", false);
        builder.setFooter("Para info consultar a cualquier miembro del Staff");
        builder.setColor(Color.BLUE);
        builder.setAuthor("Staff");
        event.replyEmbeds(builder.build()).queue();

    }
}
