package io.eliotesta98.CGACG.Modules.Lands;

import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CGACG.Utils.DebugUtils;
import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import me.angeschossen.lands.api.events.*;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.LandArea;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.util.Collection;
import java.util.UUID;

public class LandsEvents implements Listener {

    private final boolean debugCompatibility = Main.instance.getConfigGestion().getDebug().get("Compatibility");

    // delete Land
    @EventHandler
    public void onLandDeleteEvent(LandDeleteEvent e) {
        DebugUtils debug = new DebugUtils();
        long tempo = System.currentTimeMillis();
        Land land = e.getLand();
        for (UUID p : land.getTrustedPlayers()) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(p);
            for (Integer generatorId : CubeGeneratorAPI.getGeneratorsIdFromPlayer(player.getName())) {
                CubeGeneratorAPI.disbandGenerator(generatorId, player.getPlayer(), player.getName());
            }
        }
        if (debugCompatibility) {
            debug.addLine("Compatibility execution time= " + (System.currentTimeMillis() - tempo));
            debug.debug("Compatibility");
        }
    }

    // untrust player
    @EventHandler
    public void onPlayerUntrustEvent(LandUntrustPlayerEvent e) {
        DebugUtils debug = new DebugUtils();
        long tempo = System.currentTimeMillis();
        OfflinePlayer player = Bukkit.getOfflinePlayer(e.getTargetUID());
        for (Integer generatorId : CubeGeneratorAPI.getGeneratorsIdFromPlayer(player.getName())) {
            CubeGeneratorAPI.disbandGenerator(generatorId, player.getPlayer(), player.getName());
        }
        if (debugCompatibility) {
            debug.addLine("Compatibility execution time= " + (System.currentTimeMillis() - tempo));
            debug.debug("Compatibility");
        }
    }

    // leave player
    @EventHandler
    public void onPlayerLeaveEvent(PlayerLeaveLandEvent e) {
        DebugUtils debug = new DebugUtils();
        long tempo = System.currentTimeMillis();
        OfflinePlayer player = Bukkit.getOfflinePlayer(e.getPlayerUID());
        for (Integer generatorId : CubeGeneratorAPI.getGeneratorsIdFromPlayer(player.getName())) {
            CubeGeneratorAPI.disbandGenerator(generatorId, player.getPlayer(), player.getName());
        }
        if (debugCompatibility) {
            debug.addLine("Compatibility execution time= " + (System.currentTimeMillis() - tempo));
            debug.debug("Compatibility");
        }
    }

    // delete chunk
    @EventHandler
    public void onChunkDeleteEvent(ChunkDeleteEvent e) {
        DebugUtils debug = new DebugUtils();
        long tempo = System.currentTimeMillis();
        Collection<? extends LandArea> areas = e.getLand().getSubAreas(e.getWorld());
        for (LandArea area : areas) {
            for (UUID p : area.getTrustedPlayers()) {
                OfflinePlayer player = Bukkit.getOfflinePlayer(p);
                for (Integer generatorId : CubeGeneratorAPI.getGeneratorsIdFromPlayer(player.getName())) {
                    CubeGeneratorAPI.disbandGenerator(generatorId, player.getPlayer(), player.getName());
                }
            }
        }
        if (debugCompatibility) {
            debug.addLine("Compatibility execution time= " + (System.currentTimeMillis() - tempo));
            debug.debug("Compatibility");
        }
    }

    // ban player
    @EventHandler
    public void onPlayerBannedLand(LandBanPlayerEvent e) {
        DebugUtils debug = new DebugUtils();
        long tempo = System.currentTimeMillis();
        OfflinePlayer player = Bukkit.getOfflinePlayer(e.getTargetUID());
        for (Integer generatorId : CubeGeneratorAPI.getGeneratorsIdFromPlayer(player.getName())) {
            CubeGeneratorAPI.disbandGenerator(generatorId, player.getPlayer(), player.getName());
        }
        if (debugCompatibility) {
            debug.addLine("Compatibility execution time= " + (System.currentTimeMillis() - tempo));
            debug.debug("Compatibility");
        }
    }

}
