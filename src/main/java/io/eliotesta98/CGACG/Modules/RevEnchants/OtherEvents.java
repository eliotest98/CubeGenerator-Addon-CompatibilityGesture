package io.eliotesta98.CGACG.Modules.RevEnchants;

import io.eliotesta98.CGACG.Core.Main;
import io.eliotesta98.CGACG.Modules.CubeGenerator.CubeGeneratorUtils;
import io.eliotesta98.CGACG.Modules.RevBackpack.RevBackpackUtils;
import io.eliotesta98.CGACG.Utils.DebugUtils;
import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import me.revils.revenchants.events.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class OtherEvents implements Listener {

    private static final boolean debug = Main.instance.getConfigGestion().getDebug().get("Compatibility");
    private final boolean reciveDropsInInventory = Main.instance.getConfigGestion().isReciveDropsInInventory();
    private final boolean revBackpackEnabled = Main.instance.getConfigGestion().getHooks().get("RevBackPack");
    private final DebugUtils debugUtils;

    public OtherEvents() {
        debugUtils = new DebugUtils();
    }

    private final HashMap<String, Integer> generators = new HashMap<>();

    @EventHandler(priority = EventPriority.NORMAL)
    public void onExplode(ExplosiveEvent event) {
        final ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (debug) {
            debugUtils.addLine("RevEnchants Thor Hammer Event");
            debugUtils.addLine("Item Type used: " + itemInHand.getType());
            debugUtils.addLine("Number of blocks breaked: " + event.getBlocks().size());
        }

        for (Object onj : event.getBlocks()) {
            Block block = (Block) onj;
            if (debug) {
                debugUtils.addLine("Block Mined at location: " + block.getLocation());
            }
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
                    int id = CubeGeneratorAPI.getGeneratorIdFromLocation(block.getLocation());
                    CubeGeneratorAPI.setRandomGeneratorBlock(id, block.getLocation(), true, -1, true, false);
                    generators.putIfAbsent(event.getPlayer().getName(), id);
                    break;
                default:
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block isn't a generator block");
                    }
                    break;
            }
        }
        if (debug) {
            debugUtils.addLine("");
            debugUtils.debug("RevEnchants Break");
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onMeteorEvent(MeteorShowerEvent event) {
        final ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (debug) {
            debugUtils.addLine("RevEnchants Thor Hammer Event");
            debugUtils.addLine("Item Type used: " + itemInHand.getType());
            debugUtils.addLine("Number of blocks breaked: " + event.getBlocks().size());
        }

        for (Object onj : event.getBlocks()) {
            Block block = (Block) onj;
            if (debug) {
                debugUtils.addLine("Block Mined at location: " + block.getLocation());
            }
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
                    int id = CubeGeneratorAPI.getGeneratorIdFromLocation(block.getLocation());
                    CubeGeneratorAPI.setRandomGeneratorBlock(id, block.getLocation(), true, -1, true, false);
                    generators.putIfAbsent(event.getPlayer().getName(), id);
                    break;
                default:
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block isn't a generator block");
                    }
                    break;
            }
        }
        if (debug) {
            debugUtils.addLine("");
            debugUtils.debug("RevEnchants Break");
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onLaserEvent(LaserEvent event) {
        final ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (debug) {
            debugUtils.addLine("RevEnchants Thor Hammer Event");
            debugUtils.addLine("Item Type used: " + itemInHand.getType());
            debugUtils.addLine("Number of blocks breaked: " + event.getBlocks().size());
        }

        for (Object onj : event.getBlocks()) {
            Block block = (Block) onj;
            if (debug) {
                debugUtils.addLine("Block Mined at location: " + block.getLocation());
            }
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
                    int id = CubeGeneratorAPI.getGeneratorIdFromLocation(block.getLocation());
                    CubeGeneratorAPI.setRandomGeneratorBlock(id, block.getLocation(), true, -1, true, false);
                    generators.putIfAbsent(event.getPlayer().getName(), id);
                    break;
                default:
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block isn't a generator block");
                    }
                    break;
            }
        }
        if (debug) {
            debugUtils.addLine("");
            debugUtils.debug("RevEnchants Break");
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onThorEvent(ThorHammerEvent event) {
        final ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (debug) {
            debugUtils.addLine("RevEnchants Thor Hammer Event");
            debugUtils.addLine("Item Type used: " + itemInHand.getType());
            debugUtils.addLine("Number of blocks breaked: " + event.getBlocks().size());
        }

        for (Object onj : event.getBlocks()) {
            Block block = (Block) onj;
            if (debug) {
                debugUtils.addLine("Block Mined at location: " + block.getLocation());
            }
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
                    int id = CubeGeneratorAPI.getGeneratorIdFromLocation(block.getLocation());
                    CubeGeneratorAPI.setRandomGeneratorBlock(id, block.getLocation(), true, -1, true, false);
                    generators.putIfAbsent(event.getPlayer().getName(), id);
                    break;
                default:
                    if (debug) {
                        debugUtils.addLine("");
                        debugUtils.addLine("The block isn't a generator block");
                    }
                    break;
            }
        }
        if (debug) {
            debugUtils.addLine("");
            debugUtils.debug("RevEnchants Break");
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDropInInventoryEvent(ReceiveDropsEvent event) {
        if (reciveDropsInInventory) {
            if (revBackpackEnabled) {
                RevBackpackUtils.receiveDropEventGesture(event.getPlayer(), event);
                if (debug) {
                    debugUtils.addLine("");
                    debugUtils.addLine("The block mined goes to the RevBackpack, maybe ...");
                }
            }

            if (debug) {
                debugUtils.addLine("");
                debugUtils.addLine("The block mined was ignored...");
                debugUtils.addLine("");
                debugUtils.debug("ReceiveDropsEvent Break");
            }
            return;
        }
        if (generators.containsKey(event.getPlayer().getName())) {
            int generatorId = generators.get(event.getPlayer().getName());
            generators.remove(event.getPlayer().getName());
            for (Object itemStack : event.getDrops().entrySet()) {
                Map.Entry<?, ?> bho = (Map.Entry<?, ?>) itemStack;
                ItemStack itemToGive = (ItemStack) bho.getKey();
                for (int i = 0; i < (long) bho.getValue(); i++) {
                    CubeGeneratorAPI.addBlockToInventory(event.getPlayer(), itemToGive, generatorId);
                }
            }
            if (debug) {
                debugUtils.addLine("");
                debugUtils.addLine("The block mined is an internal block of a generator");
                debugUtils.addLine("");
                debugUtils.addLine("Generators: " + generators.toString());

                debugUtils.addLine("");
                debugUtils.debug("ReceiveDropsEvent Break");
            }
            event.setCancelled(true);
            return;
        }
        if (debug) {
            debugUtils.addLine("");
            debugUtils.addLine("The block mined is not a generator");

            debugUtils.addLine("");
            debugUtils.debug("ReceiveDropsEvent Break");
        }
    }

}
