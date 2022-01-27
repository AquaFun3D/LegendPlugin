package com.aquafun3d.legendplugin.listeners;

import com.aquafun3d.legendplugin.utils.BanReasonsConfig;
import com.aquafun3d.legendplugin.utils.BanService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {
	@EventHandler
	public void onLogin(PlayerLoginEvent e){
		Player player = e.getPlayer();
		if(BanService.isBanned(player.getUniqueId())){
			if(BanService.getRemainingTime(player.getUniqueId()) > 0){
				e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§e" + BanReasonsConfig.get("BannedPlayer1") + "§6" +  (int) BanService.getRemainingTime(player.getUniqueId()) + "§e" +  BanReasonsConfig.get("BannedPlayer2") + "§c" +  BanService.getReason(player.getUniqueId()));
			}
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		if(BanService.getRemainingTime(player.getUniqueId()) <= 0) {
			BanService.unban(player.getUniqueId());
		}
	}
}
