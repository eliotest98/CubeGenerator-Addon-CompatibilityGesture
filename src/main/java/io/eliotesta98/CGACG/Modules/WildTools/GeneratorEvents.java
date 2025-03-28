package io.eliotesta98.CGACG.Modules.WildTools;

import com.bgsoftware.wildtools.api.events.*;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.BreakEvent;
import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

public class GeneratorEvents implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBuilderWand(CuboidWandUseEvent event) {
        for (Location location : event.getBlocks()) {
            String generatorBlockType = CubeGeneratorAPI.isAGeneratorBlock(location.getBlock());
            if (generatorBlockType.equalsIgnoreCase("GENERATOR_BLOCK")) {
                boolean executed = CubeGeneratorAPI.doBlockBreak(location.getBlock(), event.getTool().getItemStack(), event.getPlayer());
                if (!executed) {
                    io.eliotesta98.CubeGenerator.Events.ApiEvents.BreakEvent breakEvent = new BreakEvent(event.getPlayer(), location.getBlock(), location.getBlock().getType().toString(), event.getTool().getItemStack());
                    Bukkit.getPluginManager().callEvent(breakEvent);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onItemSpawn(ItemSpawnEvent event) {
        if (event.getEntityType().toString().contains("ITEM")) {
            String generatorBlockType = CubeGeneratorAPI.isAGeneratorBlock(event.getLocation().getBlock());
            if (!generatorBlockType.equalsIgnoreCase("")) {
                event.getEntity().remove();
            }
        }
    }
}
