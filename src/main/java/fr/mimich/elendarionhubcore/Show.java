package fr.mimich.elendarionhubcore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class Show {

    private ElendarionHubCore main;
    private Set<Player> playersDeactivatedShow;

    public Show(ElendarionHubCore main) {
        this.main = main;
        this.playersDeactivatedShow = new HashSet<>();
    }

    public void showProcess(Player player) {
        int inventoryPlace = 6;
        JoinItem joinItem = this.main.getJoinItem();
        if (!this.playersDeactivatedShow.contains(player)) {
            this.playersDeactivatedShow.add(player);
            Bukkit.getOnlinePlayers().forEach(player::hidePlayer);
            player.getInventory().setItem(inventoryPlace, joinItem.SHOW_OFF);
            player.sendMessage(this.main.getSuperConfig().getConfigText(SuperConfig.TextType.MESSAGE, "show-off"));
        } else {
            this.playersDeactivatedShow.remove(player);
            Bukkit.getOnlinePlayers().forEach(player::showPlayer);
            player.getInventory().setItem(inventoryPlace, joinItem.SHOW_ON);
            player.sendMessage(this.main.getSuperConfig().getConfigText(SuperConfig.TextType.MESSAGE, "show-on"));
        }
    }

    public Set<Player> getPlayersDeactivatedShow() {
        return playersDeactivatedShow;
    }
}
