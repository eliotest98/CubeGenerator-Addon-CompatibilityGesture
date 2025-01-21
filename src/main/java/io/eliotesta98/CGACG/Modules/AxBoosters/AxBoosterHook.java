package io.eliotesta98.CGACG.Modules.AxBoosters;

import com.artillexstudios.axboosters.api.AxBoostersAPI;
import com.artillexstudios.axboosters.api.events.AxBoostersLoadEvent;
import io.eliotesta98.CGACG.Core.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AxBoosterHook implements Listener {

    @EventHandler
    public void onLoad(AxBoostersLoadEvent event) {
        AxBoosterCubeGenerator booster = new AxBoosterCubeGenerator();
        // register the booster
        AxBoostersAPI.registerBoosterHook(Main.instance, booster);
        // if you use a listener, you will have to register it
        Main.instance.getServer().getPluginManager().registerEvents(booster, Main.instance);
    }

}
