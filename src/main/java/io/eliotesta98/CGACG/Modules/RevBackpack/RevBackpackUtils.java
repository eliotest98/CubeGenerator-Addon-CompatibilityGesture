package io.eliotesta98.CGACG.Modules.RevBackpack;

import me.revils.revbackpack.backpack.RevBackPackApi;
import me.revils.revenchants.events.ReceiveDropsEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.Map;

public class RevBackpackUtils {

    public static void addItemAtBackpack(Player player, ItemStack itemStack, int amount) {
        RevBackPackApi.addItem(player, itemStack, amount);
    }

    public static void receiveDropEventGesture(Player player, ReceiveDropsEvent receiveDropsEvent) {
        RevBackPackApi.setTempMultiplier(player, receiveDropsEvent.getMultiplier());
        for (Map.Entry<ItemStack, Long> entry4 : receiveDropsEvent.getDrops().entrySet()) {
            RevBackPackApi.addItem(player, entry4.getKey().clone(), entry4.getValue());
        }
        if (receiveDropsEvent.getAutoSell()) {
            RevBackPackApi.Sellall(player, false);
        }
    }

}
