package io.eliotesta98.CGACG.Modules.PlotSquared7.CubeGenerator;

import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CGACG.Modules.PlotSquared7.PlotSquaredUtils;
import io.eliotesta98.CGACG.Utils.DebugUtils;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.ClickFrameEvent;
import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import java.util.HashSet;
import java.util.UUID;

public class ClickGeneratorFrameEvent implements Listener {

    private final boolean debugCompatibility = Main.instance.getConfigGestion().getDebug().get("Compatibility");

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlaceGenerator(ClickFrameEvent event) {
        DebugUtils debug = new DebugUtils();
        long tempo = System.currentTimeMillis();

        if (!event.getClicker().getName().equalsIgnoreCase(event.getGeneratorOwner())) {
            HashSet<UUID> players = PlotSquaredUtils.getPlotPlayers(PlotSquaredUtils.getPlotFromLocation(event.getClickedBlock().getLocation()));
            for (UUID uid : players) {// scorro i membri
                // controllo se il nome è in lista
                if (event.getClicker().getName().equalsIgnoreCase(Bukkit.getOfflinePlayer(uid).getName())) {
                    CubeGeneratorAPI.openGeneratorInterface(event.getGeneratorId(),event.getClicker());
                    break;
                }
            }
        } else {
            CubeGeneratorAPI.openGeneratorInterface(event.getGeneratorId(),event.getClicker());
        }

        event.setCancelled(true);
        if (debugCompatibility) {
            debug.addLine("ClickFrameEvent execution time= " + (System.currentTimeMillis() - tempo));
            debug.debug("ClickFrameEvent");
        }
    }
}
