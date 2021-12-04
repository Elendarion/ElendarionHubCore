package fr.mimich.elendarionhubcore.inventorys;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import fr.mimich.elendarionhubcore.utils.SuperConfig;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerInfoInventory extends FastInv {

    private ElendarionHubCore main;

    private Map<ItemStack, String> itemStackCommandsInfo;

    public ServerInfoInventory(ElendarionHubCore main) {
        super(45, main.getSuperConfig().getConfigText(SuperConfig.TextType.INVENTORY, "server-info.name"));
        this.main = main;
        this.main.getInventoryUtil().setDefaultContents(this);
        this.itemStackCommandsInfo = new HashMap<>();
        this.init();
    }

    private void init() {
        FileConfiguration configuration = main.getConfig();
        String base = this.main.getSuperConfig().addressBuilder(SuperConfig.TextType.INVENTORY, "server-info.commands-names");
        Set<String> commandsNames = new HashSet<>();
        configuration.getConfigurationSection(base).getKeys(true).forEach(keys -> commandsNames.add(keys.split("\\.")[0]));
        String prefix;
        ItemStack itemStack;
        Command command;
        Map<String, String> placeHolders = new HashMap<>();
        for (String commandName : commandsNames) {
            prefix = base + "." + commandName + ".";
            command = this.main.getCommand(commandName);
            if (command != null) {
                placeHolders.put("%usage%", command.getUsage());
                placeHolders.put("%description%", command.getDescription());
            }
            placeHolders.put("%name%", commandName);
            itemStack = new ItemBuilder(Material.valueOf(this.main.getConfig().getString(prefix + "material"))).name(this.main.getSuperConfig().getConfigText(null, prefix + "name", placeHolders)).lore(this.main.getSuperConfig().getConfigTextList(null, prefix + "lore", placeHolders)).build();
            setItem(this.main.getConfig().getInt(prefix + "position"), itemStack);
            this.itemStackCommandsInfo.putIfAbsent(itemStack, commandName);
            placeHolders.clear();
        }
    }

    @Override
    protected void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        for (Map.Entry<ItemStack, String> entry : this.itemStackCommandsInfo.entrySet()) {
            if (entry.getKey().isSimilar(event.getCurrentItem())) {
                player.closeInventory();
                Bukkit.dispatchCommand(player, entry.getValue());
            }
        }
    }
}
