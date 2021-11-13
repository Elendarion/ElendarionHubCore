package fr.mimich.elendarionhubcore;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Spawn {

    private ElendarionHubCore main;

    private Location spawnLocation;

    public Spawn(ElendarionHubCore main) {
        this.main = main;
        final String BASE = "spawn.location.";
        this.spawnLocation = new Location(Bukkit.getWorld("world"), this.main.getConfig().getDouble(BASE + "x"), this.main.getConfig().getDouble(BASE + "y"), this.main.getConfig().getDouble(BASE + "z"), (float) this.main.getConfig().getDouble(BASE + "yaw"), (float) this.main.getConfig().getRoot().getDouble(BASE + "pitch"));
        Bukkit.getWorld("world").setSpawnLocation((int) spawnLocation.getX(), (int) spawnLocation.getY(), (int) spawnLocation.getX());
    }

    public void teleportSpawn(Player player) {
        player.teleport(this.spawnLocation);
    }
}
