package io.eliotesta98.CGACG.Modules.CubeGenerator;

import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class CubeGeneratorUtils {

    public static String isGeneratorBlock(Block block) {
        return CubeGeneratorAPI.isAGeneratorBlock(block);
    }

    public static boolean doBlockBreak(Block block, ItemStack itemInHand, String playerName) {
        return CubeGeneratorAPI.doBlockBreak(block, itemInHand, playerName);
    }

}
