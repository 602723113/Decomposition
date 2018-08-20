package cc.zoyn.decomposition.gui;

import cc.zoyn.decomposition.command.CommandHandler;
import cc.zoyn.decomposition.dao.CacheDao;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SubmitGUI {

    public static void openGUI(Player player) {
        if (!player.isOnline()) {
            return;
        }
        player.closeInventory();
        Inventory inventory = Bukkit.createInventory(null, 54, "§c设置分解材料");
        inventory.addItem(CacheDao.getData(CommandHandler.selectMap.get(player.getName())));

        player.openInventory(inventory);
    }

}
