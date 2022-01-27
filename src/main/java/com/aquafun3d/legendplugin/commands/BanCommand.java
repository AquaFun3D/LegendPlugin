package com.aquafun3d.legendplugin.commands;

import com.aquafun3d.legendplugin.utils.BanReasonsConfig;
import com.aquafun3d.legendplugin.utils.BanService;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.isOp()){
			if(args.length >= 3){
				Player player = Bukkit.getPlayer(args[0]);
				if(player == null){
					sender.sendMessage("§e" + BanReasonsConfig.get("PlayerOffline"));
					return false;
				}
				int time = Integer.parseInt(args[1]);
				StringBuilder reason = new StringBuilder();
				for (int i = 2; i < args.length; i++){
					reason.append(args[i] + " ");
				}
				BanService.ban(player.getUniqueId(), player.getDisplayName(), time, reason.toString());
				sender.sendMessage("§e" + BanReasonsConfig.get("PlayerBan1") + "§b" + args[0] + "§e" +  BanReasonsConfig.get("PlayerBan2"));
			}else{
				sender.sendMessage("§a" + BanReasonsConfig.get("BanCommand"));
			}
		}else{
			sender.sendMessage("§c" + BanReasonsConfig.get("PermissionBan"));
		}
		return false;
	}
}
