package discord.bot.plug;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;


public class Avatar extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		if(e.getMessage().getContentRaw().startsWith("!avatar")) {
			List<Member> mentionedMembers = e.getMessage().getMentionedMembers();
			
			if(mentionedMembers.isEmpty()) {
				e.getChannel().sendMessage(createEmbed(e.getAuthor())).queue();
				
			}else {
				
				e.getChannel().sendMessage(createEmbed(mentionedMembers.get(0).getUser())).queue();
			}
		}
	}

	public MessageEmbed createEmbed(User user) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		embedBuilder.setImage(user.getAvatarUrl());
		return embedBuilder.build();
	}
}
