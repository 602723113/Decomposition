package cc.zoyn.decomposition.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ResultGUI {

    public static void openGUI(Player player, ItemStack[] itemStacks) {
        if (!player.isOnline()) {
            return;
        }
        player.closeInventory();
        Inventory inventory = Bukkit.createInventory(null, 54, "§b请取出装备分解出的物品");
        inventory.addItem(itemStacks);

        player.openInventory(inventory);
    }
}
