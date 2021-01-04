package host.airee.aireemessage.aireemessage;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class AireeMessage extends JavaPlugin {

	public Map<UUID, UUID> reply = new HashMap<UUID, UUID>();

	@Override
	public void onEnable() {
		getCommand("message").setExecutor(new MessageCommand(this));
		getCommand("reply").setExecutor(new ReplyCommand(this));
	}

	@Override
	public void onDisable() {

	}
}
