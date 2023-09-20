package io.eliotesta98.CGACG.Core;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;

public class ConfigGestion {

    private HashMap<String, Boolean> hooks = new HashMap<>();
    private HashMap<String, String> messages = new HashMap<>();
    private HashMap<String, Boolean> debug = new HashMap<>();
    private boolean showDimensionInConsole, showBackupInConsole, rewardEnabled;
    private String wallMaterial, groundMaterial, endLocationMaterial, resolveMaterial;
    private int backupTimer, velocityPlace;
    private ArrayList<String> rewards = new ArrayList<>();

    public ConfigGestion(FileConfiguration file) {

        for (String event : file.getConfigurationSection("Debug").getKeys(false)) {
            debug.put(event, file.getBoolean("Debug." + event));
        }

        for (String hook : file.getConfigurationSection("Configuration.Hooks").getKeys(false)) {
            hooks.put(hook, file.getBoolean("Configuration.Hooks." + hook));
        }

        String prefix = "";
        for (String message : file.getConfigurationSection("Messages").getKeys(false)) {
            if (message.equalsIgnoreCase("Commands")) {
                for (String commands : file.getConfigurationSection("Messages.Commands").getKeys(false)) {
                    messages.put(message + "." + commands, file.getString("Messages.Commands." + commands));
                }
            } else if (message.equalsIgnoreCase("Prefix")) {
                prefix = file.getString("Messages." + message);
                messages.put(message, prefix);
            } else if (message.equalsIgnoreCase("Success")) {
                for (String success : file.getConfigurationSection("Messages.Success").getKeys(false)) {
                    messages.put(message + "." + success, file.getString("Messages.Success." + success).replace("{prefix}", prefix));
                }
            } else if (message.equalsIgnoreCase("Lists")) {
                for (String success : file.getConfigurationSection("Messages.Lists").getKeys(false)) {
                    messages.put(message + "." + success, file.getString("Messages.Lists." + success).replace("{prefix}", prefix));
                }
            } else if (message.equalsIgnoreCase("Warnings")) {
                for (String success : file.getConfigurationSection("Messages.Warnings").getKeys(false)) {
                    messages.put(message + "." + success, file.getString("Messages.Warnings." + success).replace("{prefix}", prefix));
                }
            } else if (message.equalsIgnoreCase("Errors")) {
                for (String success : file.getConfigurationSection("Messages.Errors").getKeys(false)) {
                    messages.put(message + "." + success, file.getString("Messages.Errors." + success).replace("{prefix}", prefix));
                }
            } else {
                messages.put(message, file.getString("Messages." + message).replace("{prefix}", prefix));
            }
        }
        showBackupInConsole = file.getBoolean("Configuration.ShowBackupInConsole");
        rewardEnabled = file.getBoolean("Configuration.Reward.Enabled");
        showDimensionInConsole = file.getBoolean("Configuration.ShowDimensionInConsole");
        wallMaterial = file.getString("Configuration.WallMaterial");
        groundMaterial = file.getString("Configuration.GroundMaterial");
        endLocationMaterial = file.getString("Configuration.EndLocationMaterial");
        resolveMaterial = file.getString("Configuration.ResolveMaterial");
        backupTimer = file.getInt("Configuration.BackupTimer");
        velocityPlace = file.getInt("Configuration.VelocityPlace");
    }

    public HashMap<String, Boolean> getHooks() {
        return hooks;
    }

    public void setHooks(HashMap<String, Boolean> hooks) {
        this.hooks = hooks;
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<String, String> messages) {
        this.messages = messages;
    }

    public HashMap<String, Boolean> getDebug() {
        return debug;
    }

    public void setDebug(HashMap<String, Boolean> debug) {
        this.debug = debug;
    }

    public boolean isShowDimensionInConsole() {
        return showDimensionInConsole;
    }

    public void setShowDimensionInConsole(boolean showDimensionInConsole) {
        this.showDimensionInConsole = showDimensionInConsole;
    }

    public boolean isShowBackupInConsole() {
        return showBackupInConsole;
    }

    public void setShowBackupInConsole(boolean showBackupInConsole) {
        this.showBackupInConsole = showBackupInConsole;
    }

    public boolean isRewardEnabled() {
        return rewardEnabled;
    }

    public void setRewardEnabled(boolean rewardEnabled) {
        this.rewardEnabled = rewardEnabled;
    }

    public String getWallMaterial() {
        return wallMaterial;
    }

    public void setWallMaterial(String wallMaterial) {
        this.wallMaterial = wallMaterial;
    }

    public String getGroundMaterial() {
        return groundMaterial;
    }

    public void setGroundMaterial(String groundMaterial) {
        this.groundMaterial = groundMaterial;
    }

    public String getEndLocationMaterial() {
        return endLocationMaterial;
    }

    public void setEndLocationMaterial(String endLocationMaterial) {
        this.endLocationMaterial = endLocationMaterial;
    }

    public String getResolveMaterial() {
        return resolveMaterial;
    }

    public void setResolveMaterial(String resolveMaterial) {
        this.resolveMaterial = resolveMaterial;
    }

    public int getBackupTimer() {
        return backupTimer;
    }

    public void setBackupTimer(int backupTimer) {
        this.backupTimer = backupTimer;
    }

    public int getVelocityPlace() {
        return velocityPlace;
    }

    public void setVelocityPlace(int velocityPlace) {
        this.velocityPlace = velocityPlace;
    }

    public ArrayList<String> getRewards() {
        return rewards;
    }

    public void setRewards(ArrayList<String> rewards) {
        this.rewards = rewards;
    }

    @Override
    public String toString() {
        return "ConfigGestion{" +
                "hooks=" + hooks +
                ", messages=" + messages +
                ", debug=" + debug +
                ", showDimensionInConsole=" + showDimensionInConsole +
                ", showBackupInConsole=" + showBackupInConsole +
                ", rewardEnabled=" + rewardEnabled +
                ", wallMaterial='" + wallMaterial + '\'' +
                ", groundMaterial='" + groundMaterial + '\'' +
                ", endLocationMaterial='" + endLocationMaterial + '\'' +
                ", resolveMaterial='" + resolveMaterial + '\'' +
                ", backupTimer=" + backupTimer +
                ", velocityPlace=" + velocityPlace +
                ", rewards=" + rewards +
                '}';
    }
}
