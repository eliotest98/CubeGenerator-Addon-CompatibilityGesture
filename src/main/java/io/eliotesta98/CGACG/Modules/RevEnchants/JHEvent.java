package io.eliotesta98.CGACG.Modules.RevEnchants;

import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CGACG.Modules.CubeGenerator.CubeGeneratorUtils;
import io.eliotesta98.CGACG.Utils.DebugUtils;
import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import me.revils.revenchants.events.JackHammerEvent;
import org.bukkit.Material;
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
    public void onJackHammer(JackHammerEvent event) {
        final ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (debug) {
            debugUtils.addLine("RevEnchants Jack Hammer Event");
            debugUtils.addLine("Item Type used: " + itemInHand.getType());
            debugUtils.addLine("items Size:" + event.getBlocks().size());
            debugUtils.addLine("List of items:" + event.getBlocks().toString());
        }
        ArrayList<Block> blocks = new ArrayList<>();
        for (Object onj : event.getBlocks()) {
            Block block = (Block) onj;
            if (debug) {
                debugUtils.addLine("Block Mined at location: " + block.getLocation());
            }
            if (block.getType() != Material.AIR) {
                String isGenerator = CubeGeneratorUtils.isGeneratorBlock(block);
                switch (isGenerator) {
                    case "REMOVED_GENERATOR_BLOCK":
                    case "REMOVED_FRAME":
                    case "FRAME":
                        if (debug) {
                            debugUtils.addLine("");
                            debugUtils.addLine("The block mined is a " + isGenerator);
                            debugUtils.addLine("Event cancelled");
                        }
                        continue;
                    case "GENERATOR_BLOCK":
                        if (debug) {
                            debugUtils.addLine("");
                            debugUtils.addLine("The block mined is an internal block of a generator");
                        }
                        if (!CubeGeneratorAPI.doBlockBreak(block, itemInHand, event.getPlayer().getName())) {
                            if (debug) {
                                debugUtils.addLine("");
                                debugUtils.addLine("Event cancelled from doBlockBreak api of CubeGenerator");
                            }
                        }
                        continue;
                    default:
                        if (debug) {
                            debugUtils.addLine("");
                            debugUtils.addLine("The block isn't a generator block");
                        }
                        blocks.add(block);
                        continue;
                }
            }
        }
        event.setBlocks(blocks);
        if (debug) {
            debugUtils.addLine("");
            debugUtils.debug("RevEnchants Break");
        }
    }

}
