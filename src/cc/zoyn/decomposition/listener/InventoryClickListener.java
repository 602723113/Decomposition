package cc.zoyn.decomposition.listener;

import cc.zoyn.decomposition.I18n;
import cc.zoyn.decomposition.dao.CacheDao;
import cc.zoyn.decomposition.gui.ResultGUI;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class InventoryClickListener implements Listener {

    private static final List<Integer> buttonList = Lists.newArrayList(0, 1, 3, 4, 5, 7, 8);
    public static Map<String, Boolean> clickCache = Maps.newHashMap();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        String title = inv.getTitle();
        Player player = (Player) event.getWhoClicked();
        if (title.equals("§c分解装备")) {
            if (event.getRawSlot() == 6) {
                event.setCancelled(true);

                ItemStack weapon = inv.getItem(2);
                if (weapon == null || weapon.getType().equals(Material.AIR) || !weapon.hasItemMeta() || !weapon.getItemMeta().hasLore()) {
                    player.sendMessage(I18n.THE_WEAPON_CANT_DECOMPOSE.getMessage());
                    return;
                }
                ItemMeta itemMeta = weapon.getItemMeta();
                List<String> lore = itemMeta.getLore();

                // lore判断
                for (String key : CacheDao.getCache().keySet()) {
                    if (lore.contains(key)) {
                        clickCache.put(player.getName(), true);
                        ItemStack[] itemStacks = CacheDao.getData(key);
                        ResultGUI.openGUI(player, itemStacks);
                        return;
                    }
                }

                // 如果都遍历完了都不匹配则自动提示
                player.sendMessage(I18n.THE_WEAPON_CANT_DECOMPOSE.getMessage());
                return;
            }
            if (buttonList.contains(event.getRawSlot())) {
                event.setCancelled(true);
            }
        }
    }

}
