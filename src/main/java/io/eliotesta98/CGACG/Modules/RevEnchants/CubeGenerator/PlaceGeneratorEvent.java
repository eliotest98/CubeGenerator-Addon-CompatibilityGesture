package io.eliotesta98.CGACG.Modules.RevEnchants.CubeGenerator;

import io.eliotesta98.CGACG.Modules.RevEnchants.WorldGuard.WorldGuardUtils;
import io.eliotesta98.CGACG.Utils.AnglesSearcher;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.PlaceEvent;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class PlaceGeneratorEvent implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlaceGenerator(PlaceEvent event) {
        ArrayList<Location> angles = AnglesSearcher.searchAngle(event.getInternalLocations());
        WorldGuardUtils.createRegion(
                event.getPlacer().getWorld(),
                "Cube_" + event.getGeneratorId(),
                WorldGuardUtils.getVectorFromLocation(angles.get(0)),
                WorldGuardUtils.getVectorFromLocation(angles.get(1)));
    }

}
