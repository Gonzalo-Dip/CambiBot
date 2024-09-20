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

public class Mute implements ICommand {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

    }

    @Override
    public String getName() {
        return "mute";
    }

    @Override
    public String getDescription() {
        return "Will mute a member";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.USER, "muted", "The user to mute", true));
        return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        Guild guild = event.getGuild();
        assert guild != null;


        long roleId = 1282507250539171840L;

        Role role = guild.getRoleById(roleId);
        assert member != null;

        if (member.getRoles().contains(role)) {
            Member mutedMember = Objects.requireNonNull(event.getOption("muted")).getAsMember();


            Role muteRole = guild.getRoleById(roleId);
            Role defaultRole = guild.getRoleById(roleId);

            assert mutedMember != null;
            assert defaultRole != null;


            guild.removeRoleFromMember(mutedMember, defaultRole).queue();
            assert muteRole != null;
            guild.addRoleToMember(mutedMember, muteRole).queue();

            event.reply("Muted member").queue();
        } else {
            event.reply("You do not have permission to execute this command").queue();
        }
    }
}
