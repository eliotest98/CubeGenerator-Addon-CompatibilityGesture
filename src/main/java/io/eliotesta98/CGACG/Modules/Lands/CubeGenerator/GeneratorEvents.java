package io.eliotesta98.CGACG.Modules.Lands.CubeGenerator;

import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CGACG.Utils.ColorUtils;
import io.eliotesta98.CGACG.Utils.DebugUtils;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.ClickFrameEvent;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.PlaceEvent;
import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.Collection;
import java.util.UUID;

public class GeneratorEvents implements Listener {

    private final String errorLandClaimed = Main.instance.getConfigGestion().getMessages().get("Lands.ChunkClaimed");
    private final boolean debugCompatibility = Main.instance.getConfigGestion().getDebug().get("Compatibility");

    @EventHandler(priority = EventPriority.HIGHEST)
    public void placeGeneratorEvent(PlaceEvent event) {
        DebugUtils debug = new DebugUtils();
        long tempo = System.currentTimeMillis();
        Area area = Main.landsUtils.getLandsIntegration().getArea(event.getCentralBlock().getLocation());
        if (area == null) {
            ColorUtils.sendMessage(event.getPlacer(), errorLandClaimed);
            event.setReturnItem(true);
            event.setCancelled(true);
            if (debugCompatibility) {
                debug.addLine("Place Event cancelled because the Area of Land is null");
                debug.addLine("Compatibility execution time= " + (System.currentTimeMillis() - tempo));
                debug.debug("Compatibility");
            }
            return;
        }
        Land land = area.getLand();
        if (Main.instance.getConfigGestion().getExcludedLands().contains(land.getName())) {
            ColorUtils.sendMessage(event.getPlacer(), errorLandClaimed);
            event.setReturnItem(true);
            event.setCancelled(true);
            if (debugCompatibility) {
                debug.addLine("Place Event cancelled because the Land is in whitelist");
                debug.addLine("Place execution time= " + (System.currentTimeMillis() - tempo));
                debug.debug("Place");
            }
            return;
        }
        OfflinePlayer player = Bukkit.getOfflinePlayer(land.getOwnerUID());
        Collection<UUID> members = land.getTrustedPlayers();
        if ((player.getName() == null || !player.getName().equalsIgnoreCase(event.getPlacer().getName()))
                && !members.contains(event.getPlacer().getUniqueId())) {
            ColorUtils.sendMessage(event.getPlacer(), errorLandClaimed);
            event.setReturnItem(true);
            event.setCancelled(true);
            if (debugCompatibility) {
                debug.addLine("Place Event cancelled because the Land is in whitelist");
                debug.addLine("Place execution time= " + (System.currentTimeMillis() - tempo));
                debug.debug("Place");
            }
            return;
        }
        if (debugCompatibility) {
            debug.addLine("Compatibility execution time= " + (System.currentTimeMillis() - tempo));
            debug.debug("Compatibility");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void clickFrameEvent(ClickFrameEvent event) {
        DebugUtils debug = new DebugUtils();
        long tempo = System.currentTimeMillis();
        Area area = Main.landsUtils.getLandsIntegration().getArea(event.getClickedBlock().getLocation());
        if (area == null) {
            if (debugCompatibility) {
                debug.addLine("The Area is null");
                debug.addLine("Compatibility execution time= " + (System.currentTimeMillis() - tempo));
                debug.debug("Compatibility");
            }
            return;
        }
        for (LandPlayer a : area.getLand().getOnlineLandPlayers()) {
            if (a.getPlayer().getName().equalsIgnoreCase(event.getClicker().getName())) {
                int generatorId = CubeGeneratorAPI.getGeneratorIdFromLocation(event.getClickedBlock().getLocation());
                CubeGeneratorAPI.openGeneratorInterface(generatorId, event.getClicker());
                event.setCancelled(true);
                if (debugCompatibility) {
                    debug.addLine("This player is a Member of the Land");
                    debug.addLine("Compatibility execution time= " + (System.currentTimeMillis() - tempo));
                    debug.debug("Compatibility");
                }
                return;
            }
        }
        if (debugCompatibility) {
            debug.addLine("Compatibility execution time= " + (System.currentTimeMillis() - tempo));
            debug.debug("Compatibility");
        }
    }

}
