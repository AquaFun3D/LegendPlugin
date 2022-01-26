package com.aquafun3d.legendplugin;

import com.aquafun3d.legendplugin.commands.BannCommand;
import com.aquafun3d.legendplugin.commands.EntbannCommand;
import com.aquafun3d.legendplugin.utils.BanReasonsConfig;
import com.aquafun3d.legendplugin.utils.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		Bukkit.getLogger().fine("Plugin activated");
		MySQL.connect();
		new BanReasonsConfig();
		commandRegistration();
	}

	@Override
	public void onDisable() {
		Bukkit.getLogger().fine("Plugin deactivated");
		MySQL.disconnect();
	}

	private void commandRegistration(){
		getCommand("bann").setExecutor(new BannCommand());
		getCommand("entbann").setExecutor(new EntbannCommand());
	}

}
//TODO Datenbank
