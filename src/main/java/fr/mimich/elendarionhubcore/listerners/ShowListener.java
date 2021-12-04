package fr.mimich.elendarionhubcore.listerners;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import fr.mimich.elendarionhubcore.ElendarionHubCore;
import fr.mimich.elendarionhubcore.Show;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ShowListener extends PacketAdapter implements Listener {

    private ElendarionHubCore main;
    private Show show;

    public ShowListener(ElendarionHubCore main) {
        super(main, PacketType.Play.Server.EXPLOSION);
        this.main = main;
        this.show = this.main.getShow();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        show.getPlayersUUIDDeactivatedShow().forEach(playerUUID -> Bukkit.getPlayer(playerUUID).hidePlayer(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        this.show.getPlayersUUIDDeactivatedShow().remove(event.getPlayer().getUniqueId());
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        if (this.main.getShow().getPlayersUUIDDeactivatedShow().contains(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }
}
