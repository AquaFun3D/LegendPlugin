package com.aquafun3d.legendplugin;

import com.aquafun3d.legendplugin.commands.BanCommand;
import com.aquafun3d.legendplugin.commands.UnbanCommand;
import com.aquafun3d.legendplugin.listeners.LoginListener;
import com.aquafun3d.legendplugin.utils.BanReasonsConfig;
import com.aquafun3d.legendplugin.utils.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

	private static Main plugin;

	@Override
	public void onEnable() {
		Bukkit.getLogger().fine("Plugin activated");
		plugin = this;
		MySQL.connect();
		new BanReasonsConfig();
		commandRegistration();
		listenerRegistration();
	}

	@Override
	public void onDisable() {
		Bukkit.getLogger().fine("Plugin deactivated");
		MySQL.disconnect();
	}

	private void listenerRegistration(){
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new LoginListener(),this);
		pluginManager.registerEvents(new LoginListener(),this);
	}

	private void commandRegistration(){
		getCommand("ban").setExecutor(new BanCommand());
		getCommand("unban").setExecutor(new UnbanCommand());
	}

}
