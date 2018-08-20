package cc.zoyn.decomposition.command.subcommand;

import cc.zoyn.decomposition.I18n;
import cc.zoyn.decomposition.command.CommandHandler;
import cc.zoyn.decomposition.command.SubCommand;
import cc.zoyn.decomposition.dao.CacheDao;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class UseCommand implements SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        ItemStack mainHand = player.getEquipment().getItemInMainHand();
        if (mainHand == null) {
            player.sendMessage("§c手上必须要有一个装备!");
            return;
        }
        if (!mainHand.hasItemMeta()) {
            player.sendMessage("§c手上的物品不能没有特殊数据!");
            return;
        }
        if (!mainHand.getItemMeta().hasLore()) {
            player.sendMessage("§c手上的物品必须要有Lore!");
            return;
        }
        int line = Integer.valueOf(args[1]) - 1;
        List<String> lore = mainHand.getItemMeta().getLore();
        if (line < 0 || line >= lore.size()) {
            player.sendMessage("§c输入了错误的行数!");
            return;
        }
        String loreData = lore.get(line);
        if (CacheDao.hasData(loreData)) {
            CacheDao.putData(loreData, "");
        }
        // 放入选中数据中
        CommandHandler.selectMap.put(sender.getName(), loreData);

        player.sendMessage(I18n.CREATE_LORE_SUCCESS.getMessage()
                .replaceAll("%lore%", lore.get(line)));
    }
}
