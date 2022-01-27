package com.aquafun3d.legendplugin.listeners;

import com.aquafun3d.legendplugin.utils.BanService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {
	@EventHandler
	public void onLogin(PlayerLoginEvent e){
		Player player = e.getPlayer();
		if(BanService.isBanned(player.getUniqueId())){
			//e.disallow(null,"NÖ");
		}
	}
}
