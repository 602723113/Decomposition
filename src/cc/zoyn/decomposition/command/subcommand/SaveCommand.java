package cc.zoyn.decomposition.command.subcommand;

import cc.zoyn.decomposition.Decomposition;
import cc.zoyn.decomposition.command.SubCommand;
import cc.zoyn.decomposition.dao.CacheDao;
import org.bukkit.command.CommandSender;

public class SaveCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        CacheDao.saveDataToDataConfig();
        Decomposition.getInstance().saveDataConfig();
        sender.sendMessage("§a正在保存数据中...");
    }
}
