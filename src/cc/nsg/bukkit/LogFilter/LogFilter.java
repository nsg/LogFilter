package cc.nsg.bukkit.LogFilter;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class LogFilter extends JavaPlugin {

	@Override
	public void onEnable() {
		reloadConfig();
		
		if (getConfig().getBoolean("logfilter")) {
			getServer().getScheduler().runTaskLater(this, new Runnable() {

				@Override
				public void run() {
					loadFilters();
				}
				
			}, 200L);
			
		} else {
			getLogger().warning("Disabled, you need to set logfilter to true in config.yml, or type /reloadlogfilter");
		}
	}
	
	private void reload() {
		reloadConfig();
		loadFilters();
	}
	
	private void loadFilters() {
		
		Set<String> list  = (Set<String>) getConfig().getConfigurationSection("plugins").getKeys(false);
		for (String l : list) {
			if (getServer().getPluginManager().isPluginEnabled(l)) {
				Plugin p = getServer().getPluginManager().getPlugin(l);
				getLogger().info("Load overrides for the plugin " + p.getName());
				@SuppressWarnings("unchecked")
				List<String> fl = (List<String>) getConfig().getList("plugins." + l);
				p.getLogger().info("Filter loaded: " + fl);
				p.getLogger().setFilter(new FilterOverride(fl));
			} else {
				if (l.equalsIgnoreCase("Minecraft")) {
					getLogger().info("Load overrides for minecraft");
					@SuppressWarnings("unchecked")
					List<String> fl = (List<String>) getConfig().getList("plugins." + l);
					Logger.getLogger("Minecraft").setFilter(new FilterOverride(fl));
				} else {
					getLogger().info("Plugin " + l + " not found/loaded.");
				}
			}
		}
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("reloadlogfilter")) {
			sender.sendMessage("LogFilter reloaded");
			reload();
		}
		return true;
	}
	
}
