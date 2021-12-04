package fr.mimich.elendarionhubcore.listerners;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import fr.mimich.elendarionhubcore.JoinItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class JoinItemListener implements Listener {

    private ElendarionHubCore main;

    public JoinItemListener(ElendarionHubCore main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack itemStack = event.getItem();
        if (itemStack == null) return;

        Player player = event.getPlayer();
        JoinItem joinItem = this.main.getJoinItem();

        if (itemStack.isSimilar(joinItem.COMPASS_SERVERS_SELECTOR)) this.main.getServerSelectInventory().open(player);

        if (itemStack.isSimilar(joinItem.SERVER_INFO)) this.main.getInfoInventory().open(player);

        if (itemStack.isSimilar(joinItem.COSMETICS_SELECTOR)) Bukkit.dispatchCommand(player, "cosmetics");

        if (itemStack.isSimilar(joinItem.BOW_THRUSTER)) {
            final String base = "bow-thruster.multiplier.";
            Vector direction = player.getLocation().getDirection();
            player.setVelocity(new Vector(direction.getX() * this.main.getConfig().getDouble(base + "x"), this.main.getConfig().getDouble(base + "y"), direction.getZ() * this.main.getConfig().getDouble(base + "z")));
        }

        if (itemStack.isSimilar(joinItem.SHOW_ON) || itemStack.isSimilar(joinItem.SHOW_OFF))
            this.main.getShow().showProcess(player);

    }
}
