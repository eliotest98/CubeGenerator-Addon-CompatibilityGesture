package io.eliotesta98.CGACG.Modules.McMMO;

import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CGACG.Utils.DebugUtils;
import io.eliotesta98.CubeGenerator.Events.ApiEvents.BreakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class GeneratorEvents implements Listener {

    private static final boolean giveExperience = Main.instance.getConfigGestion().isGiveExperience();
    private static final boolean debug = Main.instance.getConfigGestion().getDebug().get("Compatibility");
    private final DebugUtils debugUtils;

    public GeneratorEvents() {
        debugUtils = new DebugUtils();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BreakEvent event) {
        if (debug) {
            debugUtils.addLine("BreakEvent");
        }
        if (giveExperience) {
            if (debug) {
                debugUtils.addLine("Give Experience is active");
            }
            boolean result = McMMoUtils.removeState(event.getBreaker(), event.getBlockBreaked());
            if (!result) {
                debugUtils.addLine("Event Cancelled for result");
                event.setCancelled(true);
            }
        } else {
            debugUtils.addLine("Give Experience is disabled");
            McMMoUtils.setBlockState(event.getBlockBreaked());
        }
        if (debug) {
            debugUtils.debug("Compatibility");
        }
    }

}
