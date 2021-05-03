package discord.bot.plug;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PingPong extends ListenerAdapter{

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		if(e.getMessage().getContentRaw().equals("!ping")) {
			//Instead of having the pong string being sent we have the embed
			e.getChannel().sendMessage(createEmbed()).queue();
		}
	}
	
	public MessageEmbed createEmbed() {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		embedBuilder.setTitle("!ping");
		embedBuilder.addField("Isn't Pong an epic word?","pong!",true);
		embedBuilder.setThumbnail("https://lh3.googleusercontent.com/proxy/w5K8UBolp4KNMkVKIrtmZPZRDLMF50JKbwGQ1eCCaDxvtQhk162tda_BN8xHEprMbrG9yppwG1Yy1uf1z-rqsMfv-97V8HkpVPPctGkNxiYis0w8xGRV_xRUNK278pODzAWu");
		embedBuilder.setFooter("Tbh this is just me testing the footer feature");
		embedBuilder.setColor(Color.blue);
		embedBuilder.setAuthor("Jag");
		return embedBuilder.build();
	}
	
}
