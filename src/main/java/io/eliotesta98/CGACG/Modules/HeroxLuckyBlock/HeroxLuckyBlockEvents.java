package io.eliotesta98.CGACG.Modules.HeroxLuckyBlock;

import com.heroxwar.luckyblock.api.LuckyBlockAPI;
import com.heroxwar.luckyblock.api.events.LuckyBlockOpenEvent;
import io.eliotesta98.CubeGenerator.api.CubeGeneratorAPI;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;

import static org.bukkit.Bukkit.getServer;

public class HeroxLuckyBlockEvents implements Listener {

    private LuckyBlockAPI luckyBlock;

    public HeroxLuckyBlockEvents() {
        final RegisteredServiceProvider<LuckyBlockAPI> luckyBlockProvider = getServer().getServicesManager().getRegistration(LuckyBlockAPI.class);
        if (luckyBlockProvider != null) this.luckyBlock = (LuckyBlockAPI) luckyBlockProvider.getProvider();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onOpenLuckBlock(LuckyBlockOpenEvent event) {
        Block block = event.getBlock();
        String isGenerator = CubeGeneratorAPI.isAGeneratorBlock(block);
        switch (isGenerator) {
            case "REMOVED_GENERATOR_BLOCK":
            case "REMOVED_FRAME":
            case "FRAME":
                event.setCancelled(true);
                break;
            case "GENERATOR_BLOCK":
                int generatorID = CubeGeneratorAPI.getGeneratorIdFromLocation(block.getLocation());
                CubeGeneratorAPI.setRandomGeneratorBlock(
                        generatorID,
                        block.getLocation(),
                        true,
                        -1L,
                        true,
                        true);
                break;
            default:
                break;
        }
    }

}
