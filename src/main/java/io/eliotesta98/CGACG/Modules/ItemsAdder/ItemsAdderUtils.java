package io.eliotesta98.CGACG.Modules.ItemsAdder;

import dev.lone.itemsadder.api.CustomBlock;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemsAdderUtils {

    public static void placeBlock(String namespaceId, Location location) {
        CustomBlock customBlock = CustomBlock.getInstance(namespaceId);
        customBlock.place(location);
    }

    public static boolean isMaterialCustom(String namespaceId) {
        return CustomBlock.isInRegistry(namespaceId);
    }

    public static boolean isCustomBlock(Block block) {
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        return customBlock != null;
    }

    public static List<ItemStack> getItems(Block block, ItemStack itemInHand) {
        CustomBlock customBlock = CustomBlock.byAlreadyPlaced(block);
        return customBlock.getLoot(itemInHand, false);
    }

}
