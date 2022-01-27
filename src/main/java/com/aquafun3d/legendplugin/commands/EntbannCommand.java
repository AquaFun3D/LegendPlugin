package com.aquafun3d.legendplugin.commands;

import com.aquafun3d.legendplugin.utils.BanService;
import org.bukkit.Bukkit;
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
				Player bannedPlayer = Bukkit.getPlayer(args[0]);
				assert bannedPlayer != null;
				BanService.unban(bannedPlayer.getUniqueId());
				player.sendMessage("Spieler XY entbannt");
			}else{
				player.sendMessage("Du hast keine Rechte jemanden zu entbannen.");
			}
		}else{
			//TODO Command von der console aus nutzbar
		}
		return false;
	}
}
