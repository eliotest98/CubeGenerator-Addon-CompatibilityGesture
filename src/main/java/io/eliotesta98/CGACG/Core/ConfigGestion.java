package io.eliotesta98.CGACG.Core;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigGestion {

    private final Map<String, Boolean> hooks = new HashMap<>();
    private Map<String, Boolean> debug = new HashMap<>();
    private final Map<String, String> messages = new HashMap<>();
    private final List<String> regionFlags = new ArrayList<>();
    private final List<String> excludedLands = new ArrayList<>();
    private boolean dropsLikeRevEnchantsMine;
    private final String prefix;

    public ConfigGestion(FileConfiguration file) {

        for (String event : file.getConfigurationSection("Debug").getKeys(false)) {
            debug.put(event, file.getBoolean("Debug." + event));
        }

        for (String hook : file.getConfigurationSection("Configuration.Hooks").getKeys(false)) {
            if (hook.equalsIgnoreCase("RevEnchants")) {
                hooks.put(hook, file.getBoolean("Configuration.Hooks." + hook + ".Enabled"));
                regionFlags.addAll(file.getStringList("Configuration.Hooks." + hook + ".RegionFlags"));
                dropsLikeRevEnchantsMine = file.getBoolean("Configuration.Hooks." + hook + ".DropsLikeRevEnchantsMine");
            } else if (hook.equalsIgnoreCase("Lands")) {
                hooks.put(hook, file.getBoolean("Configuration.Hooks." + hook + ".Enabled"));
                excludedLands.addAll(file.getStringList("Configuration.Hooks." + hook + ".ExcludedLands"));
            } else {
                hooks.put(hook, file.getBoolean("Configuration.Hooks." + hook));
            }
        }
        prefix = file.getString("Messages.Prefix", "");
        messages.putAll(recursiveMessages(file, file.getConfigurationSection("Messages")));
    }

    public Map<String, Boolean> getHooks() {
        return hooks;
    }

    public Map<String, Boolean> getDebug() {
        return debug;
    }

    public void setDebug(HashMap<String, Boolean> debug) {
        this.debug = debug;
    }

    public List<String> getRegionFlags() {
        return regionFlags;
    }

    public boolean isDropsLikeRevEnchantsMine() {
        return dropsLikeRevEnchantsMine;
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public List<String> getExcludedLands() {
        return excludedLands;
    }

    public Map<String, String> recursiveMessages(FileConfiguration file, ConfigurationSection messages) {
        Map<String, String> messagesFinal = new HashMap<>();
        for (String message : messages.getKeys(true)) {
            if (file.isConfigurationSection("Messages." + message)) {
                ConfigurationSection section = file.getConfigurationSection(message);
                if (section != null) {
                    messagesFinal.putAll(recursiveMessages(file, section));
                }
            } else {
                messagesFinal.put(message, file.getString("Messages." + message, "&c&l{prefix} ERROR").replace("{prefix}", prefix));
            }
        }
        return messagesFinal;
    }

    @Override
    public String toString() {
        return "ConfigGestion{" +
                "hooks=" + hooks +
                ", debug=" + debug +
                ", regionFlags=" + regionFlags +
                '}';
    }
}
