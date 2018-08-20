package cc.zoyn.decomposition.command.subcommand;

import cc.zoyn.decomposition.command.CommandHandler;
import cc.zoyn.decomposition.command.SubCommand;
import cc.zoyn.decomposition.dao.CacheDao;
import org.bukkit.command.CommandSender;

public class DelCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!CommandHandler.selectMap.containsKey(sender.getName())) {
            sender.sendMessage("§c你必须选中一个分解字符!");
            return;
        }

        // 数据移除
        CacheDao.removeData(CommandHandler.selectMap.get(sender.getName()));
        CommandHandler.selectMap.remove(sender.getName());
        sender.sendMessage("§a删除成功!");
    }
}
