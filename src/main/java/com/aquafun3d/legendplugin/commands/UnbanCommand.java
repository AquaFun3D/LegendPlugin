package com.aquafun3d.legendplugin.commands;

import com.aquafun3d.legendplugin.utils.BanReasonsConfig;
import com.aquafun3d.legendplugin.utils.BanManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnbanCommand implements CommandExecutor {

	private BanManager man = new BanManager();
	private BanReasonsConfig con = new BanReasonsConfig();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.isOp()){
			if(args.length > 0){
				man.unban(args[0]);
				sender.sendMessage("§e" + con.get("PlayerUnban1") + "§b" +  args[0] + "§e" +  con.get("PlayerUnban2"));
			}else{
				sender.sendMessage("§a" + con.get("UnbanCommand"));
			}
		}else {
			sender.sendMessage("§c" + con.get("PermissionUnban"));
		}
		return false;
	}
}
