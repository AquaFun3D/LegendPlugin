package com.aquafun3d.legendplugin.commands;

import com.aquafun3d.legendplugin.utils.BanService;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BannCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player){
			Player player = (Player) sender;
			if(player.isOp()){
				if(args.length > 0){
					Player bannedPlayer = Bukkit.getPlayer(args[0]);
					assert bannedPlayer != null;
					BanService.ban(bannedPlayer.getUniqueId(),bannedPlayer.getDisplayName(),1,"Test");
					player.sendMessage("Spieler XY gebannt");
				}

			}else{
				player.sendMessage("Du hast keine Rechte jemanden zu bannen.");
			}
		}else{
			//TODO Command von der console aus nutzbar
		}
		return false;
	}
}
