package io.eliotesta98.CGACG.Modules.PlotSquared7;

import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.plot.Plot;
import org.bukkit.Bukkit;
import com.plotsquared.core.location.Location;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlotSquaredUtils {

    private static PlotAPI plotAPI;

    public PlotSquaredUtils() {
        plotAPI = new PlotAPI();
    }

    public static boolean checkIfBlockIsInEmptyPlot(org.bukkit.Location checkLocation) {
        return Location.at(checkLocation.getWorld().getName(), checkLocation.getBlockX(),
                checkLocation.getBlockY(), checkLocation.getBlockZ()).isUnownedPlotArea();
    }

    public static boolean checkIfBlockIsOnRoadPlot(org.bukkit.Location checkLocation) {
        return Location.at(checkLocation.getWorld().getName(), checkLocation.getBlockX(),
                checkLocation.getBlockY(), checkLocation.getBlockZ()).isPlotRoad();
    }

    public static boolean checkIfBlockIsInPlot(org.bukkit.Location checkLocation) {
        return Location.at(checkLocation.getWorld().getName(), checkLocation.getBlockX(),
                checkLocation.getBlockY(), checkLocation.getBlockZ()).isPlotArea();
    }

    public static Plot getPlotFromLocation(org.bukkit.Location checkLocation) {
        return Location.at(checkLocation.getWorld().getName(), checkLocation.getBlockX(),
                checkLocation.getBlockY(), checkLocation.getBlockZ()).getPlot();
    }

    public static boolean checkIfGeneratorIsPartiallyInPlot(ArrayList<org.bukkit.Location> locations) {
        for (org.bukkit.Location location : locations) {
            if(!checkIfBlockIsOnRoadPlot(location)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkIfPlayerIsInPlot(org.bukkit.Location location, String playerName) {
        Plot plot = getPlotFromLocation(location);
        Set<UUID> all = new HashSet<>();
        if (plot == null) {
            return false;
        }
        all.addAll(plot.getMembers());
        all.addAll(plot.getOwners());
        all.addAll(plot.getTrusted());
        for (UUID uuid : all) {
            if (Bukkit.getOfflinePlayer(uuid).getName().equalsIgnoreCase(playerName)) {
                return true;
            }
        }
        return false;
    }

    public static HashSet<UUID> getPlotPlayers(Plot plot) {
        HashSet<UUID> players = plot.getMembers();// prendo i membri
        players.addAll(plot.getOwners());
        players.addAll(plot.getTrusted());
        return players;
    }

    public static boolean checkIfPlayerHavePermission(String playerName, String permission) {
        return plotAPI.wrapPlayer(playerName).hasPermission(permission);
    }

}
