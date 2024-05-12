package io.eliotesta98.CGACG.Core;

import io.eliotesta98.CGACG.Modules.ItemsAdder.CubeGenerator.GeneratorEvents;
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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public class Main extends JavaPlugin {
    public static Main instance;
    public static ConfigGestion config;

    public void onEnable() {
        DebugUtils debugsistem = new DebugUtils();
        long tempo = System.currentTimeMillis();
        Main.instance = this;

        getServer().getConsoleSender()
                .sendMessage("\n§a   ____    ____      _       ____    ____ \n" +
                        "  / ___|  / ___|    / \\     / ___|  / ___|\n" +
                        " | |     | |  _    / _ \\   | |     | |  _ \n" +
                        " | |___  | |_| |  / ___ \\  | |___  | |_| |\n" +
                        "  \\____|  \\____| /_/   \\_\\  \\____|  \\____|\n"
                        + "\n§e© Developed by §feliotesta98 §ewith §4<3 \r\n \r\n");

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
                e.printStackTrace();
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create config.yml!");
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (outputStream != null) {
                    try {
                        // outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
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
            e.printStackTrace();
        }
        config = new ConfigGestion(YamlConfiguration.loadConfiguration(configFile));
        getServer().getConsoleSender().sendMessage("§aConfiguration Loaded!");
        /*Bukkit.getScheduler().runTaskAsynchronously(instance, () -> {
            loadLabyrinths();
            new UpdateChecker(instance, 103080).getVersion(version1 -> {
                if (!instance.getDescription().getVersion().equals(version1)) {
                    getServer().getConsoleSender().sendMessage(ChatColor.RED + "New Update available for the Labyrinth Plugin!");
                }
            });
        });*/

        // RUNNABLE PER CARICARE LE DIPENDENZE ALLA FINE DELL'AVVIO DEL SERVER :D
        getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
            if (!Bukkit.getServer().getPluginManager().isPluginEnabled("CubeGenerator")) {
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
            if (Bukkit.getServer().getPluginManager().isPluginEnabled("ItemsAdder")) {
                if (getConfigGestion().getHooks().get("ItemsAdder")) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage("§e[CGACG] §7Added compatibility with ItemsAdder.");
                    Bukkit.getServer().getPluginManager().registerEvents(new GeneratorEvents(),this);
                }
            }
        });

        if (config.getDebug().get("Enabled")) {
            debugsistem.addLine("Enabled execution time= " + (System.currentTimeMillis() - tempo));
            debugsistem.debug("Enabled");
        }
    }

    public void onDisable() {
        DebugUtils debugsistem = new DebugUtils();
        long tempo = System.currentTimeMillis();
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "CGACG has been disabled, §cBye bye! §e:(");
        if (config.getDebug().get("Disabled")) {
            debugsistem.addLine("Disabled execution time= " + (System.currentTimeMillis() - tempo));
            debugsistem.debug("Disabled");
        }
    }

    public ConfigGestion getConfigGestion() {
        return config;
    }
}