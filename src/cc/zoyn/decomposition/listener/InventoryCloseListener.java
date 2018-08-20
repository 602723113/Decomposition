package cc.zoyn.decomposition.listener;

import cc.zoyn.decomposition.I18n;
import cc.zoyn.decomposition.command.CommandHandler;
import cc.zoyn.decomposition.dao.CacheDao;
import cc.zoyn.decomposition.util.ItemSerializerUtils;
import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Inventory inv = event.getInventory();
        String title = inv.getTitle();
        Player player = (Player) event.getPlayer();
        if (title.equals("§c设置分解材料")) {
            if (CommandHandler.selectMap.containsKey(player.getName())) {
                String lore = CommandHandler.selectMap.get(player.getName());
                CacheDao.putData(lore, ItemSerializerUtils.toBase64(getInventoryNotEmptyContents(inv)));
                player.sendMessage(I18n.SET_ITEM_DONE.getMessage()
                        .replaceAll("%lore%", lore));
                return;
            }
        }
        if (title.equals("§c分解装备")) {
            Map<String, Boolean> cache = InventoryClickListener.clickCache;
            if (!cache.get(player.getName())) { // 为false则意味没点击过那个岩浆桶
                ItemStack item = inv.getItem(2);
                if (item != null && !item.getType().equals(Material.AIR)) {
                    player.getInventory().addItem(inv.getItem(2));
                }
            }
        }
        if (title.equals("§b请取出装备分解出的物品")) {
            ItemStack[] itemStacks = getInventoryNotEmptyContents(inv);
            // 代表有物品
            if (itemStacks.length != 0) {
                player.getInventory().addItem(itemStacks);
                player.sendMessage(I18n.DECOMPOSITIONGUI_CLOSE.getMessage());
            }
        }
    }

    private ItemStack[] getInventoryNotEmptyContents(Inventory inventory) {
        List<ItemStack> itemStacks = Lists.newArrayList();

        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack != null) {
                itemStacks.add(itemStack);
            }
        }
        return itemStacks.toArray(new ItemStack[]{});
    }

}
