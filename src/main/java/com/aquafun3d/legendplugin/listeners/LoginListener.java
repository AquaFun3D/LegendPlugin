package com.aquafun3d.legendplugin.listeners;

import com.aquafun3d.legendplugin.utils.BanReasonsConfig;
import com.aquafun3d.legendplugin.utils.BanManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {

	/**
	 *Check if a player is banned and restricts login after
	 */
	@EventHandler
	public void onLogin(PlayerLoginEvent e){
		Player player = e.getPlayer();
		if(BanManager.isBanned(player.getUniqueId())){
			if(BanManager.getRemainingTime(player.getUniqueId()) > 0){
				int time = Math.round(BanManager.getRemainingTime(player.getUniqueId())*10)/10;
				e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§e" + BanReasonsConfig.get("BannedPlayer1") + "§6" + time + "§e" +  BanReasonsConfig.get("BannedPlayer2") + "§c" +  BanManager.getReason(player.getUniqueId()));
			}
			if(BanManager.isPerma(player.getUniqueId())){
				e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§e" + BanReasonsConfig.get("PermaBan") +  BanManager.getReason(player.getUniqueId()));
			}
		}
	}

	/**
	 * Unbans a player after successfull login
	 */
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		if(BanManager.getRemainingTime(player.getUniqueId()) <= 0 && !BanManager.isPerma(player.getUniqueId())) {
			BanManager.unban(player.getDisplayName());
		}
	}
}
