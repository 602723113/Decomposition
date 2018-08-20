package cc.zoyn.decomposition.command.subcommand;

import cc.zoyn.decomposition.I18n;
import cc.zoyn.decomposition.command.SubCommand;
import cc.zoyn.decomposition.dao.CacheDao;
import org.bukkit.command.CommandSender;

public class ListCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("§7====== §e[§6Decomposition§e] §7======");
        if (!CacheDao.getCache().isEmpty()) {
            CacheDao.getCache().keySet().forEach(s -> sender.sendMessage(" §7- " + s));
            return;
        }
        sender.sendMessage(I18n.LORE_IS_EMPTY.getMessage());
    }
}
