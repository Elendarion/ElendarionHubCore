package fr.mimich.elendarionhubcore;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Spawn {

    private ElendarionHubCore main;

    private Location spawnLocation;

    public Spawn(ElendarionHubCore main) {
        this.main = main;
        final String base = "spawn.location.";
        this.spawnLocation = new Location(Bukkit.getWorld("world"), this.main.getConfig().getDouble(base + "x"), this.main.getConfig().getDouble(base + "y"), this.main.getConfig().getDouble(base + "z"), (float) this.main.getConfig().getDouble(base + "yaw"), (float) this.main.getConfig().getRoot().getDouble(base + "pitch"));
        Bukkit.getWorld("world").setSpawnLocation((int) spawnLocation.getX(), (int) spawnLocation.getY(), (int) spawnLocation.getX());
    }

    public void teleportSpawn(Player player) {
        player.teleport(this.spawnLocation);
    }
}
