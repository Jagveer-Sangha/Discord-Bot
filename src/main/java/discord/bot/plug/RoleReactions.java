package discord.bot.plug;

import java.util.HashMap;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RoleReactions extends ListenerAdapter {

	final long channelID = 772203854980448307L;
	final long roleID = 772204797826826280L;
	// To give specific roles based on emoji
	// HashMap<String, Long> reactionToRoleID = new HashMap<>();

	/*
	 * public RoleReactions() { reactionToRoleID.put(, );//put specifc emoji }
	 */

	@Override
	public void onMessageReactionAdd(MessageReactionAddEvent e) {
		// If its null its working
		// System.out.println(e.getMember().getNickname());
		if (e.getTextChannel().getIdLong() == channelID) {
			/*
			 * Long roleID = reactionToRoleID.get(e.getReactionEmote().getIdLong());
			 * if(roleID == null) { return; }
			 */

			// for role reactions to add specific emoji w hashmao
			// System.out.println(e.getReactionEmote().getIdLong());
			e.getGuild().addRoleToMember(e.getUserId(), e.getJDA().getRoleById(roleID)).queue();
		}
	}

	@Override
	// Removes role when individual unreacts
	public void onMessageReactionRemove(MessageReactionRemoveEvent e) {
		if (e.getTextChannel().getIdLong() == channelID) {

			/*
			 * Long roleID = reactionToRoleID.get(e.getReactionEmote().getIdLong());
			 * if(roleID == null) { return; }
			 */

			e.getGuild().removeRoleFromMember(e.getUserId(), e.getJDA().getRoleById(roleID)).queue();
		}
	}
}
