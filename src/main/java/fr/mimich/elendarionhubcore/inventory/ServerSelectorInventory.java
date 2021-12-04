package fr.mimich.elendarionhubcore.inventory;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import fr.mimich.elendarionhubcore.utils.SuperConfig;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerSelectorInventory extends FastInv {

    private ElendarionHubCore main;

    private Map<ItemStack, String> itemStackServerName;

    public ServerSelectorInventory(ElendarionHubCore main) {
        super(27, main.getSuperConfig().getConfigText(SuperConfig.TextType.INVENTORY, "servers-selector.name"));
        this.main = main;
        this.main.getInventoryUtil().setDefaultContents(this);
        this.itemStackServerName = new HashMap<>();
        this.init();
    }

    private void init() {
        FileConfiguration configuration = this.main.getConfig();
        String base = this.main.getSuperConfig().addressBuilder(SuperConfig.TextType.INVENTORY, "servers-selector.servers");
        Set<String> serversNames = new HashSet<>();
        configuration.getConfigurationSection(base).getKeys(true).forEach(keys -> serversNames.add(keys.split("\\.")[0]));
        String prefix;
        ItemStack itemStack;
        for (String serverName : serversNames) {
            prefix = base + "." + serverName + ".";
            itemStack = new ItemBuilder(Material.valueOf(this.main.getConfig().getString(prefix + "material"))).name(this.main.getSuperConfig().getConfigText(null, prefix + "name")).lore(this.main.getSuperConfig().getConfigTextList(null, prefix + "lore")).enchant(Enchantment.DURABILITY).flags(ItemFlag.HIDE_ENCHANTS).build();
            setItem(this.main.getConfig().getInt(prefix + "position"), itemStack);
            this.itemStackServerName.putIfAbsent(itemStack, serverName);
        }
    }

    @Override
    protected void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        for (Map.Entry<ItemStack, String> entry : this.itemStackServerName.entrySet()) {
            if (entry.getKey().isSimilar(event.getCurrentItem())) {
                this.main.getServerTeleport().teleportPlayerToServer(player, entry.getValue());
                player.closeInventory();
            }
        }
    }
}
