package io.eliotesta98.CGACG.Core;

import io.eliotesta98.CGACG.Modules.AxBoosters.AxBoosterHook;
import io.eliotesta98.CGACG.Modules.Lands.LandsEvents;
import io.eliotesta98.CGACG.Modules.Lands.LandsUtils;
import io.eliotesta98.CGACG.Modules.PlotSquared7.CubeGenerator.ClickGeneratorFrameEvent;
import io.eliotesta98.CGACG.Modules.PlotSquared7.PlotSquaredEvents;
import io.eliotesta98.CGACG.Modules.RevEnchants.CubeGenerator.BreakEvent;
import io.eliotesta98.CGACG.Modules.RevEnchants.CubeGenerator.DisbandGeneratorEvent;
import io.eliotesta98.CGACG.Modules.RevEnchants.CubeGenerator.PlaceGeneratorEvent;
import io.eliotesta98.CGACG.Modules.RevEnchants.*;
import io.eliotesta98.CGACG.Utils.*;
import org.bukkit.plugin.java.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public class Main extends JavaPlugin {
    public static Main instance;
    public static ConfigGestion config;
    public static LandsUtils landsUtils;

    public void onEnable() {
        DebugUtils debugUtils = new DebugUtils();
        long tempo = System.currentTimeMillis();
        Main.instance = this;

        getServer().getConsoleSender()
                .sendMessage("\n§a   ____    ____      _       ____    ____ \n" +
                        "§a  / ___|  / ___|    / \\     / ___|  / ___|\n" +
                        "§a | |     | |  _    / _ \\   | |     | |  _ \n" +
                        "§a | |___  | |_| |  / ___ \\  | |___  | |_| |\n" +
                        "§a  \\____|  \\____| /_/   \\_\\  \\____|  \\____|\n"
                        + "\n§e© Developed by §feliotesta98 §e& §fxSavior_of_God §ewith §4<3 \r\n \r\n");

        this.getServer().getConsoleSender().sendMessage("§6Loading config...");

        File configFile = new File(this.getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {

                this.saveResource("config.yml", false);
                inputStream = this.getResource("config.yml");

                // write the inputStream to a FileOutputStream
                outputStream = new FileOutputStream(configFile);

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }

            } catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create config.yml!");
                if (getConfigGestion().getDebug().get("Enabled")) {
                    getLogger().log(Level.SEVERE, "Error creating config.yml", e);
                }
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        if (getConfigGestion().getDebug().get("Enabled")) {
                            getLogger().log(Level.SEVERE, "Error creating config.yml", e);
                        }
                    }
                }
                if (outputStream != null) {
                    try {
                        // outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        if (getConfigGestion().getDebug().get("Enabled")) {
                            getLogger().log(Level.SEVERE, "Error creating config.yml", e);
                        }
                    }
                }
            }
        }

        CommentedConfiguration cfg = CommentedConfiguration.loadConfiguration(configFile);

        try {
            String configname;

            configname = "config.yml";

            String splits = "Configuration.Auto_selling.Timer:Configuration.Prices";
            String[] strings = splits.split(":");
            cfg.syncWithConfig(configFile, this.getResource(configname), strings);
        } catch (IOException e) {
            if (getConfigGestion().getDebug().get("Enabled")) {
                getLogger().log(Level.SEVERE, "Error synchronizing config.yml", e);
            }
        }
        config = new ConfigGestion(YamlConfiguration.loadConfiguration(configFile));
        getServer().getConsoleSender().sendMessage("§aConfiguration Loaded!");
        /*Bukkit.getScheduler().runTaskAsynchronously(instance, () -> {
            new UpdateChecker(instance, 103080).getVersion(version1 -> {
                if (!instance.getDescription().getVersion().equals(version1)) {
                    getServer().getConsoleSender().sendMessage(ChatColor.RED + "New Update available for the Labyrinth Plugin!");
                }
            });
        });*/

        // RUNNABLE PER CARICARE LE DIPENDENZE ALLA FINE DELL'AVVIO DEL SERVER :D
        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
            if (!Bukkit.getServer().getPluginManager().isPluginEnabled("CubeGenerator")) {
                if (config.getDebug().get("Enabled")) {
                    debugUtils.addLine("Missing required dependency: CubeGenerator");
                }
                Bukkit.getServer().getConsoleSender()
                        .sendMessage("§e[CGACG] §cCubeGenerator is not present in your server folder, please install it.");
                onDisable();
            }
            if (Bukkit.getServer().getPluginManager().isPluginEnabled("RevEnchants")) {
                if (getConfigGestion().getHooks().get("RevEnchants")) {
                    if (!Bukkit.getServer().getPluginManager().isPluginEnabled("WorldGuard")) {
                        Bukkit.getServer().getConsoleSender()
                                .sendMessage("§e[CGACG] §cWorldGuard is not present in your server folder, please install it.");
                        onDisable();
                        return;
                    }
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage("§e[CGACG] §7Added compatibility with RevEnchants.");

                    if (getConfigGestion().isDropsLikeRevEnchantsMine()) {
                        Bukkit.getServer().getPluginManager().registerEvents(new DropsLikeRevEnchantsMine(), this);
                    }
                    Bukkit.getServer().getPluginManager().registerEvents(new PlaceGeneratorEvent(), this);
                    Bukkit.getServer().getPluginManager().registerEvents(new DisbandGeneratorEvent(), this);
                    Bukkit.getServer().getPluginManager().registerEvents(new JHEvent(), this);
                    Bukkit.getServer().getPluginManager().registerEvents(new BreakEvent(), this);
                    Bukkit.getServer().getPluginManager().registerEvents(new OtherEvents(), this);
                }
            }
            if (Bukkit.getServer().getPluginManager().isPluginEnabled("PlotSquared")) {
                if (getConfigGestion().getHooks().get("PlotSquared7")) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage("§e[CGACG] §7Added compatibility with PlotSquared7.");
                    new PlotSquaredEvents();
                    Bukkit.getServer().getPluginManager().registerEvents(new io.eliotesta98.CGACG.Modules.PlotSquared7.CubeGenerator.PlaceGeneratorEvent(), this);
                    Bukkit.getServer().getPluginManager().registerEvents(new ClickGeneratorFrameEvent(), this);
                }
            }
            if (Bukkit.getServer().getPluginManager().isPluginEnabled("Lands")) {
                if (getConfigGestion().getHooks().get("Lands")) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage("§e[CGACG] §7Added compatibility with Lands.");
                    Bukkit.getServer().getPluginManager().registerEvents(new LandsEvents(), this);
                    Bukkit.getServer().getPluginManager().registerEvents(new io.eliotesta98.CGACG.Modules.Lands.CubeGenerator.GeneratorEvents(), this);
                    landsUtils = new LandsUtils();
                    landsUtils.setLandsIntegration(this);
                }
            }
            if (Bukkit.getServer().getPluginManager().isPluginEnabled("ItemsAdder")) {
                if (getConfigGestion().getHooks().get("ItemsAdder")) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage("§e[CGACG] §7Added compatibility with ItemsAdder.");
                    Bukkit.getServer().getPluginManager().registerEvents(new io.eliotesta98.CGACG.Modules.ItemsAdder.CubeGenerator.GeneratorEvents(), this);
                }
            }
            if (Bukkit.getServer().getPluginManager().isPluginEnabled("AxBooster")) {
                if (getConfigGestion().getHooks().get("AxBooster")) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage("§e[CGACG] §7Added compatibility with AxBooster.");
                    Bukkit.getServer().getPluginManager().registerEvents(new AxBoosterHook(), this);
                }
            }

            if (Bukkit.getServer().getPluginManager().isPluginEnabled("WildTools")) {
                if (getConfigGestion().getHooks().get("WildTools")) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage("§e[CGACG] §7Added compatibility with WildTools.");
                    Bukkit.getServer().getPluginManager().registerEvents(new io.eliotesta98.CGACG.Modules.WildTools.GeneratorEvents(), this);
                }
            }

            if (Bukkit.getServer().getPluginManager().isPluginEnabled("AdvancedEnchantments")) {
                if (getConfigGestion().getHooks().get("AdvancedEnchantments")) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage("§e[CGACG] §7Added compatibility with AdvancedEnchantments.");
                    Bukkit.getServer().getPluginManager().registerEvents(new io.eliotesta98.CGACG.Modules.AdvancedEnchantments.GeneratorEvents(), this);
                }
            }
        });

        if (config.getDebug().get("Enabled")) {
            debugUtils.addLine("Enabled execution time= " + (System.currentTimeMillis() - tempo));
            debugUtils.debug("Enabled");
        }
    }

    public void onDisable() {
        DebugUtils debugUtils = new DebugUtils();
        long tempo = System.currentTimeMillis();
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "CGACG has been disabled, §cBye bye! §e:(");
        if (config.getDebug().get("Disabled")) {
            debugUtils.addLine("Disabled execution time= " + (System.currentTimeMillis() - tempo));
            debugUtils.debug("Disabled");
        }
    }

    public ConfigGestion getConfigGestion() {
        return config;
    }
}