package io.eliotesta98.CGACG.Core;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;

public class ConfigGestion {

    private HashMap<String, Boolean> hooks = new HashMap<>();
    private HashMap<String, Boolean> debug = new HashMap<>();
    private ArrayList<String> regionFlags = new ArrayList<>();
    private boolean reciveDropsInInventory;

    public ConfigGestion(FileConfiguration file) {

        for (String event : file.getConfigurationSection("Debug").getKeys(false)) {
            debug.put(event, file.getBoolean("Debug." + event));
        }

        for (String hook : file.getConfigurationSection("Configuration.Hooks").getKeys(false)) {
            if (hook.equalsIgnoreCase("RevEnchants")) {
                hooks.put(hook, file.getBoolean("Configuration.Hooks." + hook + ".Enabled"));
                regionFlags.addAll(file.getStringList("Configuration.Hooks." + hook + ".RegionFlags"));
                reciveDropsInInventory = file.getBoolean("Configuration.Hooks." + hook + ".ReciveDropsInInventory");
            } else {
                hooks.put(hook, file.getBoolean("Configuration.Hooks." + hook));
            }
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

    public ArrayList<String> getRegionFlags() {
        return regionFlags;
    }

    public void setRegionFlags(ArrayList<String> regionFlags) {
        this.regionFlags = regionFlags;
    }

    public boolean isReciveDropsInInventory() {
        return reciveDropsInInventory;
    }

    public void setReciveDropsInInventory(boolean reciveDropsInInventory) {
        this.reciveDropsInInventory = reciveDropsInInventory;
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
