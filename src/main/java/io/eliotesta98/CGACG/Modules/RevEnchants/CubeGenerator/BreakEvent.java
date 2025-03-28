package io.eliotesta98.CGACG.Modules.RevEnchants.CubeGenerator;

import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CGACG.Modules.RevEnchants.RevEnchantUtils;
import io.eliotesta98.CGACG.Utils.DebugUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class BreakEvent implements Listener {

    private final DebugUtils debugUtils;
    private static final boolean debug = Main.instance.getConfigGestion().getDebug().get("Compatibility");

    public BreakEvent() {
        debugUtils = new DebugUtils();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onCubeBlockBreak(io.eliotesta98.CubeGenerator.Events.ApiEvents.BreakEvent event) {
        if (debug) {
            debugUtils.addLine("CubeBlockBreakEvent");
        }
        if (RevEnchantUtils.isRevTool(event.getItemInHand())) {
            if (debug) {
                debugUtils.addLine("Item in hand is a RevTool pickaxe");
            }
            long unbreakingLevel = RevEnchantUtils.getEnchantLevel(RevEnchantUtils.getRevTool(event.getItemInHand()), "Unbreaking");
            if (unbreakingLevel > 0) {
                try {
                    if (RevEnchantUtils.give(RevEnchantUtils.chance("Unbreaking", unbreakingLevel))) {
                        event.getItemInHand().addUnsafeEnchantment(Enchantment.UNBREAKING, (int) unbreakingLevel);
                    } else {
                        event.getItemInHand().removeEnchantment(Enchantment.UNBREAKING);
                    }
                } catch (NoSuchFieldError error) {
                    if (RevEnchantUtils.give(RevEnchantUtils.chance("Unbreaking", unbreakingLevel))) {
                        event.getItemInHand().addUnsafeEnchantment(Enchantment.getByName("DURABILITY"), (int) unbreakingLevel);
                    } else {
                        event.getItemInHand().removeEnchantment(Enchantment.getByName("DURABILITY"));
                    }
                }
            }
            if (debug) {
                debugUtils.addLine("Unbreaking level: " + unbreakingLevel);
            }
        }

        if (debug) {
            debugUtils.debug("CubeBlockBreakEvent");
        }
    }

}
