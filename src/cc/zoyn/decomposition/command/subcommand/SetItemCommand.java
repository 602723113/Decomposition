package cc.zoyn.decomposition.command.subcommand;

import cc.zoyn.decomposition.I18n;
import cc.zoyn.decomposition.command.CommandHandler;
import cc.zoyn.decomposition.command.SubCommand;
import cc.zoyn.decomposition.gui.SubmitGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetItemCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (!CommandHandler.selectMap.containsKey(player.getName())) {
            player.sendMessage("§c你必须选中一个分解字符!");
            return;
        }
        player.sendMessage(I18n.PRE_SET_ITEM.getMessage()
                .replaceAll("%lore%", CommandHandler.selectMap.get(player.getName())));
        SubmitGUI.openGUI(player);
    }
}
