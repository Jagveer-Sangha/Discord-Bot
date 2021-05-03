package discord.bot.plug;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Invite extends ListenerAdapter {
	
	String url = "https://discord.com/oauth2/authorize?client_id=%s&scope=bot";
	
	@Override
	//Can invite the bot to other servers
	public void onMessageReceived(MessageReceivedEvent e) {
		if (e.getMessage().getContentRaw().equals("!invite")) {
			e.getChannel().sendMessage(String.format(url, e.getJDA().getSelfUser().getId())).queue();;
		}
	}

}
