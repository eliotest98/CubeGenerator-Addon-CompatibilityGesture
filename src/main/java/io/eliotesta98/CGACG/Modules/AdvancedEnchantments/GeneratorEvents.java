package io.eliotesta98.CGACG.Modules.AdvancedEnchantments;

import io.eliotesta98.CubeGenerator.Events.ApiEvents.BreakEvent;
import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class GeneratorEvents implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreakMinecraftEvent(BlockBreakEvent event) {
        CubeGeneratorAPI.doBlockBreak(event.getBlock(), event.getPlayer().getItemInHand(), event.getPlayer());
    }
}