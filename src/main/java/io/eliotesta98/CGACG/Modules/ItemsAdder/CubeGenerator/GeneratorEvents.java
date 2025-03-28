package io.eliotesta98.CGACG.Modules.ItemsAdder.CubeGenerator;

import io.eliotesta98.CGACG.Modules.ItemsAdder.ItemsAdderUtils;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.PlaceGeneratorBlockEvent;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.RemoveGeneratorBlockEvent;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.BreakEvent;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.TakeFromInterfaceEvent;
import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class GeneratorEvents implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlace(PlaceGeneratorBlockEvent event) {
        if (event.getType().equalsIgnoreCase("Internal")) {
            if (ItemsAdderUtils.isCustomBlock(event.getBlockPlaced())) {
                ItemsAdderUtils.removeBlock(event.getBlockPlaced(), false);
            }
            if (event.getMaterial().contains("-")) {
                String[] split = event.getMaterial().toLowerCase().split("-");
                if (split.length != 3) {
                    return;
                }
                String nameSpace = split[1];
                String material = split[2];
                if (ItemsAdderUtils.isMaterialCustom(nameSpace + ":" + material)) {
                    event.setCancelled(true);
                    ItemsAdderUtils.placeBlock(nameSpace + ":" + material, event.getBlockPlaced().getLocation());
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBreak(BreakEvent breakEvent) {
        if (ItemsAdderUtils.isCustomBlock(breakEvent.getBlockBreaked())) {
            int id = CubeGeneratorAPI.getGeneratorIdFromLocation(breakEvent.getBlockBreaked().getLocation());
            String material = ItemsAdderUtils.getNamespaceIdFromBlock(breakEvent.getBlockBreaked());
            breakEvent.setCancelled(true);

            // Check if is a lucky block
            boolean rewarded = CubeGeneratorAPI.luckyBlockBreak(id, breakEvent.getBreaker(), material);
            if (!rewarded) {
                // Added items at inventory
                for (ItemStack itemStack : ItemsAdderUtils.getItems(breakEvent.getBlockBreaked(), breakEvent.getItemInHand())) {
                    CubeGeneratorAPI.addBlockToInventory(breakEvent.getBreaker(), breakEvent.getBlockBreaked(), itemStack, id);
                }
            }

            ItemsAdderUtils.removeBlock(breakEvent.getBlockBreaked(), true);
            // Set the block
            CubeGeneratorAPI.setRandomGeneratorBlock(id, breakEvent.getBlockBreaked().getLocation(), false, -1L, true, false);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onTakeItem(TakeFromInterfaceEvent event) {
        String customName = event.getItemSelected().getItemMeta().getDisplayName();
        if (ItemsAdderUtils.isItemCustom(customName)) {
            ItemStack itemStack = ItemsAdderUtils.getItemCustom(customName);
            event.setItemSelected(itemStack);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockDelete(RemoveGeneratorBlockEvent event) {
        if (ItemsAdderUtils.isCustomBlock(event.getBlockRemoved())) {
            event.setCancelled(true);
            ItemsAdderUtils.removeBlock(event.getBlockRemoved(), true);
        }
    }

}
