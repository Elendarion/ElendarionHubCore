package fr.mimich.elendarionhubcore.listerners;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import net.jitse.npclib.api.events.NPCInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Map;

public class NPCListener implements Listener {

    private ElendarionHubCore main;

    public NPCListener(ElendarionHubCore main) {
        this.main = main;
    }

    private boolean firstJoin = false;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        if (!firstJoin) {
            this.main.getNpc().loadNpc();
            firstJoin = true;
        }
        this.main.getNpc().getNpcList().forEach(npc -> npc.show(event.getPlayer()));
    }

    @EventHandler
    public void onNPCInteract(NPCInteractEvent event) {
        for (Map.Entry entry : this.main.getNpc().getNpcServers().entrySet()) {
            if (entry.getKey().equals(event.getNPC())) {
                this.main.getServerTeleport().teleportPlayerToServer(event.getWhoClicked(), (String) entry.getValue());
            }
        }
    }
}
