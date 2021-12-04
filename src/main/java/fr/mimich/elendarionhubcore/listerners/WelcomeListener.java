package fr.mimich.elendarionhubcore.listerners;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import fr.mimich.elendarionhubcore.utils.SuperConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Collections;

public class WelcomeListener implements Listener {

    private ElendarionHubCore main;

    public WelcomeListener(ElendarionHubCore main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        String message;
        Player player = event.getPlayer();
        if (player.isOp()) {
            message = this.main.getSuperConfig().getConfigText(SuperConfig.TextType.MESSAGE, "op-join-server", Collections.singletonMap("%player%", player.getName()));
        } else {
            message = null;
        }
        event.setJoinMessage(message);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }
}
