package me.alexisevelyn.hellsmyhome;

// Bukkit Imports
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

// https://papermc.io/javadocs/paper/1.15/org/bukkit/Chunk.html
// https://papermc.io/javadocs/paper/1.15/org/bukkit/ChunkSnapshot.html

public class Commands implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("reload")) {	
			Settings.getPlugin().reloadConfig();
			
			sender.sendMessage(ChatColor.GOLD + "Finished Reloading Config!!!");
			sender.sendMessage(ChatColor.RED + "Dev Note - Reload Currently Doesn't Work!!! Restart Your Server!!!");
			
			return true;
		}
		
		return false;
	}
}
