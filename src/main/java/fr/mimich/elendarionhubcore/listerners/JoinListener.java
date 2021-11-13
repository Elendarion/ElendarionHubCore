package fr.mimich.elendarionhubcore.listerners;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private ElendarionHubCore main;

    public JoinListener(ElendarionHubCore main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.main.getJoinItem().setJointItem(player);
        this.main.getSpawn().teleportSpawn(player);
    }
}
