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
		long duration = time * 3600000L + System.currentTimeMillis();
		if(isBanned(uuid)){
			return;
		}
		MySQL.update("INSERT INTO legend.bansystem (Playername, UUID, Reason, Duration) VALUES ('"+name+"','"+uuid+"','"+reason+"','"+duration+"')");
		if(Bukkit.getPlayer(name) != null){
			Bukkit.getPlayer(name).kickPlayer("lorem ipsum");
			//TODO Text aus config loaden
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

	public static long getRemainingTime(UUID uuid){
		if(isBanned(uuid)) {
			ResultSet rs = MySQL.getResult("SELECT * FROM legend.bansystem WHERE UUID='" + uuid + "'");
			try {
				while (rs.next()) {
					return rs.getLong("Duration") - System.currentTimeMillis();
					//TODO Umrechnen
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}