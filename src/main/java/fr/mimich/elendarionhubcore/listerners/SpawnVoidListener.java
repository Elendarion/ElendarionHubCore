package fr.mimich.elendarionhubcore.listerners;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SpawnVoidListener implements Listener {

    private ElendarionHubCore main;

    public SpawnVoidListener(ElendarionHubCore main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerChat(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getY() < 0D) {
            this.main.getSpawn().teleportSpawn(player);
        }
    }
}
