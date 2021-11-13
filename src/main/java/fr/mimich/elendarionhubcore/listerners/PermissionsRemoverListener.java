package fr.mimich.elendarionhubcore.listerners;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PermissionsRemoverListener implements Listener {

    private ElendarionHubCore main;

    public PermissionsRemoverListener(ElendarionHubCore main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.main.getConfig().getStringList("removed-permissions").forEach(perm -> event.getPlayer().addAttachment(this.main).setPermission(perm, false));
    }
}
