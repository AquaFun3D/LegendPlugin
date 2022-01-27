package com.aquafun3d.legendplugin.commands;

import com.aquafun3d.legendplugin.utils.BanReasonsConfig;
import com.aquafun3d.legendplugin.utils.BanService;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnbanCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.isOp()){
			if(args.length > 0){
				Player player = Bukkit.getPlayer(args[0]);
				if(player == null){
					sender.sendMessage(BanReasonsConfig.get("PlayerOffline"));
					return false;
				}
				BanService.unban(player.getUniqueId());
				sender.sendMessage(BanReasonsConfig.get("PlayerUnban1") + args[0] + BanReasonsConfig.get("PlayerUnban2"));
			}else{
				sender.sendMessage(BanReasonsConfig.get("UnbanCommand"));
			}
		}else {
			sender.sendMessage(BanReasonsConfig.get("PermissionUnban"));
		}
		return false;
	}
}
