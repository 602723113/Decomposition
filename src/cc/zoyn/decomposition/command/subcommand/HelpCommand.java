package cc.zoyn.decomposition.command.subcommand;

import cc.zoyn.decomposition.command.SubCommand;
import org.bukkit.command.CommandSender;

/**
 * @author Zoyn
 * @since 2017-3-11
 */
public class HelpCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("§7====== §e[§6Decomposition§e] §7======");
        sender.sendMessage("§b/fadmin create <字符> §7创建一个Lore上的分解字符");
        sender.sendMessage("§b/fadmin use <Lore行数> §7提取手上装备的Lore某行作为分解字符");
        sender.sendMessage("§b/fadmin list §7查看所有分解字符");
        sender.sendMessage("§b/fadmin sel <分解字符> §7选择某个分级字符");
        sender.sendMessage("§b/fadmin setitem §7设置某个分解字符的分解结果");
        sender.sendMessage("§b/fadmin del 删除某个分解字符");
        sender.sendMessage("§b/fadmin save §7保存数据");
    }
}