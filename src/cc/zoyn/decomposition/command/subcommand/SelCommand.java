package cc.zoyn.decomposition.command.subcommand;

import cc.zoyn.decomposition.command.CommandHandler;
import cc.zoyn.decomposition.command.SubCommand;
import cc.zoyn.decomposition.dao.CacheDao;
import org.bukkit.command.CommandSender;

public class SelCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("§c参数不正确!");
            return;
        }
        String lore = args[1].replaceAll("&", "§");
        if (!CacheDao.hasData(lore)) {
            sender.sendMessage("§c缓存中未找到该分解字符!");
            return;
        }
        CommandHandler.selectMap.put(sender.getName(), lore);
        sender.sendMessage("§a选取分解字符 " + args[1] + " 成功!");
    }
}
