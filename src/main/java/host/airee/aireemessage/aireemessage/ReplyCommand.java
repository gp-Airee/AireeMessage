package host.airee.aireemessage.aireemessage;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReplyCommand implements CommandExecutor {

	private MessageCommand messageCommand;
	private AireeMessage plugin;

	public ReplyCommand(AireeMessage plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(!(sender instanceof Player)) {
			Bukkit.getLogger().info("Executable only by players");
			return true;
		}

		Player player = (Player) sender;

		if (args.length < 1) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid usage! /reply <message>"));
			return true;
		}
		UUID targetUUID = plugin.reply.get(player.getUniqueId());

		if (targetUUID == null) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou haven't messaged anyone yet."));
			return true;
		}

		Player target = Bukkit.getPlayer(targetUUID);

		if (target == null) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe last player you messaged has now gone offline."));
			return true;
		}

		String message = StringUtils.join(args, " ", 0, args.length);

		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dTo " + target.getName() + ": &f" + message));
		target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dFrom " + player.getName() + ": &f" + message));

		return true;

	}
}
