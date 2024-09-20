package com.relaxingleg.commands;

import com.relaxingleg.ICommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Unmute implements ICommand {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

    }

    @Override
    public String getName() {
        return "unmute";
    }

    @Override
    public String getDescription() {
        return "Will unmute a user";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.USER, "muted", "The user to unmute", true));
        return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        Guild guild = event.getGuild();
        assert guild != null;
        Role role = guild.getRoleById(1282507250539171840L);
        assert member != null;
        if(member.getRoles().contains(role)) {
            Member mutedMember = Objects.requireNonNull(event.getOption("muted")).getAsMember();
            Role muteRole = guild.getRoleById(1282507250539171840L);
            Role defaultRole = guild.getRoleById(1282507250539171840L);
            assert mutedMember != null;
            assert muteRole != null;
            guild.removeRoleFromMember(mutedMember, muteRole).queue();
            assert defaultRole != null;
            guild.addRoleToMember(mutedMember, defaultRole).queue();
            event.reply("Unmuted member").queue();
        } else {
            event.reply("You do not have permission to execute this command").queue();
        }
    }
}