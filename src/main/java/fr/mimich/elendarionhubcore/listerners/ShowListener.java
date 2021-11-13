package fr.mimich.elendarionhubcore.listerners;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import fr.mimich.elendarionhubcore.ElendarionHubCore;
import fr.mimich.elendarionhubcore.Show;
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
        show.getPlayersDeactivatedShow().forEach(player -> player.hidePlayer(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.show.getPlayersDeactivatedShow().remove(player);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        if (this.main.getShow().getPlayersDeactivatedShow().contains(event.getPlayer())) event.setCancelled(true);
    }
}
