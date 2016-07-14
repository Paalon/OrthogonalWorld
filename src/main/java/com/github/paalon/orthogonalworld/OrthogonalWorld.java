/*

The MIT License (MIT)

Copyright (c) 2016 Paalon

*/

package com.github.paalon.orthogonalworld;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class OrthogonalWorld extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public void onDisable() {
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Location location = event.getTo();
		double width = 0.1;
		double x = location.getX();
		double z = location.getZ();
		double dx = x - (Math.round(x + 0.5) - 0.5);
		double dz = z - (Math.round(z + 0.5) - 0.5);
		double adx = Math.abs(dx);
		double adz = Math.abs(dz);
		
		boolean xorz = adx < adz;
		
		if (adx > width && adz > width) {
			if (xorz) {
      			if (dx > 0) {
      				location.add(width - dx, 0, 0);
	        	} else {
	        		location.add(-width - dx, 0, 0);
	        	}
	        } else {
	        	if (dz > 0) {
	        		location.add(0, 0, width - dz);
	        	} else {
	        		location.add(0, 0, -width - dz);
	        	}
	        }
			player.teleport(location);
		}
	}
}
