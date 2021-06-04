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

	// Creates the discord bot object
	public void build() {

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

	/**
	 * User has the ability to send personalized messages via the bot
	 */
	public void botInitialized() {
		// Utilized lambda expressions to create block of code
		Thread thread = new Thread(() -> {
			// Bot gets access to the general chat
			TextChannel textChannel = jda.getTextChannelById(771891892677640265L);
			boolean chatting = true;

			// Allows user to input messages that the bot outputs as text in the channel
			while (chatting) {
				Scanner scanner = new Scanner(System.in);
				String message = scanner.nextLine();
				// Ends program
				if (message.equals("exit")) {
					System.exit(0);
				}
				if (message != null && message != "" && message != "\n") {
					textChannel.sendMessage(message).queue();
				} else {
					chatting = false;
					scanner.close();
				}

			}
		});
		thread.start();
	}

}
