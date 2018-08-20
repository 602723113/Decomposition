package cc.zoyn.decomposition.command;

import cc.zoyn.decomposition.command.subcommand.*;
import cc.zoyn.decomposition.gui.DecompositionGUI;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * @author Zoyn
 * @since 2018-03-22
 */
public class CommandHandler implements CommandExecutor {

    public static Map<String, SubCommand> commandMap = Maps.newHashMap();
    public static Map<String, String> selectMap = Maps.newHashMap();

    /**
     * Initialize all sub commands
     */
    public CommandHandler() {
        registerCommand("help", new HelpCommand());
        registerCommand("create", new CreateCommand());
        registerCommand("del", new DelCommand());
        registerCommand("list", new ListCommand());
        registerCommand("save", new SaveCommand());
        registerCommand("sel", new SelCommand());
        registerCommand("setitem", new SetItemCommand());
        registerCommand("use", new UseCommand());
    }

    private void registerCommand(String commandName, SubCommand subCommand) {
        if (commandMap.containsKey(commandName)) {
            Bukkit.getLogger().warning("duplicate add command!");
        }
        commandMap.put(commandName, subCommand);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§f控制台禁止输入此命令!");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("fj")) {
            Player player = (Player) sender;
            DecompositionGUI.openGUI(player);

        }
        if (cmd.getName().equalsIgnoreCase("fadmin")) {
            if (!sender.isOp()) {
                sender.sendMessage("§c权限不足!");
                return true;
            }
            if (args.length == 0) {
                commandMap.get("help").execute(sender, args);
                return true;
            }
            if (!commandMap.containsKey(args[0])) {
                sender.sendMessage("§c未知指令!");
                return true;
            }
            // args[0] ---> SubCommand name
            SubCommand subCommand = commandMap.get(args[0]);
            subCommand.execute(sender, args);
        }
        return true;
    }
}
