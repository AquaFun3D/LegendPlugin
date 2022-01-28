package com.aquafun3d.legendplugin.listeners;

import com.aquafun3d.legendplugin.utils.BanReasonsConfig;
import com.aquafun3d.legendplugin.utils.BanManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {

	private BanManager man = new BanManager();
	private BanReasonsConfig con = new BanReasonsConfig();

	/**
	 *Check if a player is banned and restricts login after
	 */
	@EventHandler
	public void onLogin(PlayerLoginEvent e){
		Player player = e.getPlayer();
		if(man.isBanned(player.getUniqueId())){
			if(man.getRemainingTime(player.getUniqueId()) > 0){
				int time = Math.round(man.getRemainingTime(player.getUniqueId())*10)/10;
				e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§e" + con.get("BannedPlayer1") + "§6" + time + "§e" +  con.get("BannedPlayer2") + "§c" +  man.getReason(player.getUniqueId()));
			}
			if(man.isPerma(player.getUniqueId())){
				e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§e" + con.get("PermaBan") +  man.getReason(player.getUniqueId()));
			}
		}
	}

	/**
	 * Unbans a player after successfull login
	 */
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		if(man.getRemainingTime(player.getUniqueId()) <= 0 && !man.isPerma(player.getUniqueId())) {
			man.unban(player.getDisplayName());
		}
	}
}
