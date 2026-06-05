package io.eliotesta98.CGACG.Modules.HeroxLuckyBlock;

import com.heroxwar.luckyblock.api.LuckyBlockAPI;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import static org.bukkit.Bukkit.getServer;

public class LuckyBlockUtils {

    private LuckyBlockAPI luckyBlock;

    public LuckyBlockUtils() {
        final RegisteredServiceProvider<LuckyBlockAPI> luckyBlockProvider = getServer().getServicesManager().getRegistration(LuckyBlockAPI.class);
        if (luckyBlockProvider != null) this.luckyBlock = (LuckyBlockAPI) luckyBlockProvider.getProvider();
    }

    public boolean openLuckyBlock(Block block, Player player) {
        return this.luckyBlock.getLuckyBlockSupplier().open(player, block);
    }

}
