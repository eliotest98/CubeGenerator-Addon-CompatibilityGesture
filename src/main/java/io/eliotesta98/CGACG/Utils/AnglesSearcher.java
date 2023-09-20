package io.eliotesta98.CGACG.Utils;

import org.bukkit.Location;
import org.bukkit.World;
import java.util.ArrayList;

public class AnglesSearcher {

    // 0 = angolo min, 1 = angolo max
    public static ArrayList<Location> searchAngle(ArrayList<Location> locations) {
        // Inizializza le coordinate minime e massime con i valori massimi e minimi possibili iniziali
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;
        World world = locations.get(0).getWorld();

        for (Location loc : locations) {
            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();

            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            minZ = Math.min(minZ, z);

            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
            maxZ = Math.max(maxZ, z);
        }

        ArrayList<Location> angoli = new ArrayList<>();
        angoli.add(new Location(world, minX, minY, minZ));
        angoli.add(new Location(world, maxX, maxY, maxZ));
        return angoli;
    }
}
