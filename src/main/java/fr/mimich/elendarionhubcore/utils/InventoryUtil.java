package fr.mimich.elendarionhubcore.utils;

import fr.mimich.elendarionhubcore.ElendarionHubCore;
import fr.mimich.elendarionhubcore.utils.SuperConfig;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
public class InventoryUtil {

    private ElendarionHubCore main;

    public InventoryUtil(ElendarionHubCore main) {
        this.main = main;
    }

    public void setDefaultContents(FastInv fastInv){
        fastInv.setItems(fastInv.getBorders(), new ItemBuilder(Material.STAINED_GLASS_PANE).data(2).build());
        fastInv.setItems(fastInv.getCorners(), new ItemBuilder(Material.STAINED_GLASS_PANE).data(10).enchant(Enchantment.DURABILITY).flags(ItemFlag.HIDE_ENCHANTS).build());
        fastInv.setItem(fastInv.getInventory().getSize() - 5,  new ItemBuilder(Material.BARRIER).enchant(Enchantment.DURABILITY).name(this.main.getSuperConfig().getConfigText(SuperConfig.TextType.INVENTORY, "leave")).flags(ItemFlag.HIDE_ENCHANTS).build(), e -> e.getWhoClicked().closeInventory());
    }
}
