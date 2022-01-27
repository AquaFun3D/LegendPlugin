package com.aquafun3d.legendplugin.utils;

import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BanService {

	public static boolean isBanned(UUID uuid){
		ResultSet rs = MySQL.getResult("SELECT * FROM legend.bansystem WHERE UUID='"+uuid+"'");
		try{
			while (rs.next()){
				return rs != null;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	public static void ban(UUID uuid, String name, int time, String reason){
		long duration;
		if(time != -1) {
			duration = time * 60000L + System.currentTimeMillis();
		}else {
			duration = time;
		}
		if(isBanned(uuid)){
			return;
		}
		MySQL.update("INSERT INTO legend.bansystem (Playername, UUID, Reason, Duration) VALUES ('"+name+"','"+uuid+"','"+reason+"','"+duration+"')");
		if(Bukkit.getPlayer(name) != null) {
			if (time != -1) {
				Bukkit.getPlayer(name).kickPlayer("§e" + BanReasonsConfig.get("KickPlayer1") + "§6" + time + "§e" + BanReasonsConfig.get("KickPlayer2") + "§c" + reason);
			}else {
				Bukkit.getPlayer(name).kickPlayer("§e" + BanReasonsConfig.get("PermaKick") + "§c" + reason);

			}
		}
	}

	public static void unban(UUID uuid){
		MySQL.update("DELETE FROM legend.bansystem WHERE UUID='"+uuid+"'");
	}

	public static String getReason(UUID uuid){
		if(isBanned(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM legend.bansystem WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getString("Reason");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static float getRemainingTime(UUID uuid){
		if(isBanned(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM legend.bansystem WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					float time = rs.getFloat("Duration");
					if(time == -1.0){
						return -1;
					}
					time -=  - System.currentTimeMillis();
					time /= 60000;
					return (float) (Math.round(time * 1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
