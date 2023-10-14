package io.eliotesta98.CGACG.Modules.CubeGenerator;

import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CGACG.Modules.RevBackpack.RevBackpackUtils;
import io.eliotesta98.CGACG.Utils.DebugUtils;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.AddItemAtInventoryEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class AddMaterialInventoryEvent implements Listener {

    private final DebugUtils debugUtils;
    private final boolean isDebugEnabled = Main.instance.getConfigGestion().getDebug().get("AddMaterialInventoryEvent");

    public AddMaterialInventoryEvent() {
        debugUtils = new DebugUtils();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onItemAdd(AddItemAtInventoryEvent event) {
        if(isDebugEnabled) {
            debugUtils.addLine("Start debug for AddItemAtInventoryEvent");
            debugUtils.addLine("Player: " + event.getPlayer().getName());
            debugUtils.addLine("ItemStack: " + event.getItemStack());
        }
        HashMap<ItemStack, Long> drops = new HashMap<>();
        drops.put(event.getItemStack(), (long) event.getItemStack().getAmount());
        RevBackpackUtils.invokeReceiveDropsEvent(event.getPlayer(), drops);
        event.setCancelled(true);
    }

}
