package com.aquafun3d.legendplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EntbannCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player){
			Player player = (Player) sender;
			if(player.isOp()){

			}else{
				player.sendMessage("Du hast keine Rechte jemanden zu entbannen.");
			}
		}
		return false;
	}
}
