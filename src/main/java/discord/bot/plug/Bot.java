package discord.bot.plug;

import java.util.Scanner;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot {
	// if we got intents to feature we can put them in here
	GatewayIntent[] gatewayIntents = new GatewayIntent[] {};

	ListenerAdapter[] listenerAdapters = new ListenerAdapter[] { new WelcomeMessage(), new PingPong(), new Invite(),
			new RoleReactions(), new Avatar() };
	String token;
	String status;
	JDA jda;

	public Bot(String token, String status) {
		this.token = token;
		this.status = status;
	}

	// Just making the main look cleaner so chucked everything here
	public void build() {

		// Can make a constants class and do Constants.TOKEN
		JDABuilder jdaBuilder = JDABuilder.createDefault(token);
		jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS, gatewayIntents);
		jdaBuilder.addEventListeners(listenerAdapters);
		jdaBuilder.setActivity(Activity.watching(status));

		try {
			jda = jdaBuilder.build();
			jda.awaitReady();
			botInitialized();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}

	// Can get the bot to send messages via console to any channel
	public void botInitialized() {
		// Use lambda expression so we don't have to create a class to show the code
		// thats input
		Thread thread = new Thread(() -> {
			// Id of the general chat
			TextChannel textChannel = jda.getTextChannelById(771891892677640265L);
			while (true) {
				Scanner scanner = new Scanner(System.in);
				String message = scanner.nextLine();
				if (message != null && message != "" && message != "\n") {
					textChannel.sendMessage(message).queue();
				}
				scanner.close();
			}

		});
		thread.start();
	}

}
