package com.aquafun3d.legendplugin.commands;

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
				int time = Integer.getInteger(args[1]);
				StringBuilder reason = new StringBuilder();
				for (int i = 2; i < args.length; i++){
					reason.append(args[i]);
				}
				BanService.ban(player.getUniqueId(), player.getDisplayName(), time, reason.toString());
				sender.sendMessage("Spieler XY gebannt"); //TODO
			}else{
				sender.sendMessage("Befehl use"); //TODO
			}
		}else{
			sender.sendMessage("Du hast keine Rechte jemanden zu bannen."); //TODO
		}
		return false;
	}
}
