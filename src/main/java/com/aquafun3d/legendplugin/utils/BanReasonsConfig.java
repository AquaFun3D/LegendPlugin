package com.aquafun3d.legendplugin.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BanReasonsConfig {

	private static File file;
	private static YamlConfiguration config;

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

	public static boolean contains(String path){
		return config.contains(path);
	}

	public static String get(String path){
		if(contains(path)){
			return config.getString(path);
		}
		return "null";
	}
}
