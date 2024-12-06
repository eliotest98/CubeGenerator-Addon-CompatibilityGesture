package io.eliotesta98.CGACG.Modules.Lands;

import me.angeschossen.lands.api.LandsIntegration;
import org.bukkit.plugin.java.JavaPlugin;

public class LandsUtils {

    private LandsIntegration landsIntegration;

    public void setLandsIntegration(JavaPlugin plugin) {
        this.landsIntegration = LandsIntegration.of(plugin);
    }

    public LandsIntegration getLandsIntegration() {
        return landsIntegration;
    }

}
