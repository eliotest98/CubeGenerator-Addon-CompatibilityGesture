package io.eliotesta98.CGACG.Modules.RevEnchants;

import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CGACG.Utils.DebugUtils;
import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import me.revils.revenchants.events.JackHammerEvent;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class JHEvent implements Listener {

    private static final boolean debug = Main.instance.getConfigGestion().getDebug().get("Compatibility");
    private final DebugUtils debugUtils;

    public JHEvent() {
        debugUtils = new DebugUtils();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onJackHammer(JackHammerEvent event) throws Exception {
        final ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (debug) {
            debugUtils.addLine("RevEnchants Jack Hammer Event");
            debugUtils.addLine("Item Type used: " + itemInHand.getType());
            debugUtils.addLine("items Size:" + event.getBlocks().size());
            debugUtils.addLine("List of items:" + event.getBlocks().toString());
            debugUtils.addLine("Point 1:" + event.getPoint1());
            debugUtils.addLine("Point 2:" + event.getPoint2());
        }
        if (event.getPoint1().getBlockY() != event.getPoint2().getBlockY()) {
            throw new Exception("The two points must be on the same Y level");
        }

        if (!event.getBlocks().isEmpty()) {
            Block block = event.getBlocks().get(0);
            String isGenerator = CubeGeneratorAPI.isAGeneratorBlock(block);
            switch (isGenerator) {
                case "REMOVED_GENERATOR_BLOCK":
                case "REMOVED_FRAME":
                case "FRAME":
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block mined is a " + isGenerator);
                        debugUtils.addLine("Event cancelled");

                        debugUtils.addLine("");
                        debugUtils.debug("RevEnchants Break");
                    }
                    event.setCancelled(true);
                    break;
                case "GENERATOR_BLOCK":
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block mined is an internal block of a generator");
                    }
                    Location one = event.getPoint1();
                    Location two = event.getPoint2();

                    int minX = Math.min(one.getBlockX(), two.getBlockX());
                    int minZ = Math.min(one.getBlockZ(), two.getBlockZ());
                    int y = one.getBlockY();
                    int maxX = Math.max(one.getBlockX(), two.getBlockX());
                    int maxZ = Math.max(one.getBlockZ(), two.getBlockZ());
                    for (int x = minX; x <= maxX; x++) {
                        for (int z = minZ; z <= maxZ; z++) {
                            CubeGeneratorAPI.doBlockBreak(one.getWorld().getBlockAt(x, y, z), itemInHand, event.getPlayer().getName());
                        }
                    }
                    event.setCancelled(true);
                    event.setBlocks(new ArrayList<>());
                    break;
                default:
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block isn't a generator block: " + block.toString());

                        debugUtils.addLine("");
                        debugUtils.debug("RevEnchants Break");
                    }
                    break;
            }
        }
    }

}
