package cc.zoyn.decomposition.gui;

import cc.zoyn.decomposition.listener.InventoryClickListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DecompositionGUI {

    private static Inventory inventory;
    private static ItemStack glass;
    private static ItemStack fireButton;
    private static int[] glassIndex = {0, 1, 3, 4, 5, 7, 8};

    static {
        ItemMeta itemMeta;

        glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        itemMeta = glass.getItemMeta();
        itemMeta.setDisplayName(" ");
        glass.setItemMeta(itemMeta);

        fireButton = new ItemStack(Material.LAVA_BUCKET);
        itemMeta = fireButton.getItemMeta();
        itemMeta.setDisplayName("§c点击开始分解该装备");
        fireButton.setItemMeta(itemMeta);
    }

    public static void openGUI(Player player) {
        if (!player.isOnline()) {
            return;
        }
        inventory = Bukkit.createInventory(null, 9, "§c分解装备");
        for (int index : glassIndex) {
            inventory.setItem(index, glass);
        }
        inventory.setItem(6, fireButton);

        player.closeInventory();
        player.openInventory(inventory);
        // 放入未点击缓存中
        InventoryClickListener.clickCache.put(player.getName(), false);
    }

}

