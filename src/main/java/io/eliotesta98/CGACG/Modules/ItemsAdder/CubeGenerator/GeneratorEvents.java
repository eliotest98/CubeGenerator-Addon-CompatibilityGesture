package io.eliotesta98.CGACG.Modules.ItemsAdder.CubeGenerator;

import io.eliotesta98.CGACG.Modules.ItemsAdder.ItemsAdderUtils;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.PlaceGeneratorBlockEvent;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.BreakEvent;
import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class GeneratorEvents implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlace(PlaceGeneratorBlockEvent event) {
        if (event.getType().equalsIgnoreCase("Internal")) {
            if (ItemsAdderUtils.isMaterialCustom(event.getMaterial())) {
                event.setCancelled(true);
                ItemsAdderUtils.placeBlock(event.getMaterial(), event.getBlockPlaced().getLocation());
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBreak(BreakEvent breakEvent) {
        if (ItemsAdderUtils.isCustomBlock(breakEvent.getBlockBreaked())) {
            int id = CubeGeneratorAPI.getGeneratorIdFromLocation(breakEvent.getBlockBreaked().getLocation());
            breakEvent.setCancelled(true);
            // Added items at inventory
            for (ItemStack itemStack : ItemsAdderUtils.getItems(breakEvent.getBlockBreaked(), breakEvent.getItemInHand())) {
                CubeGeneratorAPI.addBlockToInventory(breakEvent.getBreaker(), breakEvent.getBlockBreaked(), itemStack, id);
            }
            // Set the block
            CubeGeneratorAPI.setRandomGeneratorBlock(id, breakEvent.getBlockBreaked().getLocation());
            //TODO damage itemstack
        }
    }

}
