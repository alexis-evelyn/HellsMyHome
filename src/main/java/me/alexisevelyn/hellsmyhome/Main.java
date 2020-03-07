package me.alexisevelyn.hellsmyhome;

//Bukkit Imports
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Event.Result;

// Utility Libraries
import me.alexisevelyn.fourtytwo.debug.*;

/** Main Class
 * @author Alexis Evelyn
 * @author alexisevelyn.me
 * @version 0.0.1-Snapshot
 * @since 0.0.1-Snapshot
*/
public class Main extends JavaPlugin implements Listener {
	/** Instance of 42 Library's Debug Class - Used For Verbosity Based Logging */
	Debug debug = new Debug();
	
	// Event Listeners

	/** Called When Enabling Plugin */
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
		this.getCommand("reloadhell").setExecutor(new Commands());

		// TODO: -----------------------------------------------------------------------------
		// bStats Enabling Code
		int pluginId = 6698;
		Metrics metrics = new Metrics(this, pluginId);

		// Optional: Add custom charts
		// metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));

		getLogger().info(ChatColor.GOLD  + "bStats Enabled: " + metrics.isEnabled());
		// TODO: -----------------------------------------------------------------------------

		// Announce Successful Start
		getLogger().info("Hell's My Home has successfully started!!!");
	}

	/** Called When Disabling Plugin */
	@Override
    public void onDisable() {
		getLogger().info("Thank you for using Hell's My Home!!!");
	}

	/** Detect When Player Uses Bed and Respond Accordingly
	 * 
	 * @param event PlayerBedEnterEvent
	 */
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void onPlayerBed(PlayerBedEnterEvent event) {
		if(debug.isVerboseEnough(Verbosity.superverbose)) {
			getLogger().info(ChatColor.BLUE + "<PlayerBedEnterEvent> Player Bed Used: (" + event.getBed().getX() + ", " + event.getBed().getX() + ", " + event.getBed().getX() + ")");
			getLogger().info(ChatColor.BLUE + "<PlayerBedEnterEvent> Player Bed Result: " + event.getBedEnterResult().toString());
		}
		
		String world = event.getBed().getWorld().getName();
		
		// If World Not Listed In Config, Return To Resume Vanilla (Or Other Plugin) Behavior
		if(Settings.getConfig().get("worlds." + world) == null) {
			return;
		}
		
		boolean enabled = Settings.getConfig().getBoolean("worlds." + world);
		
		if((event.getBedEnterResult() == BedEnterResult.NOT_POSSIBLE_HERE) && enabled) {
			event.setUseBed(Result.ALLOW);
		}
	}
}
