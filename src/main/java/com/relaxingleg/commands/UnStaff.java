package com.relaxingleg.commands;


import com.relaxingleg.ICommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UnStaff implements ICommand {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

    }

    @Override
    public String getName() {
        return "unstaff";
    }

    @Override
    public String getDescription() {
        return "Will remove staff";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        Guild guild = event.getGuild();
        assert guild != null;
        Role Role = guild.getRoleById(1282507250539171840L);
        assert member != null;
        net.dv8tion.jda.api.entities.Role role = null;
        assert false;
        guild.removeRoleFromMember(member, role).queue();
        event.reply("Role Removed").queue();
    }
}