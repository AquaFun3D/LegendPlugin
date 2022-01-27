package com.aquafun3d.legendplugin.commands;

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
				BanService.unban(player.getUniqueId());
				sender.sendMessage("Spieler XY entbannt"); //TODO
			}else{
				sender.sendMessage("command use"); //TODO
			}
		}else {
			sender.sendMessage("Du hast keine Rechte jemanden zu entbannen."); //TODO
		}
		return false;
	}
}
