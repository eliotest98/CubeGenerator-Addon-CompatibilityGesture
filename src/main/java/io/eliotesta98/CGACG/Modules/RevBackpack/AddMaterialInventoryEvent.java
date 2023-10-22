package io.eliotesta98.CGACG.Modules.RevBackpack;

import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CGACG.Utils.DebugUtils;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.AddItemAtInventoryEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class AddMaterialInventoryEvent implements Listener {

    private final DebugUtils debugUtils;
    private final boolean isDebugEnabled = Main.instance.getConfigGestion().getDebug().get("AddMaterialInventoryEvent");

    public AddMaterialInventoryEvent() {
        debugUtils = new DebugUtils();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onItemAdd(AddItemAtInventoryEvent event) {
        if (isDebugEnabled) {
            debugUtils.addLine("Start debug for AddItemAtInventoryEvent");
            debugUtils.addLine("Player: " + event.getPlayer().getName());
            debugUtils.addLine("ItemStack: " + event.getItemStack());
        }
        RevBackpackUtils.addItemAtBackpack(event.getPlayer(), event.getItemStack(), event.getItemStack().getAmount());
        event.setCancelled(true);
    }

}
