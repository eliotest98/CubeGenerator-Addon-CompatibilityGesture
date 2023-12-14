package io.eliotesta98.CGACG.Modules.RevEnchants;

import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CGACG.Utils.DebugUtils;
import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import me.revils.revenchants.events.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class OtherEvents implements Listener {
    private static final boolean debug = Main.instance.getConfigGestion().getDebug().get("Compatibility");
    private final DebugUtils debugUtils;

    public OtherEvents() {
        debugUtils = new DebugUtils();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onExplode(ExplosiveEvent event) {
        final ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (debug) {
            debugUtils.addLine(event.getEventName() + " Event");
            debugUtils.addLine("Item Type used: " + itemInHand.getType());
            debugUtils.addLine("Number of blocks breaked: " + event.getBlocks().size());
        }
        boolean genFound = false;
        ArrayList<Block> blocks = new ArrayList<>();
        for (Block block : event.getBlocks()) {
            if (event.isCancelled()) {
                blocks = new ArrayList<>();
                break; // stop the loop
            }

            if (debug) {
                debugUtils.addLine("Block Mined at location: " + block.getLocation());
            }
            String isGenerator = CubeGeneratorAPI.isAGeneratorBlock(block);
            switch (isGenerator) {
                case "REMOVED_GENERATOR_BLOCK":
                case "REMOVED_FRAME":
                case "FRAME":
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block mined is a " + isGenerator);
                        debugUtils.addLine("Event cancelled");
                    }
                    event.setCancelled(true);
                    break;
                case "GENERATOR_BLOCK":
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block mined is an internal block of a generator");
                    }
                    CubeGeneratorAPI.doBlockBreak(block, itemInHand, event.getPlayer().getName());
                    genFound = true;
                    break;
                default:
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block isn't a generator block");
                    }
                    blocks.add(block);
                    break;
            }
        }
        if (!event.isCancelled()) {
            event.setCancelled(genFound);
        }
        event.setBlocks(blocks);
        if (debug) {
            debugUtils.addLine("");
            debugUtils.debug("RevEnchants Break " + event.getEventName());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onMeteorEvent(MeteorShowerEvent event) {
        final ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (debug) {
            debugUtils.addLine(event.getEventName() + " Event");
            debugUtils.addLine("Item Type used: " + itemInHand.getType());
            debugUtils.addLine("Number of blocks breaked: " + event.getBlocks().size());
        }
        boolean genFound = false;
        ArrayList<Block> blocks = new ArrayList<>();
        for (Block block : event.getBlocks()) {
            if (event.isCancelled()) {
                blocks = new ArrayList<>();
                break; // stop the loop
            }

            if (debug) {
                debugUtils.addLine("Block Mined at location: " + block.getLocation());
            }
            String isGenerator = CubeGeneratorAPI.isAGeneratorBlock(block);
            switch (isGenerator) {
                case "REMOVED_GENERATOR_BLOCK":
                case "REMOVED_FRAME":
                case "FRAME":
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block mined is a " + isGenerator);
                        debugUtils.addLine("Event cancelled");
                    }
                    event.setCancelled(true);
                    break;
                case "GENERATOR_BLOCK":
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block mined is an internal block of a generator " + block);
                    }
                    CubeGeneratorAPI.doBlockBreak(block, itemInHand, event.getPlayer().getName());
                    genFound = true;
                    break;
                default:
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block isn't a generator block");
                    }
                    blocks.add(block);
                    break;
            }
        }
        if (!event.isCancelled()) {
            event.setCancelled(genFound);
        }
        event.setBlocks(blocks);
        if (debug) {
            debugUtils.addLine("");
            debugUtils.debug("RevEnchants Break " + event.getEventName());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onFireballEvent(FireballEvent event) {
        final ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (debug) {
            debugUtils.addLine(event.getEventName() + " Event");
            debugUtils.addLine("Item Type used: " + itemInHand.getType());
            debugUtils.addLine("Number of blocks breaked: " + event.getBlocks().size());
        }
        boolean genFound = false;
        ArrayList<Block> blocks = new ArrayList<>();
        for (Block block : event.getBlocks()) {
            if (event.isCancelled()) {
                blocks = new ArrayList<>();
                break; // stop the loop
            }

            if (debug) {
                debugUtils.addLine("Block Mined at location: " + block.getLocation());
            }
            String isGenerator = CubeGeneratorAPI.isAGeneratorBlock(block);
            switch (isGenerator) {
                case "REMOVED_GENERATOR_BLOCK":
                case "REMOVED_FRAME":
                case "FRAME":
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block mined is a " + isGenerator);
                        debugUtils.addLine("Event cancelled");
                    }
                    event.setCancelled(true);
                    break;
                case "GENERATOR_BLOCK":
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block mined is an internal block of a generator");
                    }
                    CubeGeneratorAPI.doBlockBreak(block, itemInHand, event.getPlayer().getName());
                    genFound = true;
                    break;
                default:
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block isn't a generator block");
                    }
                    blocks.add(block);
                    break;
            }
        }
        if (!event.isCancelled()) {
            event.setCancelled(genFound);
        }
        event.setBlocks(blocks);
        if (debug) {
            debugUtils.addLine("");
            debugUtils.debug("RevEnchants Break " + event.getEventName());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onLaserEvent(LaserEvent event) {
        final ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (debug) {
            debugUtils.addLine(event.getEventName() + " Event");
            debugUtils.addLine("Item Type used: " + itemInHand.getType());
            debugUtils.addLine("Number of blocks breaked: " + event.getBlocks().size());
        }
        boolean genFound = false;
        ArrayList<Block> blocks = new ArrayList<>();
        for (Block block : event.getBlocks()) {
            if (event.isCancelled()) {
                blocks = new ArrayList<>();
                break; // stop the loop
            }

            if (debug) {
                debugUtils.addLine("Block Mined at location: " + block.getLocation());
            }
            String isGenerator = CubeGeneratorAPI.isAGeneratorBlock(block);
            switch (isGenerator) {
                case "REMOVED_GENERATOR_BLOCK":
                case "REMOVED_FRAME":
                case "FRAME":
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block mined is a " + isGenerator);
                        debugUtils.addLine("Event cancelled");
                    }
                    event.setCancelled(true);
                    break;
                case "GENERATOR_BLOCK":
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block mined is an internal block of a generator");
                    }
                    CubeGeneratorAPI.doBlockBreak(block, itemInHand, event.getPlayer().getName());
                    genFound = true;
                    break;
                default:
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block isn't a generator block");
                    }
                    blocks.add(block);
                    break;
            }
        }
        if (!event.isCancelled()) {
            event.setCancelled(genFound);
        }
        event.setBlocks(blocks);
        if (debug) {
            debugUtils.addLine("");
            debugUtils.debug("RevEnchants Break " + event.getEventName());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onThorEvent(ThorHammerEvent event) {
        final ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (debug) {
            debugUtils.addLine(event.getEventName() + " Event");
            debugUtils.addLine("Item Type used: " + itemInHand.getType());
            debugUtils.addLine("Number of blocks breaked: " + event.getBlocks().size());
        }
        boolean genFound = false;
        ArrayList<Block> blocks = new ArrayList<>();
        for (Block block : event.getBlocks()) {
            if (event.isCancelled()) {
                blocks = new ArrayList<>();
                break; // stop the loop
            }

            if (debug) {
                debugUtils.addLine("Block Mined at location: " + block.getLocation());
            }
            String isGenerator = CubeGeneratorAPI.isAGeneratorBlock(block);
            switch (isGenerator) {
                case "REMOVED_GENERATOR_BLOCK":
                case "REMOVED_FRAME":
                case "FRAME":
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block mined is a " + isGenerator);
                        debugUtils.addLine("Event cancelled");
                    }
                    event.setCancelled(true);
                    break;
                case "GENERATOR_BLOCK":
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block mined is an internal block of a generator");
                    }
                    CubeGeneratorAPI.doBlockBreak(block, itemInHand, event.getPlayer().getName());
                    genFound = true;
                    break;
                default:
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block isn't a generator block");
                    }
                    blocks.add(block);
                    break;
            }
        }
        if (!event.isCancelled()) {
            event.setCancelled(genFound);
        }
        event.setBlocks(blocks);
        if (debug) {
            debugUtils.addLine("");
            debugUtils.debug("RevEnchants Break " + event.getEventName());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBigBangEvent(BigBangEvent event) {
        final ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (debug) {
            debugUtils.addLine(event.getEventName() + " Event");
            debugUtils.addLine("Item Type used: " + itemInHand.getType());
            debugUtils.addLine("Number of blocks breaked: " + event.getBlocks().size());
        }
        boolean genFound = false;
        ArrayList<Block> blocks = new ArrayList<>();
        for (Block block : event.getBlocks()) {
            if (event.isCancelled()) {
                blocks = new ArrayList<>();
                break; // stop the loop
            }

            if (debug) {
                debugUtils.addLine("Block Mined at location: " + block.getLocation());
            }
            String isGenerator = CubeGeneratorAPI.isAGeneratorBlock(block);
            switch (isGenerator) {
                case "REMOVED_GENERATOR_BLOCK":
                case "REMOVED_FRAME":
                case "FRAME":
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block mined is a " + isGenerator);
                        debugUtils.addLine("Event cancelled");
                    }
                    event.setCancelled(true);
                    break;
                case "GENERATOR_BLOCK":
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block mined is an internal block of a generator");
                    }
                    CubeGeneratorAPI.doBlockBreak(block, itemInHand, event.getPlayer().getName());
                    genFound = true;
                    break;
                default:
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block isn't a generator block");
                    }
                    blocks.add(block);
                    break;
            }
        }
        if (!event.isCancelled()) {
            event.setCancelled(genFound);
        }
        event.setBlocks(blocks);
        if (debug) {
            debugUtils.addLine("");
            debugUtils.debug("RevEnchants Break " + event.getEventName());
        }
    }
}
