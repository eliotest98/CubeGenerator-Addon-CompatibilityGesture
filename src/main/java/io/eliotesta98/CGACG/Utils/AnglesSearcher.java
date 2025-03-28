package io.eliotesta98.CGACG.Utils;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class AnglesSearcher {

    // 0 = angolo min, 1 = angolo max
    public static List<Location> searchAngle(List<Location> locations) {
        // Inizializza le coordinate minime e massime con i valori massimi e minimi possibili iniziali
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int minZ = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int maxZ = Integer.MIN_VALUE;
        World world = locations.get(0).getWorld();

        for (Location loc : locations) {
            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ();

            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            minZ = Math.min(minZ, z);

            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
            maxZ = Math.max(maxZ, z);
        }

        ArrayList<Location> angoli = new ArrayList<>();
        angoli.add(new Location(world, minX, minY, minZ).add(1, 1, 1));
        angoli.add(new Location(world, maxX, maxY, maxZ).subtract(1, 1, 1));
        return angoli;
    }
}
