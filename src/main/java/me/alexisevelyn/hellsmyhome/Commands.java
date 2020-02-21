package me.alexisevelyn.hellsmyhome;

// Bukkit Imports
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

// https://papermc.io/javadocs/paper/1.15/org/bukkit/Chunk.html
// https://papermc.io/javadocs/paper/1.15/org/bukkit/ChunkSnapshot.html

/** Commands From Plugin
 * @author Alexis Evelyn
 * @author alexisevelyn.me
 * @version 0.0.1-Snapshot
 * @since 0.0.1-Snapshot
*/
public class Commands implements CommandExecutor {
	
	/** Checks For Specific Command and Executes It
	 * @param sender The sender's object (CommandSender)
	 * @param command The command to run
	 * @param label Alias of command run
	 * @param args Array of arguments for the command
	*/
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
