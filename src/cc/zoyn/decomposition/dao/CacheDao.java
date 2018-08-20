package cc.zoyn.decomposition.dao;

import cc.zoyn.decomposition.Decomposition;
import cc.zoyn.decomposition.util.ItemSerializerUtils;
import com.google.common.collect.Maps;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Set;

public class CacheDao {

    private static Map<String, String> cache = Maps.newLinkedHashMap();

    public static Map<String, String> getCache() {
        return cache;
    }

    public static void putData(String lore, String item) {
        cache.put(lore, item);
    }

    public static void removeData(String lore) {
        if (cache.containsKey(lore)) {
            cache.remove(lore);
        }
    }

    public static ItemStack[] getData(String lore) {
        if (cache.get(lore).equals("") || cache.get(lore).isEmpty()) {
            return new ItemStack[]{};
        }
        return ItemSerializerUtils.fromBase64(cache.get(lore));
    }

    public static boolean hasData(String lore) {
        return cache.containsKey(lore);
    }

    public static void saveDataToDataConfig() {
        cache.forEach((s, itemData) -> Decomposition.getInstance().getDataConfig().set(s, itemData));
    }

    public static void loadDataFromDataConfig() {
        cache.clear();
        Set<String> keys = Decomposition.getInstance().getDataConfig().getKeys(false);
        for (String key : keys) {
            cache.put(key, Decomposition.getInstance().getDataConfig().getString(key));
        }
    }

}
