package fr.mimich.elendarionhubcore;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;

public class ServerTeleport {

    private ElendarionHubCore main;

    private final String channelName;

    public ServerTeleport(ElendarionHubCore main) {
        this.main = main;
        this.channelName = "BungeeCord";
        this.main.getServer().getMessenger().registerOutgoingPluginChannel(this.main, this.channelName);
    }

    public void teleportPlayerToServer(Player player, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("server");
        player.sendPluginMessage(this.main, this.channelName, out.toByteArray());
    }
}
