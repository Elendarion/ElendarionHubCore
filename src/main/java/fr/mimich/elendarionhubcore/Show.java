package fr.mimich.elendarionhubcore;

import fr.mimich.elendarionhubcore.utils.SuperConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Show {

    private ElendarionHubCore main;
    private Set<UUID> playersUUIDDeactivatedShow;

    public Show(ElendarionHubCore main) {
        this.main = main;
        this.playersUUIDDeactivatedShow = new HashSet<>();
    }

    public void showProcess(Player player) {
        int inventoryPlace = 6;
        JoinItem joinItem = this.main.getJoinItem();
        if (!this.playersUUIDDeactivatedShow.contains(player.getUniqueId())) {
            this.playersUUIDDeactivatedShow.add(player.getUniqueId());
            Bukkit.getOnlinePlayers().forEach(player::hidePlayer);
            player.getInventory().setItem(inventoryPlace, joinItem.SHOW_OFF);
            player.sendMessage(this.main.getSuperConfig().getConfigText(SuperConfig.TextType.MESSAGE, "show-off"));
        } else {
            this.playersUUIDDeactivatedShow.remove(player.getUniqueId());
            Bukkit.getOnlinePlayers().forEach(player::showPlayer);
            player.getInventory().setItem(inventoryPlace, joinItem.SHOW_ON);
            player.sendMessage(this.main.getSuperConfig().getConfigText(SuperConfig.TextType.MESSAGE, "show-on"));
        }
    }

    public Set<UUID> getPlayersUUIDDeactivatedShow() {
        return playersUUIDDeactivatedShow;
    }
}
