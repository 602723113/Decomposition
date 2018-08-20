package cc.zoyn.decomposition.command.subcommand;

import cc.zoyn.decomposition.I18n;
import cc.zoyn.decomposition.command.CommandHandler;
import cc.zoyn.decomposition.command.SubCommand;
import cc.zoyn.decomposition.dao.CacheDao;
import org.bukkit.command.CommandSender;

public class CreateCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("§c参数不正确!");
            return;
        }
        String lore = args[1].replaceAll("&", "§");
        CacheDao.putData(lore, "");
        // 放入选中数据中
        CommandHandler.selectMap.put(sender.getName(), lore);

        sender.sendMessage(I18n.CREATE_LORE_SUCCESS.getMessage()
                .replaceAll("%lore%", lore));
    }
}
