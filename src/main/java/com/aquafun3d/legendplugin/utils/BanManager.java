package com.aquafun3d.legendplugin.utils;

import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Handles the bansystem
 */
public class BanManager {

	private MySQL sql;
	private BanReasonsConfig con;

	public BanManager() {
		sql = new MySQL();
		sql.connect();
		con = new BanReasonsConfig();
	}

	/**
	 * Checks if a player is already banned (is in database)
	 * @param uuid UUID of the player
	 * @return true if banned
	 */
	public boolean isBanned(UUID uuid){
		ResultSet rs = sql.getResult("SELECT * FROM legend.bansystem WHERE UUID='"+uuid+"'");
		try{
			while (rs.next()){
				return rs != null;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Handles the ban-process in database
	 * @param uuid players uuid
	 * @param name name of the player
	 * @param time time for how long the player is banned in minutes
	 * @param reason reason why the player got banned
	 */
	public void ban(UUID uuid, String name, int time, String reason){
		if(isBanned(uuid)){
			return;
		}
		int perma = 0;
		if(time == -1){
			perma = 1;
		}
		long duration = time * 60000L + System.currentTimeMillis();
		sql.update("INSERT INTO legend.bansystem (Playername, UUID, Reason, Duration, Perma) VALUES ('"+name+"','"+uuid+"','"+reason+"','"+duration+"','"+perma+"')");
		if(Bukkit.getPlayer(name) != null) {
			if (perma == 0) {
				Bukkit.getPlayer(name).kickPlayer("§e" + con.get("KickPlayer1") + "§6" + time + "§e" + con.get("KickPlayer2") + "§c" + reason);
			}else {
				Bukkit.getPlayer(name).kickPlayer("§e" + con.get("PermaKick") + "§c" + reason);
			}
		}
	}

	/**
	 * Removes a banned player from database
	 * @param name name of the player to unban
	 */
	public void unban(String name){
		sql.update("DELETE FROM legend.bansystem WHERE Playername='"+name+"'");
	}

	/**
	 * Check wether a player is permabanned or not
	 * @param uuid players uuid
	 * @return true if player is permabanned
	 */
	public boolean isPerma(UUID uuid){
		if(isBanned(uuid)) {
			ResultSet rs = sql.getResult("SELECT * FROM legend.bansystem WHERE UUID='"+uuid+"'");
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

	/**
	 * Gets the reason why a player is banned from database
	 * @param uuid players uuid
	 * @return Reason why the player was banned
	 */
	public String getReason(UUID uuid){
		if(isBanned(uuid)) {
			ResultSet rs = sql.getResult("SELECT * FROM legend.bansystem WHERE UUID='"+uuid+"'");
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

	/**
	 * Gives the remaining time of a banned player
	 * @param uuid players uuid
	 * @return time in minutes
	 */
	public float getRemainingTime(UUID uuid){
		if(isBanned(uuid)) {
			ResultSet rs = sql.getResult("SELECT * FROM legend.bansystem WHERE UUID='"+uuid+"'");
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
