package io.eliotesta98.CGACG.Modules.PlotSquared7;

import com.google.common.eventbus.Subscribe;
import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.events.PlayerPlotHelperEvent;
import com.plotsquared.core.events.PlayerPlotTrustedEvent;
import com.plotsquared.core.events.PlotClearEvent;
import com.plotsquared.core.events.PlotDeleteEvent;
import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CGACG.Utils.DebugUtils;
import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlotSquaredEvents {

    private final boolean debugCompatibility = Main.instance.getConfigGestion().getDebug().get("Compatibility");

    public PlotSquaredEvents() {
        new PlotAPI().registerListener(this);
    }

    @Subscribe // NON TOCCARE è un Design Pattern
    public void onPlayerTrustedEvent(PlayerPlotTrustedEvent e) {// Controllare se il player è trastato al plot
        DebugUtils debug = new DebugUtils();
        long tempo = System.currentTimeMillis();
        if (!e.wasAdded()) // se uguale a false il player viene tolto dal plot
        {
            Player player = Bukkit.getPlayer(e.getPlayer());
            ArrayList<Integer> playerGenerators = CubeGeneratorAPI.getGeneratorsIdFromPlayer(player.getName());
            if (!playerGenerators.isEmpty()) {
                for (Integer generatorId : playerGenerators) {
                    CubeGeneratorAPI.disbandGenerator(generatorId, player, player.getName());
                }
            }
        }
        if (debugCompatibility) {
            debug.addLine("Compatibility execution time= " + (System.currentTimeMillis() - tempo));
            debug.debug("Compatibility");
        }
    }

    @Subscribe // NON TOCCARE è un Design Pattern
    public void onPlotClearedEvent(PlotClearEvent e) {
        DebugUtils debug = new DebugUtils();
        long tempo = System.currentTimeMillis();
        HashSet<UUID> members = e.getPlot().getMembers();
        Set<UUID> owners = e.getPlot().getOwners();
        HashSet<UUID> trusted = e.getPlot().getTrusted();
        for (UUID uid : members) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uid);
            ArrayList<Integer> playerGenerators = CubeGeneratorAPI.getGeneratorsIdFromPlayer(Bukkit.getOfflinePlayer(uid).getName());
            if (!playerGenerators.isEmpty()) {
                for (Integer generatorId : playerGenerators) {
                    CubeGeneratorAPI.disbandGenerator(generatorId, player.getPlayer(), player.getName());
                }
            }
        }
        for (UUID uidowners : owners) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uidowners);
            ArrayList<Integer> playerGenerators = CubeGeneratorAPI.getGeneratorsIdFromPlayer(Bukkit.getOfflinePlayer(uidowners).getName());
            if (!playerGenerators.isEmpty()) {
                for (Integer generatorId : playerGenerators) {
                    CubeGeneratorAPI.disbandGenerator(generatorId, player.getPlayer(), player.getName());
                }
            }
        }
        for (UUID uidtrusted : trusted) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uidtrusted);
            ArrayList<Integer> playerGenerators = CubeGeneratorAPI.getGeneratorsIdFromPlayer(Bukkit.getOfflinePlayer(uidtrusted).getName());
            if (!playerGenerators.isEmpty()) {
                for (Integer generatorId : playerGenerators) {
                    CubeGeneratorAPI.disbandGenerator(generatorId, player.getPlayer(), player.getName());
                }
            }
        }
        if (debugCompatibility) {
            debug.addLine("Compatibility execution time= " + (System.currentTimeMillis() - tempo));
            debug.debug("Compatibility");
        }
    }

    @Subscribe // NON TOCCARE è un Design Pattern
    public void onPlotDeleteEvent(PlotDeleteEvent e) {
        DebugUtils debug = new DebugUtils();
        long tempo = System.currentTimeMillis();
        HashSet<UUID> members = e.getPlot().getMembers();
        Set<UUID> owners = e.getPlot().getOwners();
        HashSet<UUID> trusted = e.getPlot().getTrusted();
        for (UUID uid : members) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uid);
            ArrayList<Integer> playerGenerators = CubeGeneratorAPI.getGeneratorsIdFromPlayer(Bukkit.getOfflinePlayer(uid).getName());
            if (!playerGenerators.isEmpty()) {
                for (Integer generatorId : playerGenerators) {
                    CubeGeneratorAPI.disbandGenerator(generatorId, player.getPlayer(), player.getName());
                }
            }
        }
        for (UUID uidowners : owners) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uidowners);
            ArrayList<Integer> playerGenerators = CubeGeneratorAPI.getGeneratorsIdFromPlayer(Bukkit.getOfflinePlayer(uidowners).getName());
            if (!playerGenerators.isEmpty()) {
                for (Integer generatorId : playerGenerators) {
                    CubeGeneratorAPI.disbandGenerator(generatorId, player.getPlayer(), player.getName());
                }
            }
        }
        for (UUID uidtrusted : trusted) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(uidtrusted);
            ArrayList<Integer> playerGenerators = CubeGeneratorAPI.getGeneratorsIdFromPlayer(Bukkit.getOfflinePlayer(uidtrusted).getName());
            if (!playerGenerators.isEmpty()) {
                for (Integer generatorId : playerGenerators) {
                    CubeGeneratorAPI.disbandGenerator(generatorId, player.getPlayer(), player.getName());
                }
            }
        }
        if (debugCompatibility) {
            debug.addLine("Compatibility execution time= " + (System.currentTimeMillis() - tempo));
            debug.debug("Compatibility");
        }
    }

    @Subscribe // NON TOCCARE è un Design Pattern
    public void onPlayerLeaveCommandEvent(PlayerPlotHelperEvent e) {
        DebugUtils debug = new DebugUtils();
        long tempo = System.currentTimeMillis();
        // se uguale a false il player viene tolto dal plot
        if (!e.wasAdded()) {
            Player player = Bukkit.getPlayer(e.getPlayer());
            ArrayList<Integer> playerGenerators = CubeGeneratorAPI.getGeneratorsIdFromPlayer(player.getName());
            if (!playerGenerators.isEmpty()) {
                for (Integer generatorId : playerGenerators) {
                    CubeGeneratorAPI.disbandGenerator(generatorId, player, player.getName());
                }
            }
        }
        if (debugCompatibility) {
            debug.addLine("Compatibility execution time= " + (System.currentTimeMillis() - tempo));
            debug.debug("Compatibility");
        }
    }

}
