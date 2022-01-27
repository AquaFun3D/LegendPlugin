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
		if(isBanned(uuid)){
			return;
		}
		int perma = 0;
		if(time == -1){
			perma = 1;
		}
		long duration = time * 60000L + System.currentTimeMillis();
		MySQL.update("INSERT INTO legend.bansystem (Playername, UUID, Reason, Duration, Perma) VALUES ('"+name+"','"+uuid+"','"+reason+"','"+duration+"','"+perma+"')");
		if(Bukkit.getPlayer(name) != null) {
			if (perma == 0) {
				Bukkit.getPlayer(name).kickPlayer("§e" + BanReasonsConfig.get("KickPlayer1") + "§6" + time + "§e" + BanReasonsConfig.get("KickPlayer2") + "§c" + reason);
			}else {
				Bukkit.getPlayer(name).kickPlayer("§e" + BanReasonsConfig.get("PermaKick") + "§c" + reason);
			}
		}
	}

	public static void unban(UUID uuid){
		MySQL.update("DELETE FROM legend.bansystem WHERE UUID='"+uuid+"'");
	}

	public static boolean isPerma(UUID uuid){
		if(isBanned(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM legend.bansystem WHERE UUID='"+uuid+"'");
			try {
				while (rs.next()) {
					return (rs.getInt("Perma") == 1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static String getReason(UUID uuid){
		if(isBanned(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM legend.bansystem WHERE UUID='"+uuid+"'");
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
			ResultSet rs = MySQL.getResult("SELECT * FROM legend.bansystem WHERE UUID='"+uuid+"'");
			try {
				while (rs.next()) {
					float time = rs.getLong("Duration") - System.currentTimeMillis();
					time /= 60000;
					return time;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
