package io.eliotesta98.CGACG.Modules.AdvancedEnchantments;

import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.BreakEvent;
import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class GeneratorEvents implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreakMinecraftEvent(BlockBreakEvent event) {
        CubeGeneratorAPI.doBlockBreak(event.getBlock(), event.getPlayer().getItemInHand(), event.getPlayer());
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onItemSpawn(ItemSpawnEvent event) {
        String entityType = event.getEntityType().toString();
        Bukkit.getScheduler().runTaskAsynchronously(Main.instance, () -> {
            if (entityType.contains("ITEM")) {
                String generatorBlockType = CubeGeneratorAPI.isAGeneratorBlock(event.getLocation().getBlock());
                if (!generatorBlockType.equalsIgnoreCase("")) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> event.setCancelled(true));
                }
            }
        });
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDropItem(PlayerDropItemEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.instance, () -> {
            String generatorBlockType = CubeGeneratorAPI.isAGeneratorBlock(event.getPlayer().getLocation().getBlock());
            if (!generatorBlockType.equalsIgnoreCase("")) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> event.setCancelled(true));
            }
        });
    }
}