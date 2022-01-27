package com.aquafun3d.legendplugin.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BanReasonsConfig {

	private static File file;
	private static YamlConfiguration config;

	/**
	 * Creates config file if not existing
	 */
	public BanReasonsConfig(){
		File dir = new File("./plugins/configs");
		if(!dir.exists()){
			dir.mkdirs();
		}

		file = new File(dir,"BanReasonsConfig.yml");
		if(!file.exists()){
			try {
				file.createNewFile();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		config = YamlConfiguration.loadConfiguration(file);
	}

	/**
	 * Checks if a path is valid in config
	 * @param path Path to check
	 * @return true if valid
	 */
	public static boolean contains(String path){
		return config.contains(path);
	}

	/**
	 * Gets the corresponding String to a given path from th config
	 * @param path Path to check in config
	 * @return String form config
	 */
	public static String get(String path){
		if(contains(path)){
			return config.getString(path);
		}
		return "null";
	}
}
