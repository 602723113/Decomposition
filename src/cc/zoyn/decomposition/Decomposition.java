package cc.zoyn.decomposition;

import cc.zoyn.decomposition.command.CommandHandler;
import cc.zoyn.decomposition.dao.CacheDao;
import cc.zoyn.decomposition.listener.InventoryClickListener;
import cc.zoyn.decomposition.listener.InventoryCloseListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * @author Zoyn
 * @since 2018-03-26
 */
public class Decomposition extends JavaPlugin {

    private static Decomposition instance;
    private File dataFile;
    private FileConfiguration dataConfig;

    public static Decomposition getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        dataFile = new File(getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);

        // 读取数据
        CacheDao.loadDataFromDataConfig();

        CommandExecutor executor = new CommandHandler();
        Bukkit.getPluginCommand("fadmin").setExecutor(executor);
        Bukkit.getPluginCommand("fj").setExecutor(executor);

        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), this);
    }

    @Override
    public void onDisable() {
        CacheDao.saveDataToDataConfig();
        saveDataConfig();
    }

    public File getDataFile() {
        return dataFile;
    }

    public FileConfiguration getDataConfig() {
        return dataConfig;
    }

    public void saveDataConfig() {
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
