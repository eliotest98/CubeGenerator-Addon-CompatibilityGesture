package io.eliotesta98.CGACG.Modules.RevEnchants;

import io.eliotesta98.CubeGenerator.Events.ApiEvents.AddItemAtInventoryEvent;
import me.revils.revenchants.api.RevEnchantsApi;
import me.revils.revenchants.events.ReceiveDropsEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static org.bukkit.event.EventPriority.NORMAL;

public class DropsLikeRevEnchantsMine implements Listener {

    @EventHandler(priority = NORMAL)
    public void onAddItemAtInventoryEvent(AddItemAtInventoryEvent event) {
        ItemStack pickaxe = event.getPlayer().getItemInHand();
        HashMap<org.bukkit.inventory.ItemStack, Long> drops = new HashMap<>();
        drops.put(event.getItemStack(), 1L);
        ReceiveDropsEvent rcde = new ReceiveDropsEvent(event.getPlayer(), drops);
        if(RevEnchantUtils.isRevTool(pickaxe)) {
            long fortune = RevEnchantUtils.getEnchantLevel(RevEnchantUtils.getRevTool(pickaxe), "Fortune");
            rcde.setFortune(fortune);
        }
        Bukkit.getPluginManager().callEvent(rcde);
        event.setCancelled(true);
    }

}
