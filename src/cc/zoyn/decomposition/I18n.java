package cc.zoyn.decomposition;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Zoyn
 * @since 2018_03_22
 */
public enum I18n {

    CREATE_LORE_SUCCESS(translateColorCode(Decomposition.getInstance().getConfig().getString("tips.create-lore-success"))),
    PRE_SET_ITEM(translateColorCode(Decomposition.getInstance().getConfig().getString("tips.pre-set-item"))),
    SET_ITEM_DONE(translateColorCode(Decomposition.getInstance().getConfig().getString("tips.set-item-done"))),
    LORE_IS_EMPTY(translateColorCode(Decomposition.getInstance().getConfig().getString("tips.lore-is-empty"))),
    THE_WEAPON_CANT_DECOMPOSE(translateColorCode(Decomposition.getInstance().getConfig().getString("tips.the-weapon-cant-decompose"))),
    DECOMPOSITIONGUI_CLOSE(translateColorCode(Decomposition.getInstance().getConfig().getString("tips.decompositiongui-close")));

    private String message;

    I18n(String message) {
        this.message = message;
    }

    private static String translateColorCode(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    private static List<String> translateColorCode(List<String> messages) {
        return messages.stream().map(I18n::translateColorCode).collect(Collectors.toList());
    }

    public String getMessage() {
        return message;
    }

}
