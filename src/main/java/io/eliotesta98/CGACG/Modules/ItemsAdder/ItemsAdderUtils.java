package io.eliotesta98.CGACG.Modules.ItemsAdder;

import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomStack;
import io.eliotesta98.CGACG.Utils.ColorUtils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdderUtils {

    public static void placeBlock(String namespaceId, Location location) {
        CustomBlock customBlock = CustomBlock.getInstance(namespaceId);
        customBlock.place(location);
    }

    public static boolean removeBlock(Block block) {
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        customBlock.playBreakParticles();
        customBlock.playBreakEffect();
        customBlock.playBreakSound();
        return customBlock.remove();
    }

    public static boolean isMaterialCustom(String namespaceId) {
        return CustomBlock.isInRegistry(namespaceId);
    }

    public static boolean isItemCustom(String namespaceId) {
        if (namespaceId.contains(":")) {
            return CustomStack.isInRegistry(namespaceId);
        } else {
            List<String> items = searchItem(namespaceId);
            return !items.isEmpty();
        }
    }

    public static ItemStack getItemCustom(String namespaceId) {
        if (namespaceId.contains(":")) {
            return CustomStack.getInstance(namespaceId).getItemStack();
        } else {
            List<String> items = searchItem(namespaceId);
            if (items.isEmpty()) {
                return null;
            }
            return CustomStack.getInstance(items.get(0)).getItemStack();
        }
    }

    public static List<String> searchItem(String namespaceId) {
        String newNameSpaceId = ColorUtils.stripColor(namespaceId.toLowerCase().replace(" ", "_"));
        List<String> itemsFounded = new ArrayList<>();
        for (String item : CustomStack.getNamespacedIdsInRegistry()) {
            if (item.endsWith(":" + newNameSpaceId)) {
                itemsFounded.add(item);
            }
        }
        return itemsFounded;
    }

    public static boolean isCustomBlock(Block block) {
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        return customBlock != null;
    }

    public static String getNamespaceIdFromBlock(Block block) {
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        return "ITEMSADDER-" + customBlock.getNamespacedID().replace(":", "-").toUpperCase();
    }

    public static List<ItemStack> getItems(Block block, ItemStack itemInHand) {
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        return customBlock.getLoot(itemInHand, false);
    }

}
