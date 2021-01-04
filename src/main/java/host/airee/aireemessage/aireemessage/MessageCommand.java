package host.airee.aireemessage.aireemessage;


import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MessageCommand implements CommandExecutor {

	private MessageCommand messageCommand;
	private AireeMessage plugin;

	public MessageCommand(AireeMessage plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(!(sender instanceof Player)) {
			Bukkit.getLogger().info("Executable only by players");
			return true;
		}

		Player player = (Player) sender;

		if (args.length < 2) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid usage! /message <player> <message>"));
			return true;
		}

		Player target = Bukkit.getPlayer(args[0]);

		if (target == null) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player is not online!"));
			return true;
		}
		String message = StringUtils.join(args, " ", 1, args.length);

		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dTo " + target.getName() + ": &f" + message));
		target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&dFrom " + player.getName() + ": &f" + message));

		plugin.reply.put(player.getUniqueId(), target.getUniqueId());
		plugin.reply.put(target.getUniqueId(), player.getUniqueId());

		return true;
	}
}
