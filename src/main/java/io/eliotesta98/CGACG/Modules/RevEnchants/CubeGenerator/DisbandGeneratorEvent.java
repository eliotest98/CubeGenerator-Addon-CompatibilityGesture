package io.eliotesta98.CGACG.Modules.RevEnchants.CubeGenerator;

import io.eliotesta98.CGACG.Modules.RevEnchants.WorldGuard.WorldGuardUtils;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.DisbandEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class DisbandGeneratorEvent implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onGeneratorDisband(DisbandEvent event) {
        WorldGuardUtils.removeRegion(event.getDisbander().getWorld(), "Cube_" + event.getGeneratorId());
    }

}