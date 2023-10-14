package io.eliotesta98.CGACG.Modules.RevBackpack;

import me.revils.revenchants.events.ReceiveDropsEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class RevBackpackUtils {

    public static void invokeReceiveDropsEvent(Player player, HashMap<ItemStack, Long> drops) {
        new ReceiveDropsEvent(player,drops);
    }

}
