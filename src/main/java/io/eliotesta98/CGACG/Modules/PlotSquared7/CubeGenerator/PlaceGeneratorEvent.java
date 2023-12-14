package io.eliotesta98.CGACG.Modules.PlotSquared7.CubeGenerator;

import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CGACG.Modules.PlotSquared7.PlotSquaredUtils;
import io.eliotesta98.CGACG.Utils.DebugUtils;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.PlaceEvent;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlaceGeneratorEvent implements Listener {

    private final boolean debugCompatibility = Main.instance.getConfigGestion().getDebug().get("Compatibility");

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlaceGenerator(PlaceEvent event) {
        DebugUtils debug = new DebugUtils();
        long tempo = System.currentTimeMillis();

        if (!PlotSquaredUtils.checkIfPlayerIsInPlot(event.getPlacer().getLocation(), event.getPlacer().getName())) {
            if (!PlotSquaredUtils.checkIfBlockIsInPlot(event.getCentralBlock().getLocation())
                    || PlotSquaredUtils.checkIfBlockIsOnRoadPlot(event.getCentralBlock().getLocation())) {
                event.setReturnItem(false);
                event.setCancelled(true);
                if (debugCompatibility) {
                    debug.addLine("PlaceEvent Event Cancelled because the player try to place in a road or empty plot");
                    debug.addLine("PlaceEvent execution time= " + (System.currentTimeMillis() - tempo));
                    debug.debug("PlaceEvent");
                }
                return;
            }
        } else {
            if(PlotSquaredUtils.checkIfBlockIsOnRoadPlot(event.getCentralBlock().getLocation())) {
                event.setReturnItem(false);
                event.setCancelled(true);
                if (debugCompatibility) {
                    debug.addLine("PlaceEvent Event Cancelled because the player is in the plot but place it on corner");
                    debug.addLine("PlaceEvent execution time= " + (System.currentTimeMillis() - tempo));
                    debug.debug("PlaceEvent");
                }
                return;
            }
        }

        for (Location location : event.getFrameLocations()) {
            if (PlotSquaredUtils.checkIfBlockIsOnRoadPlot(location)) {
                if (PlotSquaredUtils.checkIfGeneratorIsPartiallyInPlot(event.getFrameLocations())) {
                    event.setCancelled(true);
                    if (debugCompatibility) {
                        debug.addLine("PlaceEvent Event Cancelled because a part of generator is in road");
                        debug.addLine("PlaceEvent execution time= " + (System.currentTimeMillis() - tempo));
                        debug.debug("PlaceEvent");
                    }
                    return;
                }
                event.setReturnItem(false);
                event.setCancelled(true);
                if (debugCompatibility) {
                    debug.addLine("PlaceEvent Event Cancelled because a frame location is in road");
                    debug.addLine("PlaceEvent execution time= " + (System.currentTimeMillis() - tempo));
                    debug.debug("PlaceEvent");
                }
                return;
            } else if (PlotSquaredUtils.checkIfBlockIsInEmptyPlot(location)) {
                event.setReturnItem(false);
                event.setCancelled(true);
                if (debugCompatibility) {
                    debug.addLine("PlaceEvent Event Cancelled because a frame location is in empty plot");
                    debug.addLine("PlaceEvent execution time= " + (System.currentTimeMillis() - tempo));
                    debug.debug("PlaceEvent");
                }
                return;
            }
        }

    }
}
