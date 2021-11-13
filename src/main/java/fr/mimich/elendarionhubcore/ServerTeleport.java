package fr.mimich.elendarionhubcore;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;

public class ServerTeleport {

    private ElendarionHubCore main;

    private final String CHANNEL_NAME;

    public ServerTeleport(ElendarionHubCore main) {
        this.main = main;
        this.CHANNEL_NAME = "BungeeCord";
        this.main.getServer().getMessenger().registerOutgoingPluginChannel(this.main, this.CHANNEL_NAME);
    }

    public void teleportPlayerToServer(Player player, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("server");
        player.sendPluginMessage(this.main, this.CHANNEL_NAME, out.toByteArray());
    }
}
