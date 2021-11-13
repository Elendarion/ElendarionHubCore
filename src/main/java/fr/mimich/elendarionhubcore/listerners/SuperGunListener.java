package fr.mimich.elendarionhubcore.listerners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import fr.mimich.elendarionhubcore.ElendarionHubCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class SuperGunListener extends PacketAdapter implements Listener {

    private ElendarionHubCore main;

    public SuperGunListener(ElendarionHubCore main) {
        super(main, PacketType.Play.Server.NAMED_SOUND_EFFECT);
        this.main = main;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        final String BASE = "super-gun.";
        if (this.main.getConfig().getBoolean(BASE + "enable")) {
            Player player = event.getPlayer();
            Location playerLocation = player.getLocation();
            String superGunLocation = BASE + "location.",
                    superGunLocationIn = superGunLocation + "in.",
                    superGunLocationOut = superGunLocation + "out.";
            FileConfiguration configuration = this.main.getConfig();
            if ((int) playerLocation.getX() == configuration.getInt(superGunLocationIn + "x") && (int) playerLocation.getY() == configuration.getInt(superGunLocationIn + "y") && (int) playerLocation.getZ() == configuration.getInt(superGunLocationIn + "z")) {
                Location teleportLocation = new Location(Bukkit.getWorld("world"), configuration.getDouble(superGunLocationOut + "x"), configuration.getDouble(superGunLocationOut + "y"), configuration.getDouble(superGunLocationOut + "z"), (float) configuration.getDouble(superGunLocationOut + "yaw"), (float) configuration.getDouble(superGunLocationOut + "pitch"));
                player.teleport(teleportLocation);
                Vector direction = player.getLocation().getDirection();
                double powerMultiplier = this.main.getConfig().getDouble(BASE + "power-multiplier");
                player.setVelocity(new Vector(direction.getX() * powerMultiplier, direction.getY() * powerMultiplier, direction.getZ() * powerMultiplier));
                if (this.main.getConfig().getBoolean(BASE + "explosion"))
                    player.getWorld().createExplosion(teleportLocation.getX(), teleportLocation.getY(), teleportLocation.getZ(), (float) powerMultiplier, false, false);
            }
        }
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        if (this.main.getShow().getPlayersDeactivatedShow().contains(event.getPlayer())) event.setCancelled(true);
    }
}
