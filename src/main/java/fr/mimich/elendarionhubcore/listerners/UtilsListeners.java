package fr.mimich.elendarionhubcore.listerners;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class UtilsListeners implements Listener {

    private ElendarionHubCore main;

    public UtilsListeners(ElendarionHubCore main) {
        this.main = main;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        this.main.getSpawn().teleportSpawn(player);
        this.main.getJoinItem().setJointItem(player);
    }
}
