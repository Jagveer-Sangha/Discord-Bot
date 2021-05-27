package discord.bot.plug;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class WelcomeMessage extends ListenerAdapter {
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent e) {
		Member member = e.getMember();
		// using a lambda fn
		member.getUser().openPrivateChannel().queue(channel -> {
			channel.sendMessage("Welcome to my discord, I hope you enjoy your time here.").queue();
		});
	}

}
