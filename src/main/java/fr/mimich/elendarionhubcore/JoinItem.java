package fr.mimich.elendarionhubcore;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.Map;

public class JoinItem {

    private ElendarionHubCore main;
    private SuperConfig superConfig;
    public JoinItem(ElendarionHubCore main) {
        this.main = main;
        this.superConfig = this.main.getSuperConfig();
        this.init();
    }

    private ItemStack itemJoinBuilder(Material material, String itemConfigAddress, Map<String, String> placeHolders, int data){
        final String PREFIX = "join-item.";
        SuperConfig superConfig = this.main.getSuperConfig();
        return new ItemBuilder(material).data(data).name(superConfig.getConfigText(SuperConfig.TextType.ITEM, PREFIX + itemConfigAddress + ".name")).lore(superConfig.getConfigTextList(SuperConfig.TextType.ITEM, PREFIX + itemConfigAddress + ".lore", placeHolders)).enchant(Enchantment.DURABILITY).flags(ItemFlag.HIDE_ENCHANTS).build();
    }

    private ItemStack itemJoinBuilder(Material material, String itemConfigAddress, Map<String, String> placeHolders){
        return  itemJoinBuilder(material, itemConfigAddress, placeHolders, 0);
    }

    private ItemStack itemJoinBuilder(Material material, String itemConfigAddress){
        return  itemJoinBuilder(material, itemConfigAddress, Collections.emptyMap());
    }

    private Map<String, String> placeHolderDescription(String commandName){
        return Collections.singletonMap("%description%", this.main.getServer().getPluginCommand(commandName).getDescription());
    }

    public ItemStack COMPASS_SERVERS_SELECTOR;
    public ItemStack SERVER_INFOS;
    public ItemStack COSMETICS_SELECTOR;
    public ItemStack BOW_THRUSTER;

    public ItemStack SHOW_ON;
    public ItemStack SHOW_OFF;

    private void init(){
        COMPASS_SERVERS_SELECTOR = this.itemJoinBuilder(Material.COMPASS, "compass-servers-selector");
        SERVER_INFOS = this.itemJoinBuilder(Material.PAPER, "server-infos", this.placeHolderDescription("infos"));
        COSMETICS_SELECTOR = this.itemJoinBuilder(Material.WATCH, "cosmetics-selector");
        BOW_THRUSTER = this.itemJoinBuilder(Material.BOW, "bow-thruster");

        SHOW_ON = this.itemJoinBuilder(Material.INK_SACK, "show-on", this.placeHolderDescription("show"), 10);
        SHOW_OFF = this.itemJoinBuilder(Material.INK_SACK, "show-off", this.placeHolderDescription("show"), 8);
    }

    public void setJointItem(Player player){
        Inventory playerInventory = player.getInventory();
        playerInventory.clear();
        playerInventory.setItem(0, COMPASS_SERVERS_SELECTOR);
        playerInventory.setItem(2, SERVER_INFOS);
        playerInventory.setItem(4, COSMETICS_SELECTOR);
        playerInventory.setItem(8, BOW_THRUSTER);
        playerInventory.setItem(6, SHOW_ON);
    }
}
