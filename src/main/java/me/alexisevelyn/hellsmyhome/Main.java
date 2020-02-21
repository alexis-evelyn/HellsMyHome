package me.alexisevelyn.hellsmyhome;

//Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Event.Result;

// Utility Libraries
import me.alexisevelyn.fourtytwo.debug.*;

public class Main extends JavaPlugin implements Listener {
	Debug debug = new Debug();
	
	// Event Listeners

	@Override
    public void onEnable() {
		// TODO: Hook Into GriefPrevention and Worldguard
		// ...
		Settings.getConfig().addDefault("worldguard_supported", false);
		Settings.getConfig().addDefault("griefprevention_supported", false);
		
		Settings.getConfig().addDefault("debug.verbosity", debug.getVerbosity().getValue());
		
		Settings.getConfig().options().copyDefaults(true);
        saveConfig();
        
        // Get Current Verbosity Values
        debug.setVerbosity(Settings.getConfig().getInt("debug.verbosity"));
		
		// Register Bukkit Listeners (For Event Handlers)
		Bukkit.getPluginManager().registerEvents(this, this);
		
		// Register Commands
		this.getCommand("reload").setExecutor(new Commands());

		// Announce Successful Start
		getLogger().info("Hell is My Home has successfully started!!!");
	}

	@Override
    public void onDisable() {
		getLogger().info("Thank you for using Hell is My Home!!!");
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void onPlayerBed(PlayerBedEnterEvent event) {
		if(debug.isVerboseEnough(Verbosity.superverbose)) {
			getLogger().info(ChatColor.BLUE + "<PlayerBedEnterEvent> Player Bed Used: (" + event.getBed().getX() + ", " + event.getBed().getX() + ", " + event.getBed().getX() + ")");
			getLogger().info(ChatColor.BLUE + "<PlayerBedEnterEvent> Player Bed Result: " + event.getBedEnterResult().toString());
		}
		
		event.setUseBed(Result.ALLOW);
	}
}
