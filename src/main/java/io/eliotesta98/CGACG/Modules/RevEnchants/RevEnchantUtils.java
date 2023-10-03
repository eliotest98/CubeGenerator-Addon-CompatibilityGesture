package io.eliotesta98.CGACG.Modules.RevEnchants;

import me.revils.revenchants.api.RevEnchantsApi;
import me.revils.revenchants.rev.RevTool;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class RevEnchantUtils {

    private static final Random random = new Random();

    public static boolean isRevTool(ItemStack itemStack) {
        return RevEnchantsApi.isTool(itemStack);
    }

    public static RevTool getRevTool(ItemStack itemStack) {
        return new RevTool(itemStack);
    }

    public static long getEnchantLevel(RevTool revTool, String enchant) {
        return revTool.getEnchant(enchant);
    }

    public static double chance(String enchant, long enchantLevel) {
        return RevEnchantsApi.getEnchantsYml(enchant).getDouble("Settings.Chance", 0)
                + (enchantLevel * RevEnchantsApi.getEnchantsYml(enchant).getDouble("Settings.Increase-Chance-by", 0));
    }

    public static boolean give(double chance) {
        int number = random.nextInt(100);
        return number >= chance;
    }

}
