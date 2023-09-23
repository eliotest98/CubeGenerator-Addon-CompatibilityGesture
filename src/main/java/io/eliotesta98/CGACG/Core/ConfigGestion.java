package io.eliotesta98.CGACG.Core;

import org.bukkit.configuration.file.FileConfiguration;
import java.util.HashMap;

public class ConfigGestion {

    private HashMap<String, Boolean> hooks = new HashMap<>();
    private HashMap<String, Boolean> debug = new HashMap<>();

    public ConfigGestion(FileConfiguration file) {

        for (String event : file.getConfigurationSection("Debug").getKeys(false)) {
            debug.put(event, file.getBoolean("Debug." + event));
        }

        for (String hook : file.getConfigurationSection("Configuration.Hooks").getKeys(false)) {
            hooks.put(hook, file.getBoolean("Configuration.Hooks." + hook));
        }

    }

    public HashMap<String, Boolean> getHooks() {
        return hooks;
    }

    public void setHooks(HashMap<String, Boolean> hooks) {
        this.hooks = hooks;
    }

    public HashMap<String, Boolean> getDebug() {
        return debug;
    }

    public void setDebug(HashMap<String, Boolean> debug) {
        this.debug = debug;
    }

    @Override
    public String toString() {
        return "ConfigGestion{" +
                "hooks=" + hooks +
                ", debug=" + debug +
                '}';
    }
}
