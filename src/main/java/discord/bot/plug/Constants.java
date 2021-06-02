package discord.bot.plug;

//imports
import io.github.cdimascio.dotenv.Dotenv;

public class Constants {

	/**
	 * Instantiates a new Constants. Private to prevent instantiation
	 */
	private Constants() {
		// not called
	}

	// Gets access and loads a .env file
	static Dotenv dotenv = Dotenv.configure().filename(".env").load();
	// Retrieves API key from .env file
	static String TOKEN = dotenv.get("DISCORD_BOT_TOKEN");
	static String BOT_STATUS = "Chillin";

}