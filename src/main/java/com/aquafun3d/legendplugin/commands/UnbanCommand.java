package com.aquafun3d.legendplugin.commands;

import com.aquafun3d.legendplugin.utils.BanReasonsConfig;
import com.aquafun3d.legendplugin.utils.BanManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnbanCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.isOp()){
			if(args.length > 0){
				BanManager.unban(args[0]);
				sender.sendMessage("§e" + BanReasonsConfig.get("PlayerUnban1") + "§b" +  args[0] + "§e" +  BanReasonsConfig.get("PlayerUnban2"));
			}else{
				sender.sendMessage("§a" + BanReasonsConfig.get("UnbanCommand"));
			}
		}else {
			sender.sendMessage("§c" + BanReasonsConfig.get("PermissionUnban"));
		}
		return false;
	}
}
